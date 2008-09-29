package org.qualipso.interop.semantics.procintegration.bpel.composer.core;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.mindswap.owl.OWLIndividual;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.FileUtils;

import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.NamespaceManager;
import edu.stanford.smi.protegex.owl.model.util.ImportHelper;

/**
 * Semantic Bridge to be applied on service output parameters to mediate between
 * different ontologies. This class represents the semantic bridging engine.
 * 
 */
public class SemanticBridge {

	private String semanticBridgeURL;

	private String sourceOntology;

	private String targetOntology;

	/**
	 * Load Semantic Bridge rules and prepare models for reasoning.
	 * 
	 * @param semanticBridgeURL the URL of the Semantic Bridge
	 */
	public SemanticBridge(String semanticBridgeURL) {

		long t1 = System.currentTimeMillis();

		// set filename
		this.semanticBridgeURL = semanticBridgeURL;

        // extract source and target ontology
		try {
		    JenaOWLModel bridgeModel = ProtegeOWL.createJenaOWLModelFromURI(semanticBridgeURL);
		    NamespaceManager nsm = bridgeModel.getNamespaceManager();
		    
		    // extract the source and target ontology URIs
		    for (Iterator namespaces = nsm.getPrefixes().iterator(); namespaces.hasNext();) {
                String prefix = (String) namespaces.next();
                
                if (prefix.startsWith("source")) {
                    if (this.sourceOntology != null && !this.sourceOntology.equals("")) {
                        String message = "Found two namespace declarations " 
                        		+ "with prefix starting with 'source' " 
                                + "in semantic bridge " + semanticBridgeURL;
                        Logger.info(message);
                        throw new Exception(message);
                    }
                    
                    String namespace = nsm.getNamespaceForPrefix(prefix);
                    
                    if (namespace.endsWith("#")) {
                        this.sourceOntology = namespace.substring(0, namespace.length() - 1);
                    } else {
                        this.sourceOntology = namespace;
                    }
                } else if (prefix.startsWith("target")) {
                    if (this.targetOntology != null && !this.targetOntology.equals("")) {
                        String message = "Found two namespace declarations with prefix " 
                        		+ "starting with 'target' " 
                                + "in semantic bridge " + semanticBridgeURL;
                        Logger.info(message);
                        throw new Exception(message);
                    }
                    
                    String namespace = nsm.getNamespaceForPrefix(prefix);
                    
                    if (namespace.endsWith("#")) {
                        this.targetOntology = namespace.substring(0, namespace.length() - 1);
                    } else {
                        this.targetOntology = namespace;
                    }
                }
            }
            // free memory
            bridgeModel.dispose();
		} catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }

