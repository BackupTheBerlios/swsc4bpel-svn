package org.qualipso.interop.semantics.procintegration.bpel.composer.deployment;

import java.io.File;
import java.io.FileOutputStream;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;

/**
 * Generates ActiveBPEL specific artifacts 
 * necessary for the deployment of a process.
 *
 */
public class ActiveBPELDeploymentWriter {

    /**
     * Simple method to write a deployment descriptor (.pdd) 
     * for the BPEL process.
     * 
     * @return the PDD-file
     * 
     * @throws Exception
     */
    public File generateDeploymentDescriptor() {
        ProcessModel process = Controller.getInstance().getProcess();
        String processName = process.getProcessName();
        
        MessagePanel.appendOut("    Generating Deployment Descriptor ");
        MessagePanel.appendOutBlue(processName + ".pdd");
        MessagePanel.appendOut(" ... ");

        String content = 
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<process xmlns=\"http://schemas.active-endpoints.com/pdd/2006/08/pdd.xsd\" " 
            +		"xmlns:bpelns=\"urn:" + processName + "_targetNamespaceURN\" " 
            +		"xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2003/03/addressing\" " 
            +		"location=\"bpel/" + processName + "/" + processName + ".bpel\" " 
            +		"name=\"bpelns:" + processName + "\" persistenceType=\"full\">" 
            +   "<partnerLinks>" 
            +      "<partnerLink name=\"" + processName + "_PartnerLink\">" 
            +         "<myRole allowedRoles=\"\" binding=\"MSG\" " 
            +		    "service=\"" + processName + "_PartnerLinkService\"/>" 
            +      "</partnerLink>" 
            +   "</partnerLinks>" 
            +   "<references>" 
            +      "<wsdl location=\"project:/" + processName + "/" + processName 
            +         ".wsdl\" namespace=\"urn:" + processName + "_extWsdlURN\"/>" 
            +   "</references>" 
            + "</process>";

        try {
            // write file
            File wsdlFile = new File("temp/" + processName + "/" + processName + ".pdd");
            FileOutputStream out = new FileOutputStream(wsdlFile);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();

            MessagePanel.appendSuccessln("done.");
            
            return wsdlFile;
        } catch (Exception e) {
            MessagePanel
                    .appendErrorln("Error writing Deployment Descriptor!");
            Logger.error(e.getMessage());
            e.printStackTrace();
            
            return null;
        }
    }

    /**
     * Simple method to write a catalog file 
     * for the BPEL deployment archive.
     * 
     * @return the PDD-file
     * 
     * @throws Exception
     */
    public File generateCatalogFile() {
        ProcessModel process = Controller.getInstance().getProcess();
        String processName = process.getProcessName();
        
        MessagePanel.appendOut("    Generating Catalog File ");
        MessagePanel.appendOutBlue("catalog.xml");
        MessagePanel.appendOut(" ... ");
    
        String content = 
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<catalog xmlns='http://schemas.active-endpoints.com/catalog/2006/07/catalog.xsd'>" 
            + "  <wsdlEntry location=\"project:/" + processName + "/" + processName + ".wsdl\" "
            		+ "classpath=\"wsdl/" + processName + "/" + processName + ".wsdl\" />" 
            + "</catalog>";
    
        try {
            // write file
            File catalogFile = new File("temp/" + processName + "/catalog.xml");
            FileOutputStream out = new FileOutputStream(catalogFile);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();
    
            MessagePanel.appendSuccessln("done.");
            
            return catalogFile;
        } catch (Exception e) {
            MessagePanel
                    .appendErrorln("Error writing Catalog File!");
            Logger.error(e.getMessage());
            e.printStackTrace();
            
            return null;
        }
    }
}
