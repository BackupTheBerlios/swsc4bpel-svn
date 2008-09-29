package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;


/**
 * The ParameterSplitPane displays process and service parameters in an left and right ParameterPanel.<br>
 * 
 * mode 1 (display service parameters):<br>
 * leftPanel: service inputs <br>
 * rightPanel: service outputs <br>
 * 
 * mode 2 (display process parameters):<br>
 * leftPanel: process inputs <br>
 * rightPanel: process outputs <br>
 *
 * mode 3 (display matching parameters):<br>
 * leftPanel: process inputs of dataflow target service <br>
 * rightPanel: matching outputs of dataflow source services and matching process inputs <br>
 */
public class ParameterSplitPane extends JSplitPane {

	// Panel with border
	JPanel leftPanel;
	JPanel rightPanel;

	//embedded scrollPanes
	JScrollPane scrollPaneLeft;
	JScrollPane scrollPaneRight;
	
	//default inner panel
	JPanel default1Panel;
	JPanel default2Panel;

	public ParameterSplitPane() {

		Controller.getInstance().setParameterPane(this);
		
		// set layout
		this.setBorder(null);
		this.setDividerSize(Constants.DIVIDERSIZE);
		this.setDividerLocation(Constants.PARAMETERSPLITPANE_DIVIDER_LOCATION((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()));
		this.setContinuousLayout(true);
		
		// left parameter panel
		this.leftPanel = new JPanel();
		this.leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Parameter", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, Constants.BORDER_FONT,
				Constants.BORDER_TITLE_COLOR));
		leftPanel.setBackground(Constants.PANEL_BACKGROUND_COLOR);
		leftPanel.setLayout(new BorderLayout());

		// right parameter panel
		this.rightPanel = new JPanel();
		this.rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constants.DEFAULT_PARAMETER_TITLE, TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, Constants.BORDER_FONT,
				Constants.BORDER_TITLE_COLOR));
		this.rightPanel.setBackground(Constants.PANEL_BACKGROUND_COLOR);
		this.rightPanel.setLayout(new BorderLayout());

		// embedded scroll panes
		this.scrollPaneLeft = new JScrollPane();
		this.scrollPaneLeft.setDoubleBuffered(true);
		this.scrollPaneLeft.setAutoscrolls(true);
		this.scrollPaneLeft.setBorder(null);
		this.scrollPaneRight = new JScrollPane();
		this.scrollPaneRight.setBorder(null);
		this.leftPanel.add(this.scrollPaneLeft,BorderLayout.CENTER);
		this.rightPanel.add(this.scrollPaneRight,BorderLayout.CENTER);

		// default inner panel
		this.default1Panel = new JPanel();
		this.default1Panel.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);
		this.default2Panel = new JPanel();
		this.default2Panel.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);
		this.scrollPaneLeft.getViewport().add(this.default1Panel);
		this.scrollPaneRight.getViewport().add(this.default2Panel);

		this.setLeftComponent(this.leftPanel);
		this.setRightComponent(this.rightPanel);
	}
	
	/**
	 * Update left panel.
	 * @param panel
	 */
	public void setLeftPanel(ParameterPanel panel) {
		
		//	display no parameters available
		if(panel.getTree()== null){
			JPanel noParameterPanel = new JPanel();
			noParameterPanel.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);
			noParameterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			noParameterPanel.add(panel.getNoParameterLabel());
			this.scrollPaneLeft = new JScrollPane(noParameterPanel);
		
			// display parameters
		}else {
			this.scrollPaneLeft = new JScrollPane(panel.getTree());
		}
		
		// reset scrollPane to make scrollable
		this.scrollPaneLeft.setBorder(null);
		this.leftPanel.removeAll();
		this.leftPanel.add(this.scrollPaneLeft);		
		this.setDividerLocation(this.getDividerLocation());
	}

	public void setLeftTitle(String title) {
		this.leftPanel.setBorder(BorderFactory.createTitledBorder(null, title,
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, Constants.BORDER_FONT,
				Constants.BORDER_TITLE_COLOR));
	}

	/**
	 * Update right panel.
	 * @param panel
	 */
	public void setRightPanel(ParameterPanel panel) {
		
		// display no parameters available
		if(panel.getTree()== null){
			JPanel noParameterPanel = new JPanel();
			noParameterPanel.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);
			noParameterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			noParameterPanel.add(panel.getNoParameterLabel());
			this.scrollPaneRight = new JScrollPane(noParameterPanel);
		
			// display parameters
		}else {
			this.scrollPaneRight = new JScrollPane(panel.getTree());
		}
		
		// reset scrollPane to make scrollable
		this.scrollPaneRight.setBorder(null);
		this.rightPanel.removeAll();
		this.rightPanel.add(this.scrollPaneRight);
		this.setDividerLocation(this.getDividerLocation());
	}

	public void setRightTitle(String title) {
		this.rightPanel.setBorder(BorderFactory.createTitledBorder(null, title,
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, Constants.BORDER_FONT,
				Constants.BORDER_TITLE_COLOR));
	}
	
	public String getRightTitle() {
		return ((TitledBorder)this.rightPanel.getBorder()).getTitle();
	}
	
	/**
	 * Clear parameter panels.
	 *
	 */
	public void clear(){
		
		//clear left
		this.scrollPaneLeft.getViewport().removeAll();
		this.scrollPaneLeft.getViewport().setLayout(new BorderLayout());
		this.scrollPaneLeft.getViewport().add(default1Panel, BorderLayout.CENTER);
		this.setLeftTitle(Constants.DEFAULT_PARAMETER_TITLE);
		
		//clear right
		this.scrollPaneRight.getViewport().removeAll();
		this.scrollPaneRight.getViewport().setLayout(new BorderLayout());
		this.scrollPaneRight.getViewport().add(default2Panel, BorderLayout.CENTER);
		this.setRightTitle(Constants.DEFAULT_PARAMETER_TITLE);
	}
	
}
