/**
 * StatisticalOfficeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package StatisticalOffice_pkg;

public class StatisticalOfficeServiceLocator extends org.apache.axis.client.Service implements StatisticalOffice_pkg.StatisticalOfficeService {

    // Use to get a proxy class for StatisticalOffice
    private final java.lang.String StatisticalOffice_address = "http://localhost:8080/PublicServices/services/StatisticalOffice";

    public java.lang.String getStatisticalOfficeAddress() {
        return StatisticalOffice_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String StatisticalOfficeWSDDServiceName = "StatisticalOffice";

    public java.lang.String getStatisticalOfficeWSDDServiceName() {
        return StatisticalOfficeWSDDServiceName;
    }

    public void setStatisticalOfficeWSDDServiceName(java.lang.String name) {
        StatisticalOfficeWSDDServiceName = name;
    }

    public StatisticalOffice_pkg.StatisticalOffice getStatisticalOffice() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(StatisticalOffice_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getStatisticalOffice(endpoint);
    }

    public StatisticalOffice_pkg.StatisticalOffice getStatisticalOffice(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            StatisticalOffice_pkg.StatisticalOfficeSoapBindingStub _stub = new StatisticalOffice_pkg.StatisticalOfficeSoapBindingStub(portAddress, this);
            _stub.setPortName(getStatisticalOfficeWSDDServiceName());
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
            if (StatisticalOffice_pkg.StatisticalOffice.class.isAssignableFrom(serviceEndpointInterface)) {
                StatisticalOffice_pkg.StatisticalOfficeSoapBindingStub _stub = new StatisticalOffice_pkg.StatisticalOfficeSoapBindingStub(new java.net.URL(StatisticalOffice_address), this);
                _stub.setPortName(getStatisticalOfficeWSDDServiceName());
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
        if ("StatisticalOffice".equals(inputPortName)) {
            return getStatisticalOffice();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:StatisticalOffice", "StatisticalOfficeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("StatisticalOffice"));
        }
        return ports.iterator();
    }

}
