package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ProcessModel;
import org.qualipso.interop.semantics.procintegration.bpel.composer.core.ServiceModel;



/**
 * The ProcessPanel displays the composed process as a sequence of the involved services.
 * Service icons can be pressed to display service parameters.
 *
 */
public class ProcessPanel extends JPanel{
	
	private String title;
	private Vector processComponents = new Vector();
	JPanel innerPanel;
	
	
	public ProcessPanel(){
		
		this.setBackground(Constants.PANEL_BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createTitledBorder(null,
				"Composite Process", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, Constants.BORDER_FONT,
				Constants.BORDER_TITLE_COLOR));
		
		this.innerPanel = new JPanel();
		this.innerPanel.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
		JPanel space = new JPanel(); 
		space.setBackground(Constants.INNERPANEL_BACKGROUND_COLOR);
		this.add(space,BorderLayout.NORTH);
		this.add(innerPanel,BorderLayout.CENTER);
		this.innerPanel.setLayout(new FlowLayout());
		
	}
	
	public void refresh(){
		
		this.innerPanel.removeAll();
		
		Iterator iter = processComponents.iterator();
		while(iter.hasNext()){
			JButton next = (JButton)iter.next();
			this.innerPanel.add(next);
		}
		
		this.innerPanel.doLayout();
	}
	
	public void setProcess(ProcessModel process){
		this.processComponents.removeAllElements();
		this.setTitle(process.getProcessName());
		this.addInputsOutputs();
		this.refresh();
	}
	
	/**
	 * Add initial icons for process inputs and outputs
	 */
	private void addInputsOutputs(){
		JButton processInputs = new JButton();
		processInputs.setText(Constants.PROCESSINPUTS);
		processInputs.setBackground(Constants.PROCESS_PARAMETER_COLOR);
		processInputs.addMouseListener(new ButtonListener());
		
		JButton processOutputs = new JButton();
		processOutputs.setText(Constants.PROCESSOUTPUTS);
		processOutputs.setBackground(Constants.PROCESS_PARAMETER_COLOR);
		processOutputs.addMouseListener(new ButtonListener());
		
		this.processComponents.add(processInputs);
		this.processComponents.add(processOutputs);
	}
	
	/**
	 * Add service icon.
	 * @param service
	 */
	public void addService(ServiceModel service){
		JButton serviceButton = new JButton(service.getName());
		serviceButton.setBackground(Constants.SERVICE_COLOR);
		serviceButton.addMouseListener(new ButtonListener());
		this.processComponents.insertElementAt(serviceButton,processComponents.size()-1);
		this.refresh();
	}
	
	public void setTitle(String name){
		this.title = name;
		this.setBorder(BorderFactory.createTitledBorder(null,
				this.title, TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION,Constants.BORDER_FONT,
				Constants.BORDER_TITLE_COLOR));
	}
}

class ButtonListener implements MouseListener {

	public void mousePressed(MouseEvent e) {
		Controller controller = Controller.getInstance();
		if(((JButton)e.getSource()).getActionCommand().equals(Constants.PROCESSINPUTS)||((JButton)e.getSource()).getActionCommand().equals(Constants.PROCESSOUTPUTS)){
			controller.displayProcessParameters();

		}else {
			controller.displayServiceParameters(((JButton)e.getSource()).getActionCommand());
		}
		
	}
	public void mouseClicked(MouseEvent e) {		
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {	
	}

	public void mouseReleased(MouseEvent e) {
	}
}
