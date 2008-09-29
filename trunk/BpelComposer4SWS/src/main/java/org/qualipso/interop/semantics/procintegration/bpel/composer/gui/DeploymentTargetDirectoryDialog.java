package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Controller;



/**
 * A dialog for setting the target directory.
 * Provides a default target directory or opens a file chooser.
 */
public class DeploymentTargetDirectoryDialog extends JDialog{
	
	private JTextField text = new JTextField();
	private final String defaultDirPath;
	private boolean ok;
	


	public DeploymentTargetDirectoryDialog(String defaultDirPath){
		super(Controller.getInstance()
				.getFrame(), 
                Constants.DEPLOYMENT_MENU,
                true);

		// show default target directory path
		this.defaultDirPath = defaultDirPath;
		File defaultDir = new File(defaultDirPath);
		this.text.setColumns(30);
		if(defaultDir.exists()&& defaultDir.isDirectory()) {
			this.text.setText(defaultDirPath);
		}
		
		// add components
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(top,BorderLayout.NORTH);
		this.getContentPane().add(bottom, BorderLayout.CENTER);
	
		// open file chooser button
		JButton openFileChooser = new JButton("...");
		openFileChooser.setToolTipText("Open File Chooser");
		openFileChooser.addActionListener(new ButtonListener());
		JLabel label = new JLabel(Constants.SET_TARGET_DIR+":");
		
		top.add(label);
		top.add(text);
		top.add(openFileChooser);
		
		
		JButton ok = new JButton(Constants.OK);
		JButton cancel = new JButton(Constants.CANCEL);
		ok.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		
		bottom.add(ok);
		bottom.add(cancel);
		
		// center location
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = ((int)dim.getWidth()/2)-((int)this.getWidth()/2);
		int height = ((int)dim.getHeight()/2)-((int)this.getHeight()/2);
		this.setLocation(width,height);
		this.setVisible(true);
		
	}

	public String getText() {
		
		if(isOk()){
			return text.getText();
			
		} else{
			return null;
		}
	}

	public void setText(String text) {
		this.text.setText(text);
	}
	
	public void close(){
		this.setVisible(false);
	}
	
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	/**
	 * Opens file chooser dialog to set target dir.
	 * 
	 * @param defaultPath
	 * @return path
	 */
	private String choosePath(String defaultPath){
	
		// select target directory
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(Constants.SET_TARGET_DIR);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// show default target dir
		File defaultDir = new File(defaultPath);
		if(defaultDir.exists()&& defaultDir.isDirectory()) {
			fileChooser.setSelectedFile(defaultDir);
		}
		
		int returnValue = fileChooser.showOpenDialog(Controller.getInstance()
				.getFrame());

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsoluteFile().getAbsolutePath();

		} else{
			return null;
		}
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e){
			
			if(e.getActionCommand().equals("...")){
				setText(choosePath(defaultDirPath));
				
			}else if(e.getActionCommand().equals(Constants.OK)){
				setOk(true);
				close();
				
			}else if(e.getActionCommand().equals(Constants.CANCEL)){
				setText(null);
				close();
			}
		}
	}
}