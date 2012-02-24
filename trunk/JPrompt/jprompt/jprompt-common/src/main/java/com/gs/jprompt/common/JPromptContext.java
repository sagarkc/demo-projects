package com.gs.jprompt.common;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

public final class JPromptContext {

	private static JPromptContext context;
	
	private static final Logger logger = Logger.getLogger(JPromptContext.class);
	
	private JPromptContext(){
		if(logger.isDebugEnabled()){
			logger.debug("Creating new Instance of context");
		}
		promptCount = 0;
		try{
			Font digital7mono = Font.createFont(Font.TRUETYPE_FONT, 
					getClass().getResourceAsStream("/fonts/digital-7-mono.ttf"));
			digital_7_mono = digital7mono.deriveFont(Font.PLAIN, 16);
			
			Font vera = Font.createFont(Font.TRUETYPE_FONT, 
					getClass().getResourceAsStream("/fonts/VeraMono.ttf"));
			veraMono = vera.deriveFont(Font.PLAIN, 12);
			defaultEditorFont = vera.deriveFont(Font.PLAIN, 12);
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
	
	private Font digital_7_mono;
	public Font getDigital_7_mono() {
		return digital_7_mono;
	}

	public Font veraMono;
	
	private Font defaultEditorFont;
	public Font getDefaultEditorFont() {
		return defaultEditorFont;
	}
	
	public String consoleStartupDirectory = "C:\\";
	
	public final char PROMPT_CHAR = '>';
	
	public int promptCount = 0;
	
	private Map<String, CommandWindow> commandWindowMap = new HashMap<String, CommandWindow>();

	public Map<String, CommandWindow> getCommandWindowMap() {
		return commandWindowMap;
	}
	
	
}
