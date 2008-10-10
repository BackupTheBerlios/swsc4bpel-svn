package org.qualipso.interop.semantics.procintegration.bpel.composer.deployment;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.Constants;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MessagePanel;


/**
 *  Specific deployer for the ActiveBPEL engine.
 */
public class ActiveBPELDeployer extends FileDeployer {

	
	/**
	 * Deploys the BPEL archive to a specified ActiveBPEL engine.
	 */
	public void deployProcess() {
	    generateFiles();

		ProcessModel process = Controller.getInstance().getProcess();
        String processName = process.getProcessName();

        generateAntBuildFile();
        File batchFile = generateAntDeployBatchFile();
        String processBundleFilename = processName + ".bpr";

        MessagePanel.appendOut("    Generating BPEL Archive File ");
        MessagePanel.appendOutBlue(processBundleFilename);
        MessagePanel.appendOut(" ... ");

		try {
            String[] command = new String[3];
            command[0] = "cmd";
            command[1] = "/c";
            command[2] = batchFile.getCanonicalPath().toString();

            Runtime.getRuntime().exec(command); 
            
            // wait until the file was created; wait max. 20 seconds
            File processBundleFile = new File("temp/" + processName + "/" + processBundleFilename);
            int wait = 20;	            
            while (wait > 0)
            {
                if (processBundleFile.exists()) 
                { 
                    break; 
                }
                Thread.sleep(1000);
                wait--;
            }	
            if (wait <= 0)
            {
                // if the file still not exists an error must have occurred
                throw new FileNotFoundException();
            }
            
            MessagePanel.appendSuccessln("done.");
        } catch (Exception e) {
            MessagePanel
                .appendErrorln("Error generating BPEL Archive File!");
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
		
		
        // get BPEL engine deploy dir
        File deploymentDir = this
                .getTargetDir(Constants.DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR());
        
        if (deploymentDir == null) {
            MessagePanel.appendOutln("    No files deloyed.");
            return;
        }

        // deploy the archive to the BPEL engine
        File archiveFile = new File("temp/" + processName + "/" + processBundleFilename);
		copy(archiveFile, deploymentDir);
		
		MessagePanel.appendOut("    Deploying process to ");
		MessagePanel.appendOutBlue(deploymentDir.getAbsolutePath());
		MessagePanel.appendOutln(".");
	}

    /**
     * Generates an Ant-build-file to deploy the BPEL process.
     * 
     * @return the Ant-file
     * 
     * @throws Exception
     */
    public File generateAntBuildFile() {
        ProcessModel process = Controller.getInstance().getProcess();
        String processName = process.getProcessName();
        
//        MessagePanel.appendOut("    Generating Ant Build File ");
//        MessagePanel.appendOutBlue("build.xml");
//        MessagePanel.appendOut(" ... ");
    
        String dir = new File("temp/" + processName + "/").getAbsolutePath();
        
        String content = 
            "<?xml version='1.0' encoding='UTF-8'?>"
            + "<project name=\"Service Archive\" default=\"build\" basedir=\".\">" 
            +  "<property name=\"archive.filename\" value=\"" + dir + "\\" + processName 
            +       ".bpr\" />" 
            +  "<!-- Build the BPR file from the following (generated) file set. -->" 
            +  "<target name=\"build\" depends=\"\">" 
            +    "<delete file=\"${archive.filename}\" />" 
            +    "<jar destfile=\"${archive.filename}\">" 
            +      "<!-- PDD Files. -->" 
            +      "<zipfileset file=\"" + dir 
            +           "\\" + processName + ".pdd\" fullpath=\"META-INF/pdd//" 
            +               processName + "/" + processName + ".pdd\" />" 
            +      "<!-- BPEL Files. -->" 
            +      "<zipfileset file=\"" + dir + "\\" + processName + ".bpel\" fullpath=\"bpel/" 
            +           processName + "/" + processName + ".bpel\" />" 
            +      "<!-- WSDL Files. -->" 
            +      "<zipfileset file=\"" + dir + "\\" + processName + ".wsdl\" fullpath=\"wsdl/" 
            +            processName + "/" + processName + ".wsdl\" />" 
            +      "<!-- catalog File. -->" 
            +      "<zipfileset file=\"" + dir 
            +            "\\catalog.xml\" fullpath=\"meta-inf/catalog.xml\" />" 
            +      "<!-- XSD Files -->" 
            +      "<!-- Other Files. -->" 
            +    "</jar>    " 
            +  "</target>" 
            + "</project>";
    
        try {
            // write file
            File antFile = new File("temp/" + processName + "/build.xml");
            FileOutputStream out = new FileOutputStream(antFile);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();
    
//            MessagePanel.appendSuccessln("done.");
            
            return antFile;
        } catch (Exception e) {
            MessagePanel
                    .appendErrorln("Error writing Ant Build File!");
            Logger.error(e.getMessage());
            e.printStackTrace();
            
            return null;
        }
    }

    /**
     * Generates an Ant-build-file to deploy the BPEL process.
     * 
     * @return the Ant-file
     * 
     * @throws Exception
     */
    public File generateAntDeployBatchFile() {
        ProcessModel process = Controller.getInstance().getProcess();
        String processName = process.getProcessName();
        
        String content = null;
        try {
            String canonicalPath = new File("temp/" + processName + "/").getCanonicalPath();
            content = "cd " + canonicalPath + "\\\r\n" 
                    + canonicalPath.substring(0, 2) + "\r\n" 
                    + "call ant";
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    
        try {
            // write file
            File batchFile = new File("temp/" + processName + "/deploy.bat");
            FileOutputStream out = new FileOutputStream(batchFile);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();
            
            return batchFile;
        } catch (Exception e) {
            MessagePanel
                    .appendErrorln("Error writing deploy.bat File!");
            Logger.error(e.getMessage());
            e.printStackTrace();
            
            return null;
        }
    }

}
