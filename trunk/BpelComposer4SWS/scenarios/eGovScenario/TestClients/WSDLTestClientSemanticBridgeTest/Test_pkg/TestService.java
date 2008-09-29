/**
 * TestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package Test_pkg;

public interface TestService extends javax.xml.rpc.Service {
    public java.lang.String getTestAddress();

    public Test_pkg.Test getTest() throws javax.xml.rpc.ServiceException;

    public Test_pkg.Test getTest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
