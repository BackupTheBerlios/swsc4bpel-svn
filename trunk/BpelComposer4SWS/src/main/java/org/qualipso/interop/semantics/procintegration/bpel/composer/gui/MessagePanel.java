package org.qualipso.interop.semantics.procintegration.bpel.composer.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


/**
 * Message panel GUI component. 
 * Singleton object which prints messages inside the Semantic Web Service Composer.
 *
 */
public class MessagePanel extends JScrollPane  {
	
	// singleton instance
	private static MessagePanel instance = new MessagePanel();;
	
	// color settings for text messages
	private static final SimpleAttributeSet redText = new SimpleAttributeSet();
	static {
		StyleConstants.setForeground(redText, new Color(0.7f, 0.2f, 0.2f));//Color.red);
	}

	private static final SimpleAttributeSet blackText = new SimpleAttributeSet();
	static {
		StyleConstants.setForeground(blackText, Color.black);
	}
	
	private static final SimpleAttributeSet blueText = new SimpleAttributeSet();
	static {
		StyleConstants.setForeground(blueText, Constants.BORDER_TITLE_COLOR);
	}

	private static final SimpleAttributeSet greenText = new SimpleAttributeSet();
	static {
		StyleConstants.setForeground(greenText,new Color(0.5f, 0.7f, 0.55f));
	}
	
	// enable same output to console
	private static boolean console = false;


	private JTextPane jTextPane = new JTextPane();

	private MessagePanel() {
		
		try {
			this.setBorder(javax.swing.BorderFactory.createTitledBorder(
					null, "MessagePanel", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, Constants.BORDER_FONT,
					Constants.BORDER_TITLE_COLOR));
			this.setBackground(Constants.PANEL_BACKGROUND_COLOR);
			jTextPane.setEditable(false);
			this.getViewport().add(jTextPane);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static MessagePanel getInstance() {
		return instance;
	}

	private static void _appendError(final String error) {
		try {
			final Document doc = instance.jTextPane.getDocument();
			doc.insertString(doc.getLength(), error, redText);
			if(console) System.out.print(error);
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

	public static void appendError(final String error) {
		if ("AWT-EventQueue-0".equals(Thread.currentThread().getName())) {
			_appendError(error);
		} else {
			try {
				EventQueue.invokeAndWait(new Runnable() {
					public void run() {
						_appendError(error);
					}
				});
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void _appendOut(final String out) {
		try {
			final Document doc = instance.jTextPane.getDocument();
			doc.insertString(doc.getLength(), out, blackText);
			if(console) System.out.print(out);
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

	public static void appendOut(final String out) {
		if ("AWT-EventQueue-0".equals(Thread.currentThread().getName())) {
			_appendOut(out);
		} else {
			try {
				EventQueue.invokeAndWait(new Runnable() {
					public void run() {
						_appendOut(out);
					}
				});
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void _appendOutBlue(final String out) {
		try {
			final Document doc = instance.jTextPane.getDocument();
			doc.insertString(doc.getLength(), out, blueText);
			if(console) System.out.print(out);
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

	public static void appendOutBlue(final String out) {
		if ("AWT-EventQueue-0".equals(Thread.currentThread().getName())) {
			_appendOutBlue(out);
		} else {
			try {
				EventQueue.invokeAndWait(new Runnable() {
					public void run() {
						_appendOutBlue(out);
					}
				});
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void appendOutBlueln(final String out) {
		appendOutBlue(out+"\n");
	}

	private static void _appendSuccess(final String suc) {
		try {
			final Document doc = instance.jTextPane.getDocument();
			//doc.insertString(doc.getLength(), " ... ", blackText);
			doc.insertString(doc.getLength(), suc, greenText);
			if(console) System.out.print(suc);
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

	public static void appendSuccess(final String suc) {
		if ("AWT-EventQueue-0".equals(Thread.currentThread().getName())) {
			_appendSuccess(suc);
		} else {
			try {
				EventQueue.invokeAndWait(new Runnable() {
					public void run() {
						_appendSuccess(suc);
					}
				});
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void appendOutln(final String out) {
		appendOut(out + "\n");
	}

	public static void appendErrorln(final String out) {
		appendError(out + "\n");
	}

	public static void appendSuccessln(final String suc) {
		appendSuccess(suc + "\n");
	}
}
