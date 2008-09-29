package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.ImageIcon;


/**
 * Defines GUI constants to ease customizing.
 *
 */
public class Constants {
	
	public static final Color BORDER_TITLE_COLOR = new Color(100, 110, 130);	
	public static final Color PANEL_BACKGROUND_COLOR = new Color(240, 240, 240);
	public static final Color INNERPANEL_BACKGROUND_COLOR = Color.WHITE;
	public static final Color LIGHT_BLUE = new Color(195,213,235);
	public static final Color LIGHT_GREEN = new Color(195,235,213);
	public static final Color PROCESS_PARAMETER_COLOR = LIGHT_BLUE;//new Color(150,190,240);
	public static final Color SERVICE_COLOR = new Color(140,170,210);
	
	public static final Font BORDER_FONT = new Font("Arial", 0, 11);
	
	public static final ImageIcon ROOT_ICON = new ImageIcon("root_icon.gif");
	public static final ImageIcon CHECK_MARK = new ImageIcon("check_mark.gif");
	public static final ImageIcon ARROW_RIGHT = new ImageIcon("arrowRight.gif");
	public static final ImageIcon ARROW_RIGHT_LIGHT = new ImageIcon("arrowRightLight.gif");
	public static final ImageIcon ARROW_LEFT = new ImageIcon("arrowLeft.gif");
	public static final ImageIcon ARROW_LEFT_LIGHT = new ImageIcon("arrowLeftLight.gif");
	public static final ImageIcon QUESTIONMARK = new ImageIcon("questionmark.gif");
	public static final Image APPLICATION_ICON = new ImageIcon("application_icon.gif").getImage();
	
	public static final int PARAMETER_TREE_ROW_HEIGHT = 18;
	public static final int TOP_REST_DIVIDER_LOCATION = 110;
	public static final int PARAMETERPANE_MESSAGEPANEL_DIVIDER_LOCATION(int height){ return (int)((height - 100) / 5.0 * 3.5); }
	public static final int PARAMETERSPLITPANE_DIVIDER_LOCATION(int width){ return (int) ((width - 20) / 2.0);}
	public static final int MENU_PROCESSPANEL_DIVIDER_LOCATION = 20;
	public static final int DIVIDERSIZE = 5;
	
	public static final String PROCESSINPUTS = "Process Inputs";
	public static final String PROCESSOUTPUTS = "Process Outputs";
	public static final String DEFAULT_PARAMETER_TITLE = "Parameter";
	public static final String CLEAR_ASSIGNMENT = "Clear Assignment";
	public static final String SHOW_ASSIGNMENT = "Show Assignment";
	public static final String FIND_MATCHING = "Find Matching";
	public static final String SET_AS_PROCESS_INPUT = "Set As Process Input";
	public static final String SET_AS_PROCESS_OUTPUT = "Set As Process Output";
	public static final String SET_ASSIGNMENT = "Assign";
	public static final String CLEAR_PARAMETER = "Clear Parameter";
	public static final String MATCHING_RESULT = "Matching Result";
	
	// Menu
	public static final String NEW_MENU = "New";
	public static final String CREATE_NEW_PROCESS = "Create New Process";
	public static final String EDIT_MENU = "Edit";
	public static final String ADD_SEMANTIC_WEB_SERVICE = "Add Semantic Web Service";
	public static final String LOAD_SEMANTIC_BRIDGE = "Load Semantic Bridge";
	public static final String SHOW_LOADED_SEMANTIC_BRIDGES = "Show Loaded Semantic Bridges";
	public static final String DEPLOYMENT_MENU = "Deployment";
	public static final String WEB_SERVICE_CONTAINER_DEPLOYER_MENU = "Deploy To Semantic BPEL Engine";
	public static final String TOMCAT_AXIS_DEPLOYER = "Use ActiveBPEL-Deployment-Writer";
	public static final String File_DEPLOYER = "Deploy Process Descriptions To File System";
	public static final String DEPLOY_PROCESS = "Deploy Process";
	public static final String HELP_MENU = "Help";
	public static final String HELP_CONTENTS = "Help Contents";
	public static final String ABOUT_SEMANTIC_WEB_SERVICE_COMPOSER = "About Semantic Web Service Composer";
	public static final String ABOUT = "Semantic Web Service Composer for BPEL\nVersion 1.0 - "+
    "September 2008\n" +
    "Author: Ralf Weinand (Ralf.Weinand@fokus.fraunhofer.de)\n"+
    "Fraunhofer Institute for Open Communication Systems\n\n"+
	"A prototype for semi-automatic BPEL process composition\n" +
    "based on the work of Nils Barnickel (Nils.Barnickel@fokus.fraunhofer.de).   \n\n";
	
	
	// Deployment
	public static final String SET_TARGET_DIR = "Set Target Directory";
    public static final String DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR = "DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR";
	public static final String DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR(){
		
		Properties defaults = new Properties();
		try{
		//"C:/Programme/Tomcat/webapps/PublicServices";
		FileInputStream properties = new FileInputStream("defaults.properties");
		defaults.load(properties);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return defaults.getProperty(Constants.DEFAULT_ACTIVE_BPEL_DEPLOYMENT_DIR);
	}
	public static final String OK = "OK";
	public static final String CANCEL = "Cancel";
	
	
}
