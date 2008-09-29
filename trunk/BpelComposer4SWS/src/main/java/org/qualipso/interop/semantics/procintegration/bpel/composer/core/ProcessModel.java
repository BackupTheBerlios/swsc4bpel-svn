package org.qualipso.interop.semantics.procintegration.bpel.composer.core;


import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.mindswap.owl.OWLOntology;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;


/**
 * Represents a composite process consisting of a sequence of Semantic Web services,
 * semantic bridges between them and dataflow descriptions between them.
 * 
 */
public class ProcessModel {
	
	// name of composite process
	private String processName;
	
	// sequence of services building the composite process
	private Vector services;
	
	// stores loaded Semantic Bridges
	private Vector semanticBridges;
	
	// composite input of process
	private Vector processInputs;

	// composite output of process
	private Vector processOutputs;
	
	// dataflow from service outputs to composite outputs of process
	private Dataflow outputDataflow;
	
	// counter used for creating default names
	private int processInputCount = 1;
	private int processOutputCount = 1;
	private int serviceCount = 1;
	
	public ProcessModel(String processName) {
		this.processName = processName;
		this.services = new Vector();
		this.semanticBridges = new Vector();
		this.processInputs = new Vector();
		this.processOutputs = new Vector();
		this.outputDataflow = new Dataflow("OutputDataflow");
	}

	/**
	 * Add service to service sequence.
	 * 
	 * @param service
	 */
	public void addService(ServiceModel service) {
		
		// add currently involved services into prior services
		// collection of new service.
		service.setPriorServices((Vector) services.clone());
		this.services.add(service);
		this.serviceCount++;
		
		// check if already loaded semantic bridge needs to be applied to service outputs
		if (semanticBridges.size()<1) return;
		MessagePanel.appendOut("    Checking if loaded Semantic Bridge needs to be applied to service outputs ... ");
		Iterator semanticBridgeIter = semanticBridges.iterator();
		boolean match = false;
		while(semanticBridgeIter.hasNext()){
			SemanticBridge semanticBridge = (SemanticBridge)semanticBridgeIter.next();
			
			// check import ontologies of service
			Set serviceImportOntologies = service.getOwlsService().getOntology().getImports();
			Iterator serviceImportOntologiesIter = serviceImportOntologies.iterator();
			
			while(serviceImportOntologiesIter.hasNext()){
				OWLOntology serviceImportOntology = (OWLOntology)serviceImportOntologiesIter.next();
				
				// match
				if(semanticBridge.getSourceOntology().equals(serviceImportOntology.getURI().toString())){
					
					match = true;
					
					MessagePanel.appendOut("\n    Found Semantic Bridge ");
					MessagePanel.appendOutBlue(semanticBridge.getSemanticBridgeURL());
					MessagePanel.appendOut(" matching ");
					MessagePanel.appendOutBlue(service.getName());
					MessagePanel.appendOutln(" output parameter ontology.");
					
					service.applySemanticBridge(semanticBridge);
				}
				
			}
			
			
		}
		
		if(!match){
			MessagePanel.appendOut("no matching Semantic Bridge ... ");
			MessagePanel.appendSuccessln("done.");
		}		
	}
	
	/**
	 * Add Semantic Bridge to loaded Semantic Brdige store.
	 * @param semanticBridge
	 */
	public ServiceModel addSemanticBridge(SemanticBridge semanticBridge){
		this.semanticBridges.add(semanticBridge);
		
		// apply semanticBridge to services if semanticBridge source ontology == import ontolgy of service
		Iterator serviceIter = services.iterator();
		
		// one service semantic bridge applied to 
		ServiceModel matchingService = null;
		
		MessagePanel.appendOutln("    Searching service outputs matching semantic bridge source ontology ...");
		
		while(serviceIter.hasNext()){
			ServiceModel service = (ServiceModel) serviceIter.next();
			
			// check import ontologies of service
			Set serviceImportOntologies = service.getOwlsService().getOntology().getImports();
			Iterator serviceImportOntologiesIter = serviceImportOntologies.iterator();
			while(serviceImportOntologiesIter.hasNext()){
				OWLOntology serviceImportOntology = (OWLOntology)serviceImportOntologiesIter.next();
				
				// match
				if(semanticBridge.getSourceOntology().equals(serviceImportOntology.getURI().toString())){
					
					MessagePanel.appendOut("    Found matching service ");
					MessagePanel.appendOutBlue(service.getName());
					MessagePanel.appendOutln(":");
					
					service.applySemanticBridge(semanticBridge);
					matchingService = service;
				}
			}
		}
		
		return matchingService;
		
	}

	/**
	 * Get all currently involved services of sequence.
	 * 
	 * @return Vector of services
	 */
	public Vector getServices() {
		return services;
	}


	public String getProcessName() {
		return processName;
	}
	
	public ServiceModel getServiceByName(String name){
		Iterator iter = this.services.iterator();
		while(iter.hasNext()){
			ServiceModel service = (ServiceModel)iter.next();
			if(service.getName().equals(name)) return service;
		}
		Logger.error("Service "+name+" is not contained in composite process!");
		return null;
	}

	public Vector getProcessInputs() {
		return processInputs;
	}

	public Vector getProcessOutputs() {
		return processOutputs;
	}
	
	public void addProcessInput(ProcessInput input){
		this.processInputs.add(input);
		this.processInputCount++;
	}
	
	public void addProcessOutput(ProcessOutput output){
		this.processOutputs.add(output);
		this.processOutputCount++;
	}
	
	public void removeProcessInput(String inputName){
		Iterator iter = this.processInputs.iterator();
		while(iter.hasNext()){
			ProcessInput processInput = (ProcessInput)iter.next();
			if(processInput.getName().equals(inputName)){
				this.processInputs.remove(processInput);
		        this.processInputCount--;
				break;
			}
		}
	}
	
	public void removeProcessOutput(String outputName){
		Iterator iter = this.processOutputs.iterator();
		while(iter.hasNext()){
			ProcessOutput processOutput = (ProcessOutput)iter.next();
			if(processOutput.getName().equals(outputName)){
				this.processOutputs.remove(processOutput);
		        this.processOutputCount--;
				break;
			}
		}
	}

	public int getProcessInputCount() {
		return processInputCount;
	}

	public int getProcessOutputCount() {
		return processOutputCount;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public Vector getSemanticBridges() {
		return semanticBridges;
	}

	public Dataflow getOutputDataflow() {
		return outputDataflow;
	}

}
