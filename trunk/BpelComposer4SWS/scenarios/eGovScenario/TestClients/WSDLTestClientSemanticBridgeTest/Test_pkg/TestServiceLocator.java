/**
 * TestServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package Test_pkg;

public class TestServiceLocator extends org.apache.axis.client.Service implements Test_pkg.TestService {

    // Use to get a proxy class for Test
    private final java.lang.String Test_address = "http://localhost:8080/PublicServices/services/SemanticBridgeTest";

    public java.lang.String getTestAddress() {
        return Test_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TestWSDDServiceName = "Test";

    public java.lang.String getTestWSDDServiceName() {
        return TestWSDDServiceName;
    }

    public void setTestWSDDServiceName(java.lang.String name) {
        TestWSDDServiceName = name;
    }

    public Test_pkg.Test getTest() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Test_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTest(endpoint);
    }

    public Test_pkg.Test getTest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            Test_pkg.TestSoapBindingStub _stub = new Test_pkg.TestSoapBindingStub(portAddress, this);
            _stub.setPortName(getTestWSDDServiceName());
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
            if (Test_pkg.Test.class.isAssignableFrom(serviceEndpointInterface)) {
                Test_pkg.TestSoapBindingStub _stub = new Test_pkg.TestSoapBindingStub(new java.net.URL(Test_address), this);
                _stub.setPortName(getTestWSDDServiceName());
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
        if ("Test".equals(inputPortName)) {
            return getTest();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:Test", "TestService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("Test"));
        }
        return ports.iterator();
    }

}
