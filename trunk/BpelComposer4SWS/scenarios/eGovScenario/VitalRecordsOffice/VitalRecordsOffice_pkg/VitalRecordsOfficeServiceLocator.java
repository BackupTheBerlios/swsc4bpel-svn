/**
 * VitalRecordsOfficeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package VitalRecordsOffice_pkg;

public class VitalRecordsOfficeServiceLocator extends org.apache.axis.client.Service implements VitalRecordsOffice_pkg.VitalRecordsOfficeService {

    // Use to get a proxy class for VitalRecordsOffice
    private final java.lang.String VitalRecordsOffice_address = "http://localhost:8080/PublicServices/VitalRecordsOffice";

    public java.lang.String getVitalRecordsOfficeAddress() {
        return VitalRecordsOffice_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VitalRecordsOfficeWSDDServiceName = "VitalRecordsOffice";

    public java.lang.String getVitalRecordsOfficeWSDDServiceName() {
        return VitalRecordsOfficeWSDDServiceName;
    }

    public void setVitalRecordsOfficeWSDDServiceName(java.lang.String name) {
        VitalRecordsOfficeWSDDServiceName = name;
    }

    public VitalRecordsOffice_pkg.VitalRecordsOffice getVitalRecordsOffice() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VitalRecordsOffice_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVitalRecordsOffice(endpoint);
    }

    public VitalRecordsOffice_pkg.VitalRecordsOffice getVitalRecordsOffice(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            VitalRecordsOffice_pkg.VitalRecordsOfficeSoapBindingStub _stub = new VitalRecordsOffice_pkg.VitalRecordsOfficeSoapBindingStub(portAddress, this);
            _stub.setPortName(getVitalRecordsOfficeWSDDServiceName());
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
            if (VitalRecordsOffice_pkg.VitalRecordsOffice.class.isAssignableFrom(serviceEndpointInterface)) {
                VitalRecordsOffice_pkg.VitalRecordsOfficeSoapBindingStub _stub = new VitalRecordsOffice_pkg.VitalRecordsOfficeSoapBindingStub(new java.net.URL(VitalRecordsOffice_address), this);
                _stub.setPortName(getVitalRecordsOfficeWSDDServiceName());
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
        if ("VitalRecordsOffice".equals(inputPortName)) {
            return getVitalRecordsOffice();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:VitalRecordsOffice", "VitalRecordsOfficeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("VitalRecordsOffice"));
        }
        return ports.iterator();
    }

}
