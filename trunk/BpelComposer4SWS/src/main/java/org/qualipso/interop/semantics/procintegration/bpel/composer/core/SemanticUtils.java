/*
 * SWS Challenge 2008
 *
 * You can redistribute this program and/or modify it under the terms 
 * of the GNU General Public License version 3 (GPL v3.0).
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *
 * You should have received a copy of the GPL
 * along with this program; if not, please have a look at
 * http://www.gnu.org/licenses/gpl.html 
 * to obtain the full license text.
 *
 * Author of this program: 
 * Ralf Weinand
 * Nils Barnickel 
 * Fraunhofer Institute FOKUS, http://www.fokus.fraunhofer.de
 *
 */
package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jess.Rete;

import org.w3c.dom.Document;

import com.hp.hpl.jena.util.FileUtils;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.inference.pellet.ProtegePelletOWLAPIReasoner;
import edu.stanford.smi.protegex.owl.inference.protegeowl.ReasonerManager;
import edu.stanford.smi.protegex.owl.inference.reasoner.ProtegeReasoner;
import edu.stanford.smi.protegex.owl.inference.reasoner.exception.ProtegeReasonerException;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.NamespaceManager;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFIndividual;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.bridge.jess.SWRLJessBridge;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLImp;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLIndividual;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLVariable;

/**
 * Provides several semantic utility methods for handling ontology models.
 */
public class SemanticUtils {

    /**
     * Property for setting indent levels.
     */
    protected static final String INDENT_AMOUNT_XALAN = "{http://xml.apache.org/xalan}indent-amount";
    
    /**
     * The Logger.
     */
    private static Logger Logger = new Logger();

    /**
     * Output some statistics of the SWRL bridge to the debug log.
     * 
     * @param Logger
     *            the Logger
     * @param bridge
     *            the bridge
     */
    public static void logBridgeStatistics(Logger Logger, SWRLJessBridge bridge) {
        Logger.debug("number of imported classes : " + bridge.getNumberOfImportedClasses());
        Logger.debug("number of imported axioms : " + bridge.getNumberOfImportedAxioms());
        Logger.debug("number of imported property assertion axioms : "
                + bridge.getNumberOfImportedPropertyAssertionAxioms());
        Logger.debug("number of imported rules : " + bridge.getNumberOfImportedSWRLRules());
        Logger.debug("number of imported individuals : " 
                + bridge.getNumberOfImportedIndividuals());
        Logger.debug("number of inferred individuals : " 
                + bridge.getNumberOfInferredIndividuals());
        Logger.debug("number of inferred property assertion axioms : "
                + bridge.getNumberOfInferredPropertyAssertionAxioms());
    }

    /**
     * Parses a RDF/XML-serialization and returns the base-URI if a value for
     * 'xml:base' was set, <code>null</code> otherwise.
     * 
     * @param rdfString
     *            RDF/XML-serialization to parse
     * @return the base-URI
     * @throws URISyntaxException
     */
    public static URI extractBaseUriFromRDF(String rdfString) throws URISyntaxException {
        int startPos = -1;
        int endPos = -1;

        startPos = rdfString.indexOf("xml:base=\"") + 10;

        if (startPos > 10) {
            endPos = rdfString.indexOf("\"", startPos + 1);
        } else {
            startPos = rdfString.indexOf("xmlns=\"") + 7;
            endPos = rdfString.indexOf("\"", startPos + 1) - 1; // -1 because
            // protege puts
            // a '#' after
            // xml:base-declaration
        }

        String uri = rdfString.substring(startPos, endPos);

        return new URI(uri);
    }


