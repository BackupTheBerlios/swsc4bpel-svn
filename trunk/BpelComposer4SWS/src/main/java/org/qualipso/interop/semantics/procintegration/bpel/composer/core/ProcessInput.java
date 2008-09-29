package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLIndividual;

/**
 * Process Input represents an input parameter for the composed process.
 *
 */
public class ProcessInput extends Parameter {

	public ProcessInput(String name,ParameterNode serviceInputParameterNode) {
		super();
		
		// set  attributes
		this.name = name;
		if (serviceInputParameterNode.getValue().isDataValue()) {
			this.parameterTypeURI = ((OWLDataValue)serviceInputParameterNode.getValue()).getDatatypeURI();
		}else{
			this.parameterTypeURI = ((OWLIndividual)serviceInputParameterNode.getValue()).getType().getURI();
			this.kb = ((OWLIndividual)serviceInputParameterNode.getValue()).getKB();
		}
		this.root = copyRootParameterNode(serviceInputParameterNode);
		
		// set assignment relations
		serviceInputParameterNode.setAssignmentSourceParameterNode(root);
		this.root.addAssignmentTargetParameterNode(serviceInputParameterNode);
		
		
		
		
	}
	
	
	

}