		// time logging
		long t2 = System.currentTimeMillis();
		Logger.info("SemanticBridgeCreationTimeConsumption for "
				+ semanticBridgeURL + ": " + (t2 - t1));
	}

	/**
	 * Apply Semantic Bridge rules to service output parameter, refresh
	 * classification and rebuild parameter node tree.
	 * 
	 * @param serviceOutput the service output to use
	 */
	public void applyToServiceOutput(ServiceOutput serviceOutput) {

		//serviceOutput.removeTBoxRedundancy();

		long t1 = System.currentTimeMillis();

        JenaOWLModel bridgingModel = null;
        try {
            String proxyIndividual = ((OWLIndividual) serviceOutput.root.getValue()).toRDF();
//            Logger.debug("EXTRACTED PROXY INDIVIDUAL TO BE BRIDGED: ");
//            Logger.debug(proxyIndividual);

            // save individual ontology to file for import
            File individualFile = File.createTempFile(
                    "tempOntology_serviceIndividualINCOMMING", ".owl");
            FileOutputStream fos = new FileOutputStream(individualFile);
            fos.write(proxyIndividual.getBytes("UTF-8"));
            fos.close();
            
            // import the individuals into the main model (TopTripleStore)
            bridgingModel = ProtegeOWL.createJenaOWLModelFromURI(
                    individualFile.toURI().toString());
            
            // Import the bridge ontology containing the mapping rules 
            // from the specified URI            
            ImportHelper importHelper = new ImportHelper(bridgingModel);
            importHelper.addImport(new URI(this.semanticBridgeURL));
            importHelper.importOntologies();
            
            // TODO this is a workaround for removing "bad" namespace declarations
            // caused by creating objects that do not have a namespace declaration
            bridgingModel = SemanticUtils.removeBadNamespaceDeclarations(bridgingModel);
            
            // safe and reload the file due to incompatibilities 
            // between the different serializations
            File bridgeModelFile = File.createTempFile(
                    "tempOntology_serviceIndividual+bridge", ".owl");
            String owlFileName = bridgeModelFile.getAbsoluteFile().toURI().toString(); 
            Collection errors = new Vector();            
            bridgingModel.save(bridgeModelFile.getAbsoluteFile().toURI(), 
                    FileUtils.langXMLAbbrev, errors);          
            //bridgingModel.getOntModel().writeAll(fos, FileUtils.langXMLAbbrev, owlFileName ); 
            bridgingModel = ProtegeOWL.createJenaOWLModelFromURI(owlFileName);
            
            // DEBUG: log errors
            for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
                Object error = iterator.next();
                Logger.debug(error.toString());
            }

            // perform all necessary steps for OWL and SWRL reasoning 
            // (which performs the actual dataflow)
            SemanticUtils.performOWLandSWRLreasoning(bridgingModel);

            // clean up the bridging model 
            // ATTENTION: if this line is deleted, then the 
            // "Find Matching"-functionality does not work
            bridgingModel = SemanticUtils.removeTBox(bridgingModel);

            // safe and reload (again after reasoning) due to problems getting the inferred types
            // when a model with inferred types is written to file, then the types are asserted
            // next time the ontology is loaded
            File resultingModelFile = File.createTempFile(
                    "tempOntology_serviceIndividual+bridge_After_Reasoning", ".owl");
            owlFileName = resultingModelFile.getAbsoluteFile().toURI().toString(); 
            errors = new Vector();            
            bridgingModel.save(resultingModelFile.getAbsoluteFile().toURI(), 
                    FileUtils.langXML, errors);          
            
            FileInputStream fis = new FileInputStream(resultingModelFile); 
            BufferedInputStream bis = new BufferedInputStream(fis); 
            DataInputStream dis = new DataInputStream(bis); 

            String line;
            StringBuffer fileContent = new StringBuffer();
            while ((line = dis.readLine()) != null) { 
                fileContent.append(line);
            } 
            
            dis.close();
            bis.close();
            fis.close();
            
            String modelString = fileContent.toString();   
       
            bridgingModel = ProtegeOWL.createJenaOWLModelFromReader(new StringReader(modelString));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

		// restore statements to parameter KB
        ((OntModel) serviceOutput.getKb().getImplementation()).removeAll();
        ((OntModel) serviceOutput.getKb().getImplementation()).add(bridgingModel.getOntModel());

		// time logging
		long t2 = System.currentTimeMillis();
		Logger.info("SemanticBridgingTimeConsumption for "
				+ serviceOutput.getName() + ": " + (t2 - t1));

		MessagePanel.appendSuccessln("done.");

		// rebuild ParameterNode tree according additional inferred nodes
		MessagePanel.appendOut("    Rebuilding parameter tree for ");
		MessagePanel.appendOutBlue(serviceOutput.getName());
		MessagePanel.appendOut(" ... ");
		//writeModel((OntModel)serviceOutput.getKb().getImplementation(),"CheckDat1.owl");
        serviceOutput.removeTBoxRedundancy();
		serviceOutput.rebuildParameterTreeStart();
		MessagePanel.appendSuccessln("done.");

        // make sure the memory is deallocated
        bridgingModel.dispose();
	}

	/**
	 * Serializes model to file.
	 * 
	 * @param model the model to serialise
	 * @param filename the filename
	 */
	public static void writeModel(OntModel model, String filename) {
		try {
			model.writeAll(new FileOutputStream(filename), "RDF/XML", null);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	/**
	 * Get the source ontoloy.
	 * 
	 * @return the URL of source ontology
	 */
	public String getSourceOntology() {
		return sourceOntology;
	}

	/**
	 * Get the URL of the Semantic Bridge.
	 * 
	 * @return the URL of the Semantic Bridge
	 */
	public String getSemanticBridgeURL() {
		return semanticBridgeURL;
	}
}
