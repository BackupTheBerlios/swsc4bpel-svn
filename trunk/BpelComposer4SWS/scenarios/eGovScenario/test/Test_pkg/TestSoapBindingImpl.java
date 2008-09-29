/**
 * TestSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package Test_pkg;

public class TestSoapBindingImpl implements Test_pkg.Test{
    public localhost.PublicServices.SemanticBridgeTest.XTest.Letter performTest(localhost.PublicServices.SemanticBridgeTest.XTest.Address in0) throws java.rmi.RemoteException {
      localhost.PublicServices.SemanticBridgeTest.XTest.Letter letter = new localhost.PublicServices.SemanticBridgeTest.XTest.Letter();
      letter.setTitle("The World Formula");
      letter.setContent(in0.getRecipient());
        return letter;
    }

}
