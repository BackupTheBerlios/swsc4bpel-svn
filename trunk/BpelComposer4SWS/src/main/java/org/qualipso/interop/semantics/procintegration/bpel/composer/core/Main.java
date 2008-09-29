package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import javax.swing.JFrame;

import org.mindswap.owl.OWLFactory;
import org.qualipso.interop.semantics.procintegration.bpel.composer.gui.MainFrame;

/**
 * Main class for starting the Semantic Web Service Composer.
 *
 */
public class Main {	
	/**
	 * Start Semantic Web Service Composer
	 * @param args
	 */
	public static void main(String[] args) {
		
		Logger.info("Starting Semantic Web Service Composition Tool ...");
		init();
		
		JFrame f = new MainFrame();
		Controller.getInstance().setFrame(f);
		f.setVisible(true);	
	}
	
	/**
	 * init composer
	 *
	 */
	public static void init(){

		//	logger config
		Logger.configure();
		
		//	init OWL-S API
		OWLFactory.createKB();
	}
}
