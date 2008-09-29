package org.qualipso.interop.semantics.procintegration.bpel.composer.core;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

import org.mindswap.owl.OWLConfig;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;

import com.hp.hpl.jena.util.FileUtils;

import edu.stanford.smi.protege.util.URIUtilities;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.NamespaceManager;
import edu.stanford.smi.protegex.owl.model.util.ImportHelper;
import edu.stanford.smi.protegex.owl.swrl.model.SWRLFactory;
import edu.stanford.smi.protegex.owl.swrl.parser.SWRLParser;

/**
 * Generates dataflow rules based on assignments between parameter nodes.
 *
 */
public class Dataflow {

	// map of Strings representing pairs of (rule name, dataflow rules in human readable syntax)
	private TreeMap dataflowRules;
	
	// map with mappings from namespaces to prefixes
	private TreeMap registeredPrefixes;

	// collection of Strings representing each one rule for creating a new node
	// in an assignment path
	private TreeMap createRules;

	// collection of Strings naming dataflow source container
	// (serviceName|processInput)
	private Vector sources;

	// rules filename
	private String dataflowFilename;
    private File dataflowFile;

	// counter variable for unique rule names
	private int i = 1;

	private int k = 1;


	/**
	 * Constructor for a Dataflow.
	 * 
	 * @param name the name of the dataflow file to use (without path and extension)
	 */
	public Dataflow(String name) {
        this.dataflowRules = new TreeMap();
        this.registeredPrefixes = new TreeMap();
		this.createRules = new TreeMap();
		this.sources = new Vector();
		this.dataflowFilename = name + ".owl";
		// configure owl-s api
		OWLConfig.setStrictConversion(false);
	}

