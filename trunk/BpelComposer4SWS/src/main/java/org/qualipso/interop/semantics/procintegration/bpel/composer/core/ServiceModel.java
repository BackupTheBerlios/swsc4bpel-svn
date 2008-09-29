package org.qualipso.interop.semantics.procintegration.bpel.composer.core;


import java.net.URI;
import java.util.Iterator;
import java.util.Vector;

import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.InputList;
import org.mindswap.owls.service.Service;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;

/**
 * Represents a service involved within the composition.
 * 
 */
public class ServiceModel {
	
	// name of the service given from user in composition
	private String name;
	
	// owl-s description of service
	private String owls;
	
	// OWL-S API service representation
	private Service owlsService;
	
	private Vector inputs;

	// output parameter holder
	private Vector outputs;
	
	// stores applied Semantic Bridges
	private Vector semanticBridges;

	// services of composition which are executed before this service
	private Vector priorServices;
	
	// dataflow to service inputs (pull-mode)
	private Dataflow dataflow;

	/**
	 * Read owls service description and create input and output parameter
	 * holder in tree structure.
	 * 
	 * @param name
	 * @param owls
	 */
	public ServiceModel(String name, String owls) throws Exception {

		// init attributes
		this.name = name;
		this.owls = owls;
		this.inputs = new Vector();
		this.outputs = new Vector();
		this.semanticBridges = new Vector();
		this.dataflow = new Dataflow(name+"Dataflow");
		this.priorServices = new Vector();

			long t1 = System.currentTimeMillis();

			// read service description
			MessagePanel.appendOut("    Reading service description ...");
			OWLKnowledgeBase kb = OWLFactory.createKB();
			this.owlsService = kb.readService(owls);
			MessagePanel.appendSuccessln(" done.");

			// time logging
			long t2 = System.currentTimeMillis();
			Logger.info("ReadServiceDescriptionTimeConsumption for "
					+ this.name + ": " + (t2 - t1));

			// get inputs
			InputList inputs = owlsService.getProcess().getInputs();
			for (int i = 0; i < inputs.size(); i++) {
				Input input = inputs.inputAt(i);

				URI inputType = input.getParamType().getURI();
				String inputName = input.getLabel();
				MessagePanel.appendOut("    Extracting input parameter ");
				MessagePanel.appendOutBlue(inputName);
				MessagePanel.appendOut(" type of ");
				MessagePanel.appendOutBlue(inputType.toString());
				MessagePanel.appendOutln("");

				// set inputs
				MessagePanel.appendOut("    Generating parameter tree for ");
				MessagePanel.appendOutBlue(inputName);
				MessagePanel.appendOut(" ...");
				this.inputs.add(new ServiceInput(this,inputName, inputType));
				MessagePanel.appendSuccessln(" done.");
			}
			
			// get outputs
			URI outputType = owlsService.getProcess().getOutput().getParamType()
					.getURI();
			String outputName = owlsService.getProcess().getOutput().getLabel();
			MessagePanel.appendOut("    Extracting output parameter ");
			MessagePanel.appendOutBlue(outputName);
			MessagePanel.appendOut(" type of ");
			MessagePanel.appendOutBlue(outputType.toString());
			MessagePanel.appendOutln("");
			// set outputs
			MessagePanel.appendOut("    Generating parameter tree for ");
			MessagePanel.appendOutBlue(outputName);
			MessagePanel.appendOut(" ...");
			
			this.outputs.add(new ServiceOutput(this, outputName, outputType));
			

		
	}
	
	/**
	 * Apply Semantic Bridge rules to service output parameter, refresh
	 * classification and rebuild parameter node tree.
	 * @param semanticBridge
	 */
	public void applySemanticBridge(SemanticBridge semanticBridge){
		this.semanticBridges.add(semanticBridge);
		Iterator outputIter = this.outputs.iterator();
		while(outputIter.hasNext()){		
			ServiceOutput output = (ServiceOutput) outputIter.next();
			
			MessagePanel.appendOut("    Applying semantic bridge to ");
			MessagePanel.appendOut("output parameter ");
			MessagePanel.appendOutBlue(output.getName());
			MessagePanel.appendOut(" ... ");
			
			// apply semantic bridge to output
			semanticBridge.applyToServiceOutput(output);
		}
		
	}

	public Vector getPriorServices() {
		return priorServices;
	}

	public void setPriorServices(Vector priorServices) {
		this.priorServices = priorServices;
	}

	public String getName() {
		return name;
	}

	public Vector getInputs() {
		return inputs;
	}

	public Vector getOutputs() {
		return outputs;
	}

	public Service getOwlsService() {
		return owlsService;
	}

	public String getOwls() {
		return owls;
	}

	public void setOwls(String owls) {
		this.owls = owls;
	}

	public Vector getSemanticBridges() {
		return semanticBridges;
	}

	public Dataflow getDataflow() {
		return dataflow;
	}

}
