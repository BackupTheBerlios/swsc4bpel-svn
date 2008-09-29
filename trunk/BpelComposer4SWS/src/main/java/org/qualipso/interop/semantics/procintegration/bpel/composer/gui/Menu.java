package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.SemanticBridge;



/**
 * Menu GUI component.
 *
 */
public class Menu extends JMenuBar {
	
	// list already loaded semantic bridges
	private JMenu showSemanticBridges;
	private JMenu editMenu;
	
	public Menu(){
		
		this.setBorder(null);
		//this.setBackground(Constants.PANEL_BACKGROUND_COLOR);
		MenuListener menuListener = new MenuListener(); 
		
		JMenu newMenu = new JMenu(Constants.NEW_MENU); 
		JMenuItem createNewProcess = new JMenuItem(Constants.CREATE_NEW_PROCESS);
		createNewProcess.addActionListener(menuListener);
		newMenu.add(createNewProcess);
		
		this.editMenu = new JMenu(Constants.EDIT_MENU);
		JMenuItem addService = new JMenuItem(Constants.ADD_SEMANTIC_WEB_SERVICE);
		JMenuItem addBridge = new JMenuItem(Constants.LOAD_SEMANTIC_BRIDGE);
		this.showSemanticBridges = new JMenu(Constants.SHOW_LOADED_SEMANTIC_BRIDGES);
		
		addService.addActionListener(menuListener);
		addBridge.addActionListener(menuListener);
		editMenu.add(addService);
		editMenu.add(addBridge);
		
		
		JMenu deploymentMenu = new JMenu(Constants.DEPLOYMENT_MENU);
		JMenu deployToWebServiceContainerMenu = new JMenu(Constants.WEB_SERVICE_CONTAINER_DEPLOYER_MENU);
		
		JMenuItem deployToTomcatAxis = new JMenuItem(Constants.TOMCAT_AXIS_DEPLOYER);
		JMenuItem deployToFileSystem = new JMenuItem(Constants.File_DEPLOYER);
		
		deployToTomcatAxis.addActionListener(menuListener);
		deployToFileSystem.addActionListener(menuListener);
		
		deployToWebServiceContainerMenu.add(deployToTomcatAxis);
		deploymentMenu.add(deployToWebServiceContainerMenu);
		deploymentMenu.add(deployToFileSystem);
		
		
		JMenu helpMenu = new JMenu(Constants.HELP_MENU);
		JMenuItem help = new JMenuItem(Constants.HELP_CONTENTS);
		JMenuItem about = new JMenuItem(Constants.ABOUT_SEMANTIC_WEB_SERVICE_COMPOSER);
		help.addActionListener(menuListener);
		about.addActionListener(menuListener);
		helpMenu.add(help);
		helpMenu.add(about);
		
		this.add(newMenu);
		this.add(editMenu);
		this.add(deploymentMenu);
		this.add(helpMenu);
	}
	
	public void addSemanticBridge(SemanticBridge semanticBridge){
		this.editMenu.add(showSemanticBridges);
		this.showSemanticBridges.add(new JMenuItem(semanticBridge.getSemanticBridgeURL()));
	}
	
	
	public void removeSemanticBridges(){
		this.showSemanticBridges.removeAll();
		this.editMenu.remove(showSemanticBridges);
	}
	
}
class MenuListener implements ActionListener {

	public void actionPerformed(ActionEvent e){
		
		Controller controller = Controller.getInstance();
		if(e.getActionCommand().equals(Constants.CREATE_NEW_PROCESS)){
			controller.createNewProcess();
			
		}else if (e.getActionCommand().equals(Constants.ADD_SEMANTIC_WEB_SERVICE)){
			controller.addSemanticWebService();
			
		}else if (e.getActionCommand().equals(Constants.LOAD_SEMANTIC_BRIDGE)){
			controller.loadSemanticBridge();
		
		}else if (e.getActionCommand().equals(Constants.TOMCAT_AXIS_DEPLOYER)){
			controller.deployProcess(Constants.TOMCAT_AXIS_DEPLOYER);
			
		}else if (e.getActionCommand().equals(Constants.File_DEPLOYER)){
			controller.deployProcess(Constants.File_DEPLOYER);
		}else if (e.getActionCommand().equals(Constants.HELP_CONTENTS)){
			controller.displayHelp();
		}else if (e.getActionCommand().equals(Constants.ABOUT_SEMANTIC_WEB_SERVICE_COMPOSER)){
			controller.displayAbout();
		}
	}
}
