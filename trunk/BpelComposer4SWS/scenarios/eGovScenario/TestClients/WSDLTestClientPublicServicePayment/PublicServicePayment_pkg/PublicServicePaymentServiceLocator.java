/**
 * PublicServicePaymentServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package PublicServicePayment_pkg;

public class PublicServicePaymentServiceLocator extends org.apache.axis.client.Service implements PublicServicePayment_pkg.PublicServicePaymentService {

    // Use to get a proxy class for PublicServicePayment
    private final java.lang.String PublicServicePayment_address = "http://localhost:8080/PublicServices/services/PublicServicePayment";

    public java.lang.String getPublicServicePaymentAddress() {
        return PublicServicePayment_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PublicServicePaymentWSDDServiceName = "PublicServicePayment";

    public java.lang.String getPublicServicePaymentWSDDServiceName() {
        return PublicServicePaymentWSDDServiceName;
    }

    public void setPublicServicePaymentWSDDServiceName(java.lang.String name) {
        PublicServicePaymentWSDDServiceName = name;
    }

    public PublicServicePayment_pkg.PublicServicePayment getPublicServicePayment() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PublicServicePayment_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPublicServicePayment(endpoint);
    }

    public PublicServicePayment_pkg.PublicServicePayment getPublicServicePayment(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            PublicServicePayment_pkg.PublicServicePaymentSoapBindingStub _stub = new PublicServicePayment_pkg.PublicServicePaymentSoapBindingStub(portAddress, this);
            _stub.setPortName(getPublicServicePaymentWSDDServiceName());
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
            if (PublicServicePayment_pkg.PublicServicePayment.class.isAssignableFrom(serviceEndpointInterface)) {
                PublicServicePayment_pkg.PublicServicePaymentSoapBindingStub _stub = new PublicServicePayment_pkg.PublicServicePaymentSoapBindingStub(new java.net.URL(PublicServicePayment_address), this);
                _stub.setPortName(getPublicServicePaymentWSDDServiceName());
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
        if ("PublicServicePayment".equals(inputPortName)) {
            return getPublicServicePayment();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:PublicServicePayment", "PublicServicePaymentService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("PublicServicePayment"));
        }
        return ports.iterator();
    }

}
