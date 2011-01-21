package com.gs.jprompt.common;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

public final class JPromptContext {

	private static JPromptContext context;
	
	private static final Logger logger = Logger.getLogger(JPromptContext.class);
	
	private JPromptContext(){
		if(logger.isDebugEnabled()){
			logger.debug("Creating new Instance of context");
		}
		try{
			Font digital7mono = Font.createFont(Font.TRUETYPE_FONT, 
					getClass().getResourceAsStream("/fonts/digital-7-mono.ttf"));
			
			
			
			digital_7_mono = digital7mono.deriveFont(Font.PLAIN, 16);//new Font(digital7mono.getName(), Font.PLAIN, 14);
		}catch(Exception ex){
			logger.error("Cannot initialize Fonts", ex);
			ex.printStackTrace();
		}
		
	}

	public static JPromptContext getContext() {
		synchronized (JPromptContext.class) {
			if(null == context){
				context = new JPromptContext();
			}
		}
		
		return context;
	}
	
	public static JPromptContext resetContext() {
		synchronized (JPromptContext.class) {
			context = new JPromptContext();
		}
		return context;
	}
	
	/**
	 * Last browsed directory
	 */
	public String lastBrowsedPath = ".";
	
	public String promptStartupDirectory = ".";
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("com/gs/jprompt/app/form/Bundle");
	public ResourceBundle getResourceBundle(){
		return resourceBundle;
	}
	
	public Font digital_7_mono;
	
	public static void main(String[] args) throws Exception {
	    File f = new File("fonts/digital-7-mono.ttf");
	    FileInputStream in = new FileInputStream(f);
	    Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, in);
	    Font dynamicFont32Pt = dynamicFont.deriveFont(32f);

	    JLabel testLabel = new JLabel(dynamicFont.getName());
	    testLabel.setFont(dynamicFont32Pt);
	    JFrame frame = new JFrame("Font Loading Demo");
	    frame.getContentPane().add(testLabel);
	    frame.pack();
	    frame.setVisible(true);
	  }
}
