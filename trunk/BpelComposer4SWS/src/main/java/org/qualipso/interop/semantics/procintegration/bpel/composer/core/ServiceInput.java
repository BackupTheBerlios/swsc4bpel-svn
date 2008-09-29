package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import java.net.URI;

/**
 * Represents an input parameter of a service.
 *
 */
public class ServiceInput extends Parameter{
	
	private ServiceModel service;
	
	public ServiceInput(ServiceModel service, String name,URI inputTypeURI){
		super(name, inputTypeURI);
		this.service = service;
		
	}

	public ServiceModel getService() {
		return service;
	}
}
