package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.qualipso.interop.semantics.procintegration.bpel.composer.core.Logger;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.theme.SkyBlue;

/**
 * Frame to display the help contents.
 * Realized as a singleton for non-multiple appearance.
 *
 */
public class HelpFrame extends JFrame {
	
	static HelpFrame helpFrame;
	
	public static void displayHelpFrame(){
		if(helpFrame == null){
			helpFrame = new HelpFrame();
			helpFrame.setVisible(true);
		}else{
			helpFrame.setVisible(true);
		}
	}
	
	private HelpFrame(){

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		try {
			java.net.URL helpURL = new URL("file:doc/help.html");
			editorPane.setPage(helpURL);
		} catch (Exception e) {
			MessagePanel.appendErrorln("Could not find help content!");
			Logger.error(e.getMessage());
			e.printStackTrace();
		}

		// Put the editor pane in a scroll pane.
		JScrollPane helpContent = new JScrollPane(editorPane);
		helpContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.getContentPane().add(helpContent);
		
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
		int height = (int)dim.getHeight();
		int width = (int)dim.getWidth()/3;
		int x = (int)(dim.getWidth()/(1.5));
		int y = 0;
		this.setBounds(x,y,width,height);

		//title
		this.setTitle("Help Content");
		this.setIconImage(Constants.APPLICATION_ICON);
	}
}
