/**
 * CompositionTestSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package CompositionTest;


import java.util.*;


public class CompositionTestSoapBindingImpl implements CompositionTest.CompositionTestPortType{
    public java.lang.String compositionTest(java.lang.String residentSearchProfile) throws java.rmi.RemoteException {	
    	HashMap outputs = null;
    	try { 
    		HashMap inputs = new HashMap();
    		inputs.put("ResidentSearchProfile",residentSearchProfile);
    		execution.ExecutionEngine engine = new execution.ExecutionEngine();
    		String deploymentDirectory = "C:/Programme/Tomcat/webapps/PublicServices";
    		String processName = "CompositionTest";
    		outputs = engine.run(deploymentDirectory+"/"+processName,processName+".xml",inputs);

	} catch(Exception e) {
			System.err.println("ExecutionEngineError");
			e.printStackTrace();
    	}
        return (String)outputs.get("OutputLetter");
    }

}
