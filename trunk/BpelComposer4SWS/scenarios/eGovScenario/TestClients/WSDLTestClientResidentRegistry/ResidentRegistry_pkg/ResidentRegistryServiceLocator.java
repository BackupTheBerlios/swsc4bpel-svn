/**
 * ResidentRegistryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package ResidentRegistry_pkg;

public class ResidentRegistryServiceLocator extends org.apache.axis.client.Service implements ResidentRegistry_pkg.ResidentRegistryService {

    // Use to get a proxy class for ResidentRegistry
    private final java.lang.String ResidentRegistry_address = "http://localhost:8080/PublicServices/services/ResidentRegistry";

    public java.lang.String getResidentRegistryAddress() {
        return ResidentRegistry_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ResidentRegistryWSDDServiceName = "ResidentRegistry";

    public java.lang.String getResidentRegistryWSDDServiceName() {
        return ResidentRegistryWSDDServiceName;
    }

    public void setResidentRegistryWSDDServiceName(java.lang.String name) {
        ResidentRegistryWSDDServiceName = name;
    }

    public ResidentRegistry_pkg.ResidentRegistry getResidentRegistry() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ResidentRegistry_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getResidentRegistry(endpoint);
    }

    public ResidentRegistry_pkg.ResidentRegistry getResidentRegistry(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ResidentRegistry_pkg.ResidentRegistrySoapBindingStub _stub = new ResidentRegistry_pkg.ResidentRegistrySoapBindingStub(portAddress, this);
            _stub.setPortName(getResidentRegistryWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ResidentRegistry_pkg.ResidentRegistry.class.isAssignableFrom(serviceEndpointInterface)) {
                ResidentRegistry_pkg.ResidentRegistrySoapBindingStub _stub = new ResidentRegistry_pkg.ResidentRegistrySoapBindingStub(new java.net.URL(ResidentRegistry_address), this);
                _stub.setPortName(getResidentRegistryWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("ResidentRegistry".equals(inputPortName)) {
            return getResidentRegistry();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ResidentRegistry", "ResidentRegistryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("ResidentRegistry"));
        }
        return ports.iterator();
    }

}
