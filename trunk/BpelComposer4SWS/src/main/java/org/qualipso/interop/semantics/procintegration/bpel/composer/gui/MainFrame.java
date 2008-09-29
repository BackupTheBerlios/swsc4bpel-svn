package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.theme.SkyBlue;

/**
 * Main frame of Semantic Web Service Composer holding all GUI elements.
 *
 */
public class MainFrame extends JFrame {
	
	// displays the composed process
	private ProcessPanel processPanel;
	
	// displays parameter tree
	private JSplitPane parameterPane;
	
	// displays messages
	private MessagePanel messagePanel;
	
	// structure for panels
	private JSplitPane top_RestSplitPane;
	private JSplitPane menu_ProcessPanelSplitpane;
	private JSplitPane parameterPane_MessagePanelSplitPane;

	/**
	 * Creates initial GUI components and configures layout.
	 *
	 */
	public MainFrame() {
		
		// init
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		// set look
		try{
			Plastic3DLookAndFeel.setCurrentTheme(new SkyBlue());
			UIManager.setLookAndFeel ("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		}catch (Exception e){
			Logger.warn("com.jgoodies.looks.plastic.Plastic3DLookAndFeel could not be loaded!");
			Logger.warn("using default LookAndFeel");
			
		}
		this.getContentPane().setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim);

		//title
		this.setTitle("Semantic Web Service Composer");
		this.setIconImage(Constants.APPLICATION_ICON);
		
		
		// menu
		Menu menu = new Menu();
		Controller.getInstance().setMenu(menu);
		this.menu_ProcessPanelSplitpane  = new JSplitPane(javax.swing.JSplitPane.VERTICAL_SPLIT);
		//this.top.setLayout(new BorderLayout());
		this.menu_ProcessPanelSplitpane.setLeftComponent(menu);//,BorderLayout.NORTH);
		
		// process panel	
		this.processPanel = new ProcessPanel();
		Controller.getInstance().setProcessPanel(this.processPanel);
		this.menu_ProcessPanelSplitpane.setRightComponent(processPanel);//,BorderLayout.CENTER);
		this.menu_ProcessPanelSplitpane.setBorder(null);
		this.menu_ProcessPanelSplitpane.setDividerLocation(Constants.MENU_PROCESSPANEL_DIVIDER_LOCATION);
		//this.menu_ProcessPanelSplitpane.enable(false);
		this.menu_ProcessPanelSplitpane.setDividerSize(Constants.DIVIDERSIZE);
	
		// parameter pane
		this.parameterPane = new ParameterSplitPane();

		
		// message panel
		this.messagePanel = MessagePanel.getInstance();
		
		
		// structure in split panes
		parameterPane_MessagePanelSplitPane = new JSplitPane();
		parameterPane_MessagePanelSplitPane.setBorder(null);
		parameterPane_MessagePanelSplitPane
				.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		parameterPane_MessagePanelSplitPane.setDividerSize(Constants.DIVIDERSIZE);
		parameterPane_MessagePanelSplitPane.setLeftComponent(parameterPane);
		parameterPane_MessagePanelSplitPane.setRightComponent(messagePanel);
		parameterPane_MessagePanelSplitPane
				.setDividerLocation(Constants.PARAMETERPANE_MESSAGEPANEL_DIVIDER_LOCATION((int)dim.getHeight()));
		
		top_RestSplitPane = new JSplitPane();
		top_RestSplitPane.setBorder(null);
		top_RestSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		top_RestSplitPane.setDividerSize(Constants.DIVIDERSIZE);
		top_RestSplitPane.setLeftComponent(menu_ProcessPanelSplitpane);
		top_RestSplitPane.setRightComponent(parameterPane_MessagePanelSplitPane);
		top_RestSplitPane.setDividerLocation(Constants.TOP_REST_DIVIDER_LOCATION);

		// add to frame
		this.getContentPane().add(top_RestSplitPane);

	}

}
