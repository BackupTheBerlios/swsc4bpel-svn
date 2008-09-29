/**
 * LookyBookServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.dyndns.dinoch.webservices.books;

public class LookyBookServiceLocator extends org.apache.axis.client.Service implements org.dyndns.dinoch.webservices.books.LookyBookService {

    // Use to get a proxy class for LookyBookServiceSoap
    private final java.lang.String LookyBookServiceSoap_address = "http://cheeso.members.winisp.net/books/books.asmx";

    public java.lang.String getLookyBookServiceSoapAddress() {
        return LookyBookServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LookyBookServiceSoapWSDDServiceName = "LookyBookServiceSoap";

    public java.lang.String getLookyBookServiceSoapWSDDServiceName() {
        return LookyBookServiceSoapWSDDServiceName;
    }

    public void setLookyBookServiceSoapWSDDServiceName(java.lang.String name) {
        LookyBookServiceSoapWSDDServiceName = name;
    }

    public org.dyndns.dinoch.webservices.books.LookyBookServiceSoap getLookyBookServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LookyBookServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLookyBookServiceSoap(endpoint);
    }

    public org.dyndns.dinoch.webservices.books.LookyBookServiceSoap getLookyBookServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.dyndns.dinoch.webservices.books.LookyBookServiceSoapStub _stub = new org.dyndns.dinoch.webservices.books.LookyBookServiceSoapStub(portAddress, this);
            _stub.setPortName(getLookyBookServiceSoapWSDDServiceName());
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
            if (org.dyndns.dinoch.webservices.books.LookyBookServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.dyndns.dinoch.webservices.books.LookyBookServiceSoapStub _stub = new org.dyndns.dinoch.webservices.books.LookyBookServiceSoapStub(new java.net.URL(LookyBookServiceSoap_address), this);
                _stub.setPortName(getLookyBookServiceSoapWSDDServiceName());
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
        if ("LookyBookServiceSoap".equals(inputPortName)) {
            return getLookyBookServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "LookyBookService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("LookyBookServiceSoap"));
        }
        return ports.iterator();
    }

}
