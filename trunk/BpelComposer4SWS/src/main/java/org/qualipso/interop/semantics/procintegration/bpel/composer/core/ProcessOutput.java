package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import java.net.URI;

/**
 * ProcessOutput represents an output parameter of the composed process.
 *
 */
public class ProcessOutput extends Parameter{

	public ProcessOutput(String name,ParameterNode serviceOutputParameterNode, URI directType) {
		super();
		
		// set  attributes
		this.name = name;
		this.root = copyRootParameterNode(serviceOutputParameterNode);
		this.parameterTypeURI = directType; //TODO: redundant
		this.root.setDirectType(directType);
		
		
		// set assignment relation
		this.root.setAssignmentSourceParameterNode(serviceOutputParameterNode);
		serviceOutputParameterNode.addAssignmentTargetParameterNode(this.root);
	}

}