    /**
     * Parses the given XML String and returns a Document.
     * 
     * @param aXmlString the string to parse
     * @param rootElementName the name of the element which should wrap the whole content;
     *        if <code>null</code>, the content is not wrapped
     * @return Document the DOM
     * 
     * @throws Exception
     */
    public static Document xmlStringToElement(Object aXmlString, String rootElementName)
            throws Exception {
        if (!(aXmlString instanceof String)) {
            Logger.error("Custom XPath Error in xmlStringToElement: " 
            	  + "argument not of type java.lang.String, it's a "
                  + aXmlString.getClass().toString());
            return null;
        }

        try {
            String documentString = (String) aXmlString;

            // Workaround: I have to encapsulate the xmlString with one more
            // "rootElement",
            // because the rootelement is automatically renamed during SOAP
            // encoding
            // and I don´t want to loose the original name (which should be
            // "RDF") of the document root.
            if (rootElementName != null) {
                documentString = "<" + rootElementName + ">" + documentString + "</"
                        + rootElementName + ">";
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setNamespaceAware(true);
            factory.setValidating(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(documentString.getBytes()));

            return document;

        } catch (Exception ex) {
            throw new Exception("Custom XPath Error - xmlStringToElement"
                    + ex.getLocalizedMessage());
        }
    }

    /**
     * This method removes bad namespace declarations such as "p1 = http://".
     * This is a workaround for a bug in the namespace mechanism of Protege3.4
     * build 128. caused by creating objects that do not have a namespace
     * declaration
     * 
     * @param bridgingModel
     *            the model where bad namespace declarations should be removed
     *            
     * @return the cleaned up model            
     */
    public static JenaOWLModel removeBadNamespaceDeclarations(JenaOWLModel bridgingModel) {
        NamespaceManager namespaceManager;
        namespaceManager = bridgingModel.getNamespaceManager();
        
        String badUriNamespacePrefix = namespaceManager.getPrefix("http://");
        while (badUriNamespacePrefix != null) {
            // remove the bad prefix which causes problems
            namespaceManager.removePrefix(badUriNamespacePrefix);
            // check for other bad URIs
            badUriNamespacePrefix = namespaceManager.getPrefix("http://");
        }

        badUriNamespacePrefix = namespaceManager.getPrefix(
                "http://dummy-ontologies.com/dummy.owl#");
        while (badUriNamespacePrefix != null) {
            // remove the bad prefix which causes problems
            namespaceManager.removePrefix(badUriNamespacePrefix);
            // check for other bad URIs
            badUriNamespacePrefix = namespaceManager.getPrefix(
                    "http://dummy-ontologies.com/dummy.owl#");
        }
        return bridgingModel;
    }

    /**
     * Creates an instance for reasoning over the given owl model. There are two
     * implementations that support a direct connection to pellet:
     * ProtegePelletOWLAPIReasoner and ProtegePelletJenaReasoner Currently the
     * ProtegePelletOWLAPIReasoner, is used internally, since this is
     * recommended by protege developers. However, ProtegePelletJenaReasoner
     * does transfer SWRL rules to Pellet. So it should be considered to use
     * this implementation as soon as Pellet supports SWRL build-ins. This is
     * not the case with Pellet version 1.5.1 yet.
     * 
     * @param owlModel
     *            the owl model to reason over.
     * 
     * @return an instance of a Protege reasoner
     */
    public static final ProtegeReasoner createProtegeReasoner(JenaOWLModel owlModel) {

        // Get the reasoner manager and obtain a reasoner for the OWL model.
        ReasonerManager reasonerManager = ReasonerManager.getInstance();

        // Get an instance of the Protege Pellet reasoner
        // ProtegeReasoner reasoner =
        // reasonerManager.createProtegeReasoner(owlModel,
        // ProtegePelletJenaReasoner.class);
        ProtegeReasoner reasoner = reasonerManager.createProtegeReasoner(owlModel,
                ProtegePelletOWLAPIReasoner.class);

        return reasoner;
    }

    /**
     * Perform consistency checks on the model, then classifies, executed SWRL
     * rules and reclassifies instances in the model.
     * 
     * @param jenaModel
     *            the model to reason on
     * 
     * @throws ProtegeReasonerException
     * @throws SWRLRuleEngineBridgeException
     */
    public static void performOWLandSWRLreasoning(JenaOWLModel jenaModel)
            throws ProtegeReasonerException, SWRLRuleEngineBridgeException {
        // create the reasoner for an initial check of the bridging model
        ProtegeReasoner reasoner = SemanticUtils.createProtegeReasoner(jenaModel);
        
        // perform owl reasoning
//        reasoner.computeInconsistentConcepts();
//        reasoner.classifyTaxonomy();
//        reasoner.computeInferredIndividualTypes();        

        // Create Jess rule engine and the bridge
        // to copy swrl-rules and owl-facts to Jess.
        Rete rete = new Rete();
        SWRLJessBridge bridge = null;

        // create the bridge from SWRL to Jess-Rules
        bridge = new SWRLJessBridge(jenaModel, rete);

        // just in case...
        bridge.reset();
        bridge.resetRuleEngine();
        
        // 1. copy swrl-rules and owl-facts to Jess
        // 2. fire the SWRL-rules
        // 3. updates the Protege-OWL-model with new facts
        bridge.importSWRLRulesAndOWLKnowledge();
        bridge.run();
        bridge.writeInferredKnowledge2OWL();
                
        // debug
        if (Logger.isDebugEnabled()) {
            SemanticUtils.logBridgeStatistics(Logger, bridge);
        }

        // after the semantic bridge was executed,
        // again perform owl reasoning
        reasoner.computeInconsistentConcepts();
        reasoner.classifyTaxonomy();
        reasoner.computeInferredIndividualTypes();
    }

    /**
     * Attaches an input individual to a given process individual
     * 
     * @param jenaOWLModel
     *            the ontology model representing the container with all the
     *            process inputs
     * @param process
     *            the process where the input should be added
     * @param parameterName
     *            the name of the input
     * @param parameterType 
     *            the type of the parameter (one of "Input" or "Output")
     * 
     * @return ProcessInput individual
     */
    public static RDFIndividual addProcessParameter(JenaOWLModel jenaOWLModel, 
            RDFIndividual process, String parameterName, String parameterType) {
        OWLNamedClass parameter = jenaOWLModel
                .getOWLNamedClass(jenaOWLModel.getResourceNameForURI(
                        "http://localhost:8080/ontologies/ServiceAnchor.owl#" + parameterType));
        OWLObjectProperty hasParameter = jenaOWLModel
                .getOWLObjectProperty(jenaOWLModel.getResourceNameForURI(
                        "http://localhost:8080/ontologies/ServiceAnchor.owl#has" + parameterType));
        
        // create a parameter and set its name as label
        RDFIndividual parameterIndividual = parameter.createRDFIndividual(
                parameterName + parameterType);
        parameterIndividual.addLabel(parameterName, null);
        
        // add the parameter to the process individual
        process.addPropertyValue(hasParameter, parameterIndividual);
        
        return parameterIndividual;
    }


    /**
     * Creates a process individual to use as parameter root.
     * 
     * @param model
     * @param processLabel
     * @return process root individual
     */
    public static RDFIndividual addProcessRoot(JenaOWLModel model, String processLabel) {
        OWLNamedClass process = model
                .getOWLNamedClass(model.getResourceNameForURI(
                        "http://localhost:8080/ontologies/ServiceAnchor.owl#Process"));
        
        // create an instance of type Process and add a label
        RDFIndividual aProcess = process.createRDFIndividual(processLabel);
        aProcess.addLabel(processLabel, null);
        
        return aProcess;
    }

    /**
     * Check if the given namespace is related to Protege.
     * 
     * @param namespace the namespace
     * 
     * @return <code>true</code> if the given namespace is Protege-related, 
     *         otherwise (also if <code>null</code>) <code>false</code>
     */
    private static boolean isProtegeRelatedNamespace(String namespace) {
        
        if (namespace == null)
        {
            return false;
        }
        
        if (namespace.endsWith("#"))
        {
            namespace = namespace.substring(0, namespace.length() - 1);
        }
        
        return "http://www.w3.org/2003/11/swrl".equals(namespace)
                || "http://swrl.stanford.edu/ontologies/built-ins/3.3/temporal.owl"
                    .equals(namespace)
                || "http://swrl.stanford.edu/ontologies/3.3/swrla.owl".equals(namespace)
                || "http://swrl.stanford.edu/ontologies/built-ins/3.3/swrlx.owl".equals(namespace)
                || "http://swrl.stanford.edu/ontologies/built-ins/3.4/swrlm.owl".equals(namespace)
                || "http://www.w3.org/2003/11/swrlb".equals(namespace)
                || "http://swrl.stanford.edu/ontologies/built-ins/3.3/abox.owl".equals(namespace)
                || "http://protege.stanford.edu/plugins/owl/protege".equals(namespace)
                || "http://sqwrl.stanford.edu/ontologies/built-ins/3.4/sqwrl.owl".equals(namespace)
                || "http://swrl.stanford.edu/ontologies/built-ins/3.3/tbox.owl".equals(namespace)
                || "http://swrl.stanford.edu/ontologies/built-ins/3.4/swrlxml.owl"
                    .equals(namespace);
    }


    /**
     * Check if the given namespace is an RDF-, OWL-, or XSD-related namespace.
     * 
     * @param namespace the namespace
     * 
     * @return <code>true</code> if the given namespace is Protege-related
     */
    private static boolean isRdfOwlOrXsdRelatedNamespace(String namespace) {
        
        if (namespace == null)
        {
            return false;
        }
        
        if (namespace.endsWith("#"))
        {
            namespace = namespace.substring(0, namespace.length() - 1);
        }
        
        return "http://www.daml.org/2001/03/daml+oil#".equals(namespace)
              || "http://www.w3.org/2000/01/rdf-schema#".equals(namespace)
              || "http://www.w3.org/2001/XMLSchema#".equals(namespace)
              || "http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(namespace)
              || "http://www.w3.org/2002/07/owl#".equals(namespace);
    }

    /**
     * Remove the T-Box and and all parts of the A-Box that are Protege-internal
     * from the given model.
     * 
     * @param originalJenaModel the model to clean up
     * @return the cleaned up model
     * 
     * @throws Exception
     */
    public static JenaOWLModel removeTBox(JenaOWLModel originalJenaModel) throws Exception {
        Vector individualsToCopy = new Vector();
        Vector registeredNamespaces = new Vector();

        // find the relevant individuals to copy and their necessary 
        // namespaces that need to be imported into new model 
        // in order to copy the individuals into it
        for (Iterator iterator = originalJenaModel.getUserDefinedRDFIndividuals(true)
                .iterator(); iterator.hasNext();) {
            RDFResource individual = (RDFResource) iterator.next();
            
            String namespace = individual.getNamespace();
            if (!(isProtegeRelatedNamespace(namespace)
                    || individual instanceof SWRLVariable
                    || individual instanceof SWRLImp
                    || individual instanceof SWRLIndividual
                    || individual.getName().trim().toLowerCase().equals("<<deleted>>")))
            {                   
                // mark individual for copy operation
                individualsToCopy.add(individual);
                
                // register the namespaces of all types of individuals for imports
                for (Iterator types = individual.getDirectTypes().iterator(); types.hasNext();) {
                    RDFResource type = (RDFResource) types.next();                    
                    if (!isProtegeRelatedNamespace(namespace) 
                            && !isRdfOwlOrXsdRelatedNamespace(namespace))
                    {
                        registeredNamespaces.add(type.getNamespace());
                    }
                }
                for (Iterator types = individual.getInferredTypes().iterator(); types.hasNext();) {
                    RDFResource type = (RDFResource) types.next();
                    if (!isProtegeRelatedNamespace(namespace) 
                            && !isRdfOwlOrXsdRelatedNamespace(namespace))
                    {
                        registeredNamespaces.add(type.getNamespace());
                    }
                }                
                for (Iterator types = individual.getRDFProperties().iterator(); types.hasNext();) {
                    RDFResource property = (RDFResource) types.next();
                    if (!isProtegeRelatedNamespace(namespace) 
                            && !isRdfOwlOrXsdRelatedNamespace(namespace))
                    {
                        registeredNamespaces.add(property.getNamespace());
                    }
                }                
            }
            else
            {
                Logger.debug("IGNORING Individual (Type=" + individual.getClass().getName() 
                        + "): " + individual.getName() + " with namespace: " + namespace);
            }
        }  
        
        // create new empty model
        JenaOWLModel resultingJenaModel = ProtegeOWL.createJenaOWLModel();
        
        // copy namespace/prefix-mappings to new model
        NamespaceManager nsmFrom = originalJenaModel.getNamespaceManager();
        NamespaceManager nsmTo = resultingJenaModel.getNamespaceManager();
        for (Iterator iterator = nsmFrom.getPrefixes().iterator(); iterator.hasNext();) {
            String prefix = (String) iterator.next();
            String namespace = nsmFrom.getNamespaceForPrefix(prefix);
            if (!isProtegeRelatedNamespace(namespace))
            {
                Logger.debug("Copy namespace/prefix mapping for: " + namespace);
                nsmTo.setPrefix(nsmFrom.getNamespaceForPrefix(prefix), prefix);
            }
        }
        // set the same default namespace for the new model 
        // (since it has to represent the original model)
        String defaultNamespace = nsmFrom.getDefaultNamespace();
        Logger.debug("Default Namespace: " + defaultNamespace);
        nsmTo.setDefaultNamespace(defaultNamespace);
        resultingJenaModel.getTripleStoreModel().getTopTripleStore()
        .setDefaultNamespace(defaultNamespace);
        Logger.debug(originalJenaModel.getDefaultOWLOntology().getBrowserText());
        
        // copy imports to new model
        for (Iterator iterator = originalJenaModel.getAllImports().iterator(); 
                iterator.hasNext();) {
            String importURI = (String) iterator.next();
            
            if (registeredNamespaces.contains(importURI) 
                    || registeredNamespaces.contains(importURI + "#"))
            {
                Logger.debug("Copy import for: " + importURI);
                resultingJenaModel.getDefaultOWLOntology().addImports(importURI);
            }
        }

        // write intermediate model without individuals 
        // to avoid the mysterious "copy slot assertion" error
        Collection errors = new Vector(); 
        File tempFile = File.createTempFile("temp_intermediate_ontology_", ".owl");
        JenaOWLModel.save(tempFile, resultingJenaModel.getOntModel(), 
                FileUtils.langXMLAbbrev, originalJenaModel.getDefaultOWLOntology().getNamespace());
        if (!errors.isEmpty())
        {
            for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
                Object error = iterator.next();
                Logger.debug("Error: " + error);
            }
        }
        
        // load the intermediate model
        resultingJenaModel = ProtegeOWL.createJenaOWLModelFromURI(tempFile.toURL().toString());        

        // copy individuals to intermediate model
        for (Iterator iterator = individualsToCopy.iterator(); iterator.hasNext();) {
            RDFResource individual = (RDFResource) iterator.next();
            
            String namespace = individual.getNamespace();
        
            Logger.debug("copy individual: " + individual.getBrowserText() 
                    + " with namespace: " + individual.getNamespace() 
                    + " and namespacePrefix: " + individual.getNamespacePrefix() 
                    + " and Class: " + individual.getClass().getName());
            //individual.shallowCopy(resultingJenaModel, null);

            // create new instance with same name and direct types
            Collection allTypes = individual.getDirectTypes();
            edu.stanford.smi.protegex.owl.model.OWLIndividual newIndividual = 
                resultingJenaModel.getOWLThingClass().createOWLIndividual(individual.getName());
            
            // add the asserted types
            for (Iterator inferredTypesIt = individual.getInferredTypes().iterator(); 
                    inferredTypesIt.hasNext();) {
                Cls type = (Cls) inferredTypesIt.next();
                newIndividual.addDirectType(type);
            }
            
            // also add the inferred types as asserted types now
            for (Iterator inferredTypesIt = individual.getInferredTypes().iterator(); 
                    inferredTypesIt.hasNext();) {
                Cls type = (Cls) inferredTypesIt.next();
                newIndividual.addDirectType(type);
            }              
        
        }  
        // copy properties of individuals to intermediate model
        for (Iterator iterator = individualsToCopy.iterator(); iterator.hasNext();) {
            RDFResource individual = (RDFResource) iterator.next();
            
            String namespace = individual.getNamespace();      
            Logger.debug("copy properties of individual: " + individual.getBrowserText() 
                    + " with namespace: " + individual.getNamespace() + " and namespacePrefix: " 
                    + individual.getNamespacePrefix() + " and Class: " 
                    + individual.getClass().getName());

            edu.stanford.smi.protegex.owl.model.OWLIndividual newIndividual = 
                resultingJenaModel.getOWLIndividual(individual.getName());

            // create all properties
            for (Iterator propertyIt = individual.getRDFProperties().iterator(); 
                    propertyIt.hasNext();) {
                RDFProperty property = (RDFProperty) propertyIt.next();
                Logger.debug("  property: " + property.getBrowserText());
                
                Collection propertyValues = new Vector();
                for (Iterator iterator2 = individual.listPropertyValues(property); 
                        iterator2.hasNext();) {
                    Object propertyValue = iterator2.next();
                    Logger.debug("  property value: " + propertyValue);
                   
                    if (!"protege".equals(property.getNamespacePrefix()))
                    {
                        Logger.debug("  - copy property " + property.getName());
                        propertyValues.add(propertyValue);
                    }
                }
                newIndividual.setPropertyValues(property, propertyValues);
            }                
        }  

//        if (Logger.isDebugEnabled())
//        {
//            // log resulting model
//            Vector errors2 = new Vector(); 
//            File tempFile2 = File.createTempFile("temp_intermediate_ontology_", ".owl");
//            JenaOWLModel.save(tempFile2, resultingJenaModel.getOntModel(), 
//              FileUtils.langXMLAbbrev, originalJenaModel.getDefaultOWLOntology().getNamespace());
//            if (!errors2.isEmpty())
//            {
//                for (Iterator iterator = errors2.iterator(); iterator.hasNext();) {
//                    Object error = iterator.next();
//                    Logger.debug("Error: " + error);
//                }
//            }        
//        }

        return resultingJenaModel;
    }
}