	/**
	 * Generate rule with builtin swrlx:makeOWLThing to create upper level empty node 
	 * if assignments have been made to all children. 
	 * 
	 * @param emptyNode the parameter node for which to create an empty node
	 */
	public void createEmptyNode(ParameterNode emptyNode) {

		try {

			ServiceModel targetService = ((ServiceInput) emptyNode
					.getParameter()).getService();

			// define (x has new), (n typeOf NewClass) <- fromPath x,
			// makeInstance(x
			// has new)
			// backward rule because Jena builtIn 'makeInstance' can only be
			// used in
			// backward rules
			// body seems to be redundant but so it works with Jena rule engine
			// 1) from-path init
			String ruleName = "dataflow" + targetService.getName() + "Create"
					+ k++;
			String createRuleInit1 = "anchor:Process(?" + targetService.getName() + ")";
			String createRuleInit2 = "rdfs:label(?" + targetService.getName()
					+ ", \""
					+ targetService.getOwlsService().getName() + "Process\")";
			String createRuleInit3 = "anchor:hasInput(?" + targetService.getName()
					+ ", ?" + targetService.getName() + "IN)";
			String createRuleInit4 = "rdfs:label(?" + targetService.getName()
					+ "IN, \"" + emptyNode.getParameter().getName() + "\")";

			String makeInstance = "";
			String path = "";
			String head1 = "";

			if (emptyNode.isRoot()) {
				path = "";
				makeInstance = "swrlx:makeOWLThing(?N,?" 
                    + targetService.getName() + "IN)";

				head1 = "anchor:parameterObject(?" + targetService.getName()
						+ "IN, ?N)";

			} else {
				// TODO not yet tested
				// 2) get path-nodes in right order
				Vector pathParameterNodes = new Vector();

				ParameterNode nodeIter = emptyNode.getParent();
				while (nodeIter.getParent() != null) {
					pathParameterNodes.insertElementAt(nodeIter, 0);
					nodeIter = nodeIter.getParent();
				}

				// 3) create path rule fragment
				path = SWRLParser.AND_CHAR + " anchor:parameterObject(?" + targetService.getName()
						+ "IN, ?X0)";

				// rest
				Iterator iter1 = pathParameterNodes.iterator();
				int pathIndex = 0;

				while (iter1.hasNext()) {
					ParameterNode node = (ParameterNode) iter1.next();
	                URI propertyURI = node.getProperty().getURI();
	                String prefix = createPrefixForNamespace(propertyURI);
					path = path + SWRLParser.AND_CHAR + prefix + ":" + propertyURI.getFragment() 
					        + "(?X" + pathIndex++ + ",?X" + pathIndex + ")";
				}
				makeInstance = "swrlx:makeOWLThing(?N, ?X" + pathIndex + ")";

		        URI propertyURI = emptyNode.getProperty().getURI();
                String prefix = createPrefixForNamespace(propertyURI);
				head1 = prefix + ":" + propertyURI.getFragment() + "(?X" + pathIndex + ",?N)";

			}
            URI propertyURI = emptyNode.getDirectType();
            String prefix = createPrefixForNamespace(propertyURI);
			String head2 = prefix + ":" + propertyURI.getFragment() + "(?N)";
			
			String rule = createRuleInit1 + SWRLParser.AND_CHAR 
    			+ createRuleInit2 + SWRLParser.AND_CHAR
                + createRuleInit3 + SWRLParser.AND_CHAR 
                + createRuleInit4 + path + SWRLParser.AND_CHAR
                + makeInstance 
                + SWRLParser.IMP_CHAR 
                + head1 + SWRLParser.AND_CHAR 
                + head2;

			this.createRules.put(ruleName, rule);

		} catch (Exception e) {
			MessagePanel.appendErrorln(e.getMessage());
			Logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Generate rule for assignment between service parameter nodes.
	 * 
	 * @param source the source of the assignment
	 * @param target the target of the assignment
	 */
	public void addService2ServiceDataflow(ParameterNode source,
			ParameterNode target) {

		ServiceModel sourceService = ((ServiceOutput) source.getParameter())
				.getService();
		ServiceModel targetService = ((ServiceInput) target.getParameter())
				.getService();

		// define rule fromPath x, toPath y -> y has x
		// 1) from-path init
		String ruleName = "dataflow" + targetService.getName() + "Assignment"
				+ i++;
        String dataflowRuleFromInit0 = "owl:Thing(?copyAllIndividualsToJess)";
		String dataflowRuleFromInit1 = "anchor:Process(?" + sourceService.getName() + ")";
		String dataflowRuleFromInit2 = "rdfs:label(?" + sourceService.getName()
				+ ", \"" + sourceService.getOwlsService().getName()
				+ "Process\")";
		String dataflowRuleFromInit3 = "anchor:hasOutput(?" + sourceService.getName()
				+ ", ?" + sourceService.getName() + "OUT)";
		String dataflowRuleFromInit4 = "rdfs:label(?" + sourceService.getName()
				+ "OUT, \"" + source.getParameter().getName() + "\")";
		String dataflowRuleFromInit5 = "anchor:parameterObject(?" + sourceService.getName()
				+ "OUT, ?X0)";

		// 2) get from-path-nodes in right order
		Vector fromPathParameterNodes = new Vector();
		ParameterNode nodeIter = source;
		while (nodeIter.getParent() != null) {
			fromPathParameterNodes.insertElementAt(nodeIter, 0);
			nodeIter = nodeIter.getParent();
		}

		// 3) create from-path rule fragment
		// init
		String fromPath = dataflowRuleFromInit0 
		        + SWRLParser.AND_CHAR + dataflowRuleFromInit1 
		        + SWRLParser.AND_CHAR + dataflowRuleFromInit2
				+ SWRLParser.AND_CHAR + dataflowRuleFromInit3 
				+ SWRLParser.AND_CHAR + dataflowRuleFromInit4
				+ SWRLParser.AND_CHAR + dataflowRuleFromInit5;

		// rest
		Iterator iter1 = fromPathParameterNodes.iterator();
		int fromIndex = 0;
		while (iter1.hasNext()) {
			ParameterNode node = (ParameterNode) iter1.next();
            URI propertyURI = node.getProperty().getURI();
            String prefix = createPrefixForNamespace(propertyURI);
            fromPath = fromPath + SWRLParser.AND_CHAR + prefix + ":" + propertyURI.getFragment() 
		            + "(?X" + fromIndex++ + ",?X" + fromIndex + ")";
		}

		// 1) to-path init
		String dataflowRuleToInit1 = "anchor:Process(?" + targetService.getName() + ")";
		String dataflowRuleToInit2 = "rdfs:label(?" + targetService.getName()
				+ ", \"" + targetService.getOwlsService().getName()
				+ "Process\")";
		String dataflowRuleToInit3 = "anchor:hasInput(?" + targetService.getName()
				+ ", ?" + targetService.getName() + "IN)";
		String dataflowRuleToInit4 = "rdfs:label(?" + targetService.getName()
				+ "IN, \"" + target.getParameter().getName() + "\")";
		String toPath = "";
		String ruleHead = "";

		// 2) get to-path-nodes in right order
		Vector toPathParameterNodes = new Vector();
		ParameterNode toPathNodeIter = target;

		// target node is not input parameter root node
		if (toPathNodeIter.getParent() != null) {
			toPathNodeIter = toPathNodeIter.getParent();

			// ToPath start
			String dataflowRuleToInit5 = SWRLParser.AND_CHAR 
			        + " anchor:parameterObject(?" + targetService.getName()
					+ "IN, ?Y0)";

			// ToPath rest
			while (toPathNodeIter.getParent() != null) {
				toPathParameterNodes.insertElementAt(toPathNodeIter, 0);
				toPathNodeIter = toPathNodeIter.getParent();
			}

			// 3) create to-path rule fragment
			toPath = dataflowRuleToInit1 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit2 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit3 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit4
					+ dataflowRuleToInit5;

			Iterator iter2 = toPathParameterNodes.iterator();
			int k = 0;
			while (iter2.hasNext()) {
				ParameterNode node = (ParameterNode) iter2.next();
	            URI propertyURI = node.getProperty().getURI();
	            String prefix = createPrefixForNamespace(propertyURI);
				toPath = toPath + SWRLParser.AND_CHAR + prefix + ":" 
				        + propertyURI.getFragment() + "(?Y" + k++ 
						+ ",?Y" + k	+ ")";
			}

			// rule head
            URI propertyURI = target.getProperty().getURI();
            String prefix = createPrefixForNamespace(propertyURI);
			ruleHead = prefix + ":" + propertyURI.getFragment() + "(?Y" + k + ",?X"	
			    + fromIndex + ")";
			
			// backward
//			this.dataflowRules.add("\n[" + ruleName + ": " + ruleHead + " <- "
//					+ fromPath + toPath + ", print('eval rule: " + ruleName + "')]");
			
			this.dataflowRules.put(ruleName, fromPath + SWRLParser.AND_CHAR + toPath 
			        + SWRLParser.IMP_CHAR + ruleHead);
			
			// target node is input parameter root node
		} else {
			// 3) create to-path rule fragment
			toPath = dataflowRuleToInit1 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit2 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit3 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit4;

			ruleHead = "anchor:parameterObject(?" + targetService.getName()
					+ "IN, ?X" + fromIndex + ")";

			// this rule weirdly only runs in forward mode
			this.dataflowRules.put(ruleName, fromPath + SWRLParser.AND_CHAR + toPath
					+ SWRLParser.IMP_CHAR + ruleHead);
//			Logger.debug("[" + ruleName + ": " + fromPath + toPath + SWRLParser.IMP_CHAR
//					+ ruleHead + ", print('eval rule: " + ruleName + "')]");
		}

		// set dataflow source container
		this.addDataflowSources(sourceService);
	}

	/**
	 * Sets dataflow source container for execution plan.
	 * 
	 * @param sourceService the service to add as source for the dataflow
	 */
	private void addDataflowSources(ServiceModel sourceService) {

		// no semantic bridges applied, use output container directly
//		if (sourceService.getSemanticBridges().size() == 0) {
			if (!this.sources.contains(sourceService.getName() + "Output")) {
				this.sources.add(sourceService.getName() + "Output");
//				Logger.info("source: " + sourceService.getName() + "Output");
			}
			// use semantic bridging output container
//		} else {
//			Iterator iter = sourceService.getSemanticBridges().iterator();
//			while (iter.hasNext()) {
//				SemanticBridge semanticBridge = (SemanticBridge) iter.next();
//				String sourceName = sourceService.getName() + "Output"
//						+ semanticBridge.getFilename().replaceAll(".rules", "");
//				if (!this.sources.contains(sourceName)) {
//					this.sources.add(sourceName);
//					Logger.info("source: " + sourceName);
//				}
//			}
//		}
	}

	/**
	 * For each assignment this method is called to add an appropiate rule
	 * describing the dataflow of the assignment. The source service of the
	 * assignment is stored in sources to be used for execution plan generation.
	 * 
	 * @param source the source parameter for the assignment
	 * @param target the target parameter for the assignment
	 */
	public void addAssignmentDataflow(ParameterNode source, ParameterNode target) {

		// service to service assignment
		if (source.getParameter() instanceof ServiceOutput
				&& target.getParameter() instanceof ServiceInput) {
			addService2ServiceDataflow(source, target);
			// process input assignment
		} else if (source.getParameter() instanceof ProcessInput
				&& target.getParameter() instanceof ServiceInput) {
			addProcessInputDataflow(source, target);
			// process output assignment
		} else if (source.getParameter() instanceof ServiceOutput
				&& target.getParameter() instanceof ProcessOutput) {
			addProcessOutputDataflow(source, target);
		}
	}
	
	/**
	 * Generate rule for assignment from process input.
	 * 
	 * @param source the source parameter for the assignment
	 * @param target the target parameter for the assignment
	 */
	public void addProcessInputDataflow(ParameterNode source,
			ParameterNode target) {
		Logger.info("process input dataflow: " + source.getPropertyLabel()
				+ SWRLParser.IMP_CHAR + target.getPropertyLabel());
		Parameter targetParameter = target.getParameter();
        ServiceModel targetService = ((ServiceInput) targetParameter)
				.getService();
		ProcessModel process = Controller.getInstance().getProcess();

		// add rule describing dataflow from process input
		// define rule fromPath x, toPath y -> y has x
		// 1) from-path init
		String ruleName = "dataflow" + targetService.getName() + "Assignment"
				+ i++;
        String dataflowRuleFromInit0 = "owl:Thing(?copyAllIndividualsToJess)";
		String dataflowRuleFromInit1 = "anchor:Process(?" + process.getProcessName()
				+ ")";
		String dataflowRuleFromInit2 = "rdfs:label(?" + process.getProcessName()
				+ ", \"" + process.getProcessName() + "Process\")";
        String dataflowRuleFromInit3 = "anchor:hasInput(?" + process.getProcessName()
                + ", ?" + process.getProcessName() + "IN)";
//        String dataflowRuleFromInit3b = "anchor:Input(?" + process.getProcessName() + "IN)";
		String dataflowRuleFromInit4 = "rdfs:label(?" + process.getProcessName()
				+ "IN, \"" + source.getParameter().getName() + "\")";
		String dataflowRuleFromInit5 = "anchor:parameterObject(?" + process.getProcessName()
				+ "IN, ?X0)";
        String prefix = createPrefixForNamespace(targetParameter.getParameterTypeURI());
//        String dataflowRuleFromInit5b = prefix + ":" + targetParameter.getName() + "(?X0)";

		String fromPath = dataflowRuleFromInit0 
		        + SWRLParser.AND_CHAR + dataflowRuleFromInit1
        		+ SWRLParser.AND_CHAR + dataflowRuleFromInit2
        		+ SWRLParser.AND_CHAR + dataflowRuleFromInit3 
//        		+ SWRLParser.AND_CHAR + dataflowRuleFromInit3b 
                + SWRLParser.AND_CHAR + dataflowRuleFromInit4 
                + SWRLParser.AND_CHAR + dataflowRuleFromInit5; 
//        		+ SWRLParser.AND_CHAR + dataflowRuleFromInit5b;

		// 1) to-path init
		String dataflowRuleToInit1 = "anchor:Process(?" + targetService.getName()
				+ ")";
		
		String dataflowRuleToInit2 = "rdfs:label(?" + targetService.getName()
				+ ", \"" + targetService.getOwlsService().getName()
				+ "Process\")";
        String dataflowRuleToInit3 = "anchor:hasInput(?" + targetService.getName()
            + ", ?" + targetService.getName() + "IN)";
//        String dataflowRuleToInit3b = "anchor:Input(?" + targetService.getName() + "IN)";
		String dataflowRuleToInit4 = "rdfs:label(?" + targetService.getName()
				+ "IN, \"" + targetParameter.getName() + "\")";
		String toPath = "";
		String ruleHead = "";

		// 2) get to-path-nodes in right order
		Vector toPathParameterNodes = new Vector();
		ParameterNode toPathNodeIter = target;

		// target node is not input parameter root node
		if (toPathNodeIter.getParent() != null) {
			toPathNodeIter = toPathNodeIter.getParent();

			// ToPath start
			String dataflowRuleToInit5 = "anchor:parameterObject(?" + targetService.getName()
					+ "IN, ?Y0)";

			// ToPath rest
			while (toPathNodeIter.getParent() != null) {
				toPathParameterNodes.insertElementAt(toPathNodeIter, 0);
				toPathNodeIter = toPathNodeIter.getParent();
			}

			// 3) create to-path rule fragment
			toPath = SWRLParser.AND_CHAR + dataflowRuleToInit1 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit2 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit3 
//                    + SWRLParser.AND_CHAR + dataflowRuleToInit3b 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit4 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit5;

			Iterator iter2 = toPathParameterNodes.iterator();
			int k = 0;
			while (iter2.hasNext()) {
				ParameterNode node = (ParameterNode) iter2.next();
				URI parameterUri = node.getProperty().getURI();
                prefix = createPrefixForNamespace(parameterUri);
				toPath = toPath + SWRLParser.AND_CHAR 
				        + prefix + ":" + parameterUri.getFragment() + "(?Y" + k++ 
						+ ",?Y" + k
						+ ")";
			}

			// rule head
            URI parameterUri = target.getProperty().getURI();
            prefix = createPrefixForNamespace(parameterUri);
			ruleHead = prefix + ":" + parameterUri.getFragment() + "(?Y" + k + ",?X0)";

			// add rule
// backward
//			this.dataflowRules.add("\n[" + ruleName + ": " + ruleHead + " <- "
//					+ fromPath + toPath + ", print('eval rule: " + ruleName + "')]");
			
			this.dataflowRules.put(ruleName, fromPath + toPath + 
			            SWRLParser.IMP_CHAR  + ruleHead);

			// target node is input parameter root node
		} else {
		    
			toPath = SWRLParser.AND_CHAR + dataflowRuleToInit1 
			        + SWRLParser.AND_CHAR + dataflowRuleToInit2
					+ SWRLParser.AND_CHAR + dataflowRuleToInit3 
					+ SWRLParser.AND_CHAR + dataflowRuleToInit4;

			ruleHead = "anchor:parameterObject(?" + targetService.getName()
					+ "IN, ?X0)";

			this.dataflowRules.put(ruleName, fromPath + toPath
					+ SWRLParser.IMP_CHAR + ruleHead);
			// Logger.debug("[" + ruleName + ": " + fromPath + toPath + SWRLParser.IMP_CHAR
			// + ruleHead + ", print('eval rule: " + ruleName + "')]");

		}

		// set dataflow source container
		if (!this.sources.contains(process.getProcessName() + "Input")) {
			this.sources.add(process.getProcessName() + "Input");
		}
	}
	
	/**
	 * Creates a prefix/namespace mapping for the given URI and registers it.
	 * If the namespace is already registered, the prefix is returned, otherwise
	 * a new prefix is generated.
	 * The URI without fragment (#...) is considered to be the namespace.
	 * 
	 * @param propertyUri the URI to be analysed and registered
	 * 
	 * @return the registered prefix of the namespace
	 */
	private String createPrefixForNamespace(URI propertyUri) {
        String propertyUriString = propertyUri.toString();
        int endPos = propertyUriString.indexOf("#");
        String namespace = propertyUriString.substring(0, endPos + 1);
        
        // check if namespace is already registered
        if (this.registeredPrefixes.containsKey(namespace)) {
            return (String)this.registeredPrefixes.get(namespace);
        }

        // create new prefix and register the namespace
        String newPrefix = "myPrefix" + this.registeredPrefixes.size();
        this.registeredPrefixes.put(namespace, newPrefix);
        
        Logger.info("Registered namespace " + namespace + " with prefix " + newPrefix);
        
        return newPrefix;
    }

    /**
	 * Generate rule for assignment to process output.
	 * 
	 * @param source the source parameter for the assignment
	 * @param target the target parameter for the assignment
	 */
	public void addProcessOutputDataflow(ParameterNode source,
			ParameterNode target) {

		// add rule describing dataflow to process output
		ServiceModel sourceService = ((ServiceOutput) source.getParameter())
				.getService();
		ProcessModel process = Controller.getInstance().getProcess();

		// define rule fromPath x, toPath y -> y has x
		// 1) from-path init
		String ruleName = "OutputDataflowAssignment" + i++;
        String dataflowRuleFromInit0 = "owl:Thing(?copyAllIndividualsToJess)";
		String dataflowRuleFromInit1 = "anchor:Process(?" + sourceService.getName() + ")";
		String dataflowRuleFromInit2 = "rdfs:label(?" + sourceService.getName()
				+ ", \"" + sourceService.getOwlsService().getName()
				+ "Process\")";
		String dataflowRuleFromInit3 = "anchor:hasOutput(?" + sourceService.getName()
				+ ", ?" + sourceService.getName() + "OUT)";
		String dataflowRuleFromInit4 = "rdfs:label(?" + sourceService.getName()
				+ "OUT, \"" + source.getParameter().getName() + "\")";
		String dataflowRuleFromInit5 = "anchor:parameterObject(?" + sourceService.getName()
				+ "OUT, ?X0)";

		// 2) get from-path-nodes in right order
		Vector fromPathParameterNodes = new Vector();
		ParameterNode nodeIter = source;
		while (nodeIter.getParent() != null) {
			fromPathParameterNodes.insertElementAt(nodeIter, 0);
			nodeIter = nodeIter.getParent();
		}

		// 3) create from-path rule fragment
		// init
		String fromPath = dataflowRuleFromInit0 
		        + SWRLParser.AND_CHAR + dataflowRuleFromInit1 
		        + SWRLParser.AND_CHAR + dataflowRuleFromInit2
				+ SWRLParser.AND_CHAR + dataflowRuleFromInit3 
				+ SWRLParser.AND_CHAR + dataflowRuleFromInit4
				+ SWRLParser.AND_CHAR + dataflowRuleFromInit5;

		// rest
		Iterator iter1 = fromPathParameterNodes.iterator();
		int fromIndex = 0;
		while (iter1.hasNext()) {
			ParameterNode node = (ParameterNode) iter1.next();
            URI parameterUri = node.getProperty().getURI();
            String prefix = createPrefixForNamespace(parameterUri);
			fromPath = fromPath + SWRLParser.AND_CHAR + prefix + ":" 
			        + parameterUri.getFragment() + "(?X" + fromIndex++ 
			        + ",?X"	+ fromIndex + ")";
		}

		// set toPath
		String dataflowRuleToInit1 = "anchor:Process(?" + process.getProcessName() + ")";
		String dataflowRuleToInit2 = "rdfs:label(?" + process.getProcessName()
				+ ", \"" + process.getProcessName() + "Process\")";
		String dataflowRuleToInit3 = "anchor:hasOutput(?" + process.getProcessName()
				+ ", ?" + process.getProcessName() + "OUT)";
		String dataflowRuleToInit4 = "rdfs:label(?" + process.getProcessName()
				+ "OUT, \"" + target.getParameter().getName() + "\")";

		String toPath = dataflowRuleToInit1 
		        + SWRLParser.AND_CHAR + dataflowRuleToInit2 
		        + SWRLParser.AND_CHAR + dataflowRuleToInit3 
		        + SWRLParser.AND_CHAR + dataflowRuleToInit4;

		String ruleHead = "anchor:parameterObject(?" + process.getProcessName()
				+ "OUT, ?X" + fromIndex + ")";

		// add rule
		this.dataflowRules.put(ruleName, fromPath + SWRLParser.AND_CHAR
				+ toPath + SWRLParser.IMP_CHAR + ruleHead);
		// Logger.debug("[" + ruleName + ": " + fromPath + toPath + SWRLParser.IMP_CHAR
		// + ruleHead + ", print('eval rule: " + ruleName + "')]");
		
		this.addDataflowSources(sourceService);
	}

	/**
	 * Writes dataflow rules to a file.
	 * 
	 * @return file with dataflow descriptions
	 */
	public File serializeDataflows() {
		this.dataflowFile = new File("temp/"+Controller.getInstance().getProcess()
		        .getProcessName()+"/"+this.getDataflowFilename());
    
        JenaOWLModel dataflowModel = null;
        try {
            dataflowModel = ProtegeOWL.createJenaOWLModel();

            NamespaceManager namespaceManager = dataflowModel.getNamespaceManager();
            namespaceManager.setDefaultNamespace("http://process.dataflow." 
                    + this.getDataflowFilename() + "#");
            namespaceManager.setPrefix("http://www.w3.org/2003/11/swrl#", "swrl");        
            namespaceManager.setPrefix("http://swrl.stanford.edu/ontologies/3.3/swrla.owl#", 
                    "swrla");        
            namespaceManager.setPrefix(
                    "http://swrl.stanford.edu/ontologies/built-ins/3.3/swrlx.owl#", "swrlx");        
            namespaceManager.setPrefix("http://localhost:8080/ontologies/ServiceAnchor.owl#", 
                    "anchor");        
            namespaceManager.setPrefix("http://protege.stanford.edu/plugins/owl/protege#", 
                    "protege");        
           
            ImportHelper importHelper = new ImportHelper(dataflowModel);
            URI importUri = URIUtilities.createURI(
                    "http://swrl.stanford.edu/ontologies/3.3/swrla.owl");
            importHelper.addImport(importUri);
            importUri = URIUtilities.createURI(
                    "http://swrl.stanford.edu/ontologies/built-ins/3.3/swrlx.owl");
            importHelper.addImport(importUri);
            importUri = URIUtilities.createURI(
                    "http://localhost:8080/ontologies/ServiceAnchor.owl");
            importHelper.addImport(importUri);
            
            Iterator namespaceIt = this.registeredPrefixes.keySet().iterator();
            while (namespaceIt.hasNext()) {
                String namespace = (String) namespaceIt.next();                
                namespaceManager.setPrefix(namespace, 
                        (String) this.registeredPrefixes.get(namespace));        

                namespace = namespace.substring(0, namespace.indexOf("#"));
                importHelper.addImport(new URI(namespace));
            }
            
            importHelper.importOntologies(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.error("Error creating dataflow ontology for " + this.getDataflowFilename());
        }


        String ruleName = null;
        String ruleSwrlString = null;
		try {
	        SWRLFactory factory = new SWRLFactory(dataflowModel);
            
            // write dataflow rules
            Iterator createIt = createRules.keySet().iterator();
            while (createIt.hasNext()) {
                ruleName = (String) createIt.next();
                ruleSwrlString = (String) createRules.get(ruleName);
                factory.createImp(ruleName, ruleSwrlString);
            }
            
            // write dataflow rules
            Iterator ruleIt = dataflowRules.keySet().iterator();
            while (ruleIt.hasNext()) {
                ruleName = (String) ruleIt.next();
                ruleSwrlString = (String) dataflowRules.get(ruleName);
                factory.createImp(ruleName, ruleSwrlString);
            }
		} 
		catch (Exception ex) {
            ex.printStackTrace();
            ruleSwrlString = ruleSwrlString.replaceAll("" + SWRLParser.AND_CHAR, " ^ "); 
            ruleSwrlString = ruleSwrlString.replaceAll("" + SWRLParser.IMP_CHAR, " --> "); 
			Logger.error("Error creating rule: " + ruleName + "=" + ruleSwrlString);
		}
		
        try {
            String ontologyName = dataflowModel.getDefaultOWLOntology().getNamespace();
            JenaOWLModel.save(dataflowFile, dataflowModel.getOntModel(), 
                    FileUtils.langXMLAbbrev, ontologyName);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.error("Error writing dataflow ontology for " + this.getDataflowFilename());
        }

		return dataflowFile;
	}

	
	/**
	 * Get dataflow source containers for execution plan.
	 * 
	 * @return Vector of source container names.
	 */
	public Vector getSources() {
		return sources;
	}

    /**
     * Get the name of the data flow serialisation file.
     * 
     * @return the filename of the dataflow
     */
	public String getDataflowFilename() {
		return dataflowFilename;
	}


    /**
     * Deletes already generated rules.
     * This is necessary, if a user selects "Deploy PRocess" twice.
     * Otherwise the rules are duplicated.
     * 
     */
    public void resetDataflows() {
        this.dataflowRules = new TreeMap();
    }
    
    /**
     * Deletes already generated rules.
     * This is necessary, if a user selects "Deploy PRocess" twice.
     * Otherwise the rules are duplicated.
     * 
     */
    public void resetCreates() {
        this.createRules = new TreeMap();
    }

    /**
     * Get the data flow serialisation file.
     * 
     * @return the dataflow file
     */
    public File getDataflowFile() {
        return dataflowFile;
    }

    /**
     * Set the file where the data flow is serialised into.
     * 
     * @param dataflowFile the dataflow file
     */
    public void setDataflowFile(File dataflowFile) {
        this.dataflowFile = dataflowFile;
    }

    /**
     * Get the registered prefixes and their namespaces.
     * 
     * @return the registered prefixes
     */
    public TreeMap getRegisteredPrefixes() {
        return registeredPrefixes;
    }
}
