package org.qualipso.interop.semantics.procintegration.bpel.composer.core;


import java.io.StringReader;
import java.net.URI;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Represents an output parameter of a service.
 *
 */
public class ServiceOutput extends Parameter{
	
	// serviceModel to which output belongs
	private ServiceModel service;

	/**
	 * Generate parameter and classify to prepare for matching.
	 * @param service
	 * @param name
	 * @param outputTypeURI
	 */
	public ServiceOutput(ServiceModel service,String name, URI outputTypeURI){
		super(name,outputTypeURI);
		this.service = service;
		
		MessagePanel.appendSuccessln(" done.");
		MessagePanel.appendOut("    Classify output parameter ... ");
		
		
		long t1 = System.currentTimeMillis();
		
		// create OWL-DL reasoning model for facet analysis
		OntModel owlDLModel = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		owlDLModel.add((OntModel)kb.getImplementation());
		
		// inferre types from subclass relations
		((OntModel)kb.getImplementation()).add(owlDLModel);
		
		// add inferred types to type collection of ParameterNodes
		((ParameterNode)this.getRoot()).updateInferredTypes();
		
		
		// time logging
		long t2 = System.currentTimeMillis();
		Logger.info("OutputClassificationTimeConsumption for "
				+ this.getName() + ": " + (t2 - t1));
	

		
		MessagePanel.appendSuccessln("done");
	}

	/**
	 * Semantic bridging of this output parameter. 
	 * @param semanticBridge
	 */
	public void applySemanticBridge(SemanticBridge semanticBridge){
		Logger.info("apply semantic bridge");
		semanticBridge.applyToServiceOutput(this);
	}
	
	
	public ServiceModel getService() {
		return service;
	}
	
	/**
	 * Remove old T-Box to reduce complexity, applied after semantic bridging.
	 */
	public void removeTBoxRedundancy(){
		
		long t1 = System.currentTimeMillis();
        
        // debug output
//        ((OntModel) this.getKb().getImplementation()).writeAll(System.out, FileUtils.langXMLAbbrev, null);
		
		// extract parameter root individual
		OWLClass parameterTypeClass = this.getKb().getClass(this.getParameterTypeURI());
        OWLIndividual rootIndividual = (OWLIndividual) this.getKb()
		.getInstances(parameterTypeClass).iterator().next();
		
		// and store without T-Box in model for data handling
		OWLKnowledgeBase kbTemp = OWLFactory.createKB();
		((OntModel) kbTemp.getImplementation()).read(new StringReader(
				rootIndividual.toRDF()), null);
		
		// rebuild type information
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		model.add((OntModel) kbTemp.getImplementation());
		((OntModel) kbTemp.getImplementation()).add(model);
		
		
		// replace KB
		this.kb=kbTemp;
		
		// get new root
		OWLIndividual newRootIndividual = (OWLIndividual) this.getKb()
		.getInstances(parameterTypeClass).iterator().next();
		
		// set new root
		((ParameterNode)this.getRoot()).setValue(newRootIndividual);
		
		// time logging
		long t2 = System.currentTimeMillis();
		Logger.info("RemoveTBoxTimeConsumption for "
				+ this.getName() + ": " + (t2 - t1));
	}

}
