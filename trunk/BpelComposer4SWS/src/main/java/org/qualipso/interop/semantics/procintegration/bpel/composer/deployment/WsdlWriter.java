package org.qualipso.interop.semantics.procintegration.bpel.composer.deployment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Vector;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessInput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessOutput;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;

/**
 * Generates a WSDL file for the specified process.
 * 
 */
public class WsdlWriter {

    /**
     * Simple method to write a WSDL file 
     * according to the defined process inputs and outputs.
     * 
     * @return the WSDL file
     * 
     * @throws Exception
     */
    public File generateWSDL() {
        ProcessModel process = Controller.getInstance().getProcess();
        String processName = process.getProcessName();
        String parameterOrder = " parameterOrder=\"";
        String processTypes = "<wsdl:types><xsd:schema targetNamespace=\"urn:processTypes\">";
        Vector registeredProcessTypes = new Vector();
        
        MessagePanel.appendOut("    Generating WSDL ");
        MessagePanel.appendOutBlue(processName + ".wsdl");
        MessagePanel.appendOut(" ... ");

        String content = 
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" 
        + "<wsdl:definitions xmlns:processTypes=\"urn:processTypes\" targetNamespace=\"urn:" 
        + processName + "_extWsdlURN\" xmlns:impl=\"urn:" + processName 
        + "_extWsdlURN\" xmlns:wsdlsoap=\"http://schemas.xmlsoap.org/wsdl/soap/\" " 
        + "xmlns:apachesoap=\"http://xml.apache.org/xml-soap\" " 
        + "xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" "
        + "xmlns:plnk=\"http://schemas.xmlsoap.org/ws/2003/05/partner-link/\" " 
        + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " 
        + "xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\">"
        + "<plnk:partnerLinkType xmlns:plnk=\"http://docs.oasis-open.org/wsbpel/2.0/plnktype\" " 
        + "name=\"" + processName + "_PartnerLinkType\">" 
        + "   <plnk:role name=\"serviceProvider\" portType=\"impl:" 
        + processName + "_PortType\"/>" 
        + "</plnk:partnerLinkType>" 
        + "  <wsdl:message name=\"processInputMessage\">";

        // add inputs
        Iterator inputIter = process.getProcessInputs().iterator();
        while (inputIter.hasNext()) {
            ProcessInput processInput = (ProcessInput) inputIter.next();
            String processInputName = processInput.getName();
            content        += "    <wsdl:part name=\"" + processInputName 
                + "\" type=\"processTypes:" + processInputName + "\"/>";
            parameterOrder += processInputName + " ";
            if (!registeredProcessTypes.contains(processInputName)) {
                processTypes   += "<xsd:complexType name=\"" + processInputName 
                    + "\">  <xsd:sequence>  <xsd:element name=\"" + processInputName 
                    + "Data\" type=\"xsd:string\" nillable=\"false\"/> " 
                    + " </xsd:sequence>  </xsd:complexType>";
                registeredProcessTypes.add(processInputName);
            }
        }
        
        content += "  </wsdl:message>";

        if (process.getProcessOutputs().size() > 0) {
            content += "  <wsdl:message name=\"processOutputMessage\">";

            // add outputs
            Iterator outputIter = process.getProcessOutputs().iterator();
            while (outputIter.hasNext()) {
                ProcessOutput processOutput = (ProcessOutput) outputIter.next();
                String processOutputName = processOutput.getName();
                content        += "    <wsdl:part name=\"" + processOutputName 
                    + "\" type=\"processTypes:" + processOutputName + "\"/>";
                parameterOrder += processOutputName + " ";
                if (!registeredProcessTypes.contains(processOutputName))
                {
                    processTypes   += "<xsd:complexType name=\"" + processOutputName 
                        + "\">  <xsd:sequence>  <xsd:element name=\"" + processOutputName 
                        + "Data\" type=\"xsd:string\" nillable=\"false\"/> " 
                        + " </xsd:sequence>  </xsd:complexType>";
                    registeredProcessTypes.add(processOutputName);
                }
            }
            
            content +=        
            "  </wsdl:message>";
        }
        
        parameterOrder += "\"";
        processTypes += "</xsd:schema></wsdl:types>";
        
        content +=         
        "  <wsdl:portType name=\"" + processName + "_PortType\">" 
        + "    <wsdl:operation name=\"" + processName + "_processOperation\" " 
        + parameterOrder + ">" 
        + "      <wsdl:input name=\"inputMessage\" message=\"impl:processInputMessage\"/>";
        
        if (process.getProcessOutputs().size() > 0) {
            content += 
                "    <wsdl:output name=\"outputMessage\" message=\"impl:processOutputMessage\"/>";
        }
        
        content +=
        "    </wsdl:operation>" +
        "  </wsdl:portType>" +
        processTypes +
        "</wsdl:definitions>";

        try {
            // write file
            File wsdlFile = new File("temp/" + processName+"/" + processName + ".wsdl");
            FileOutputStream out = new FileOutputStream(wsdlFile);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();

            MessagePanel.appendSuccessln("done.");
            
            return wsdlFile;
        } catch (Exception e) {
            MessagePanel
                    .appendErrorln("Error writing WSDL file!");
            Logger.error(e.getMessage());
            e.printStackTrace();
            
            return null;
        }
    }
}
