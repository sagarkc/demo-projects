package com.gs.tools.editorial.core;

import java.io.File;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the contextual data for the currently running instance.
 * <br/>Load context from file on the startup
 * <br/>Saves the context on exit of the application
 * 
 * @author sabuj.das
 *
 */
public final class ApplicationContext implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8166218693627471796L;

	private static volatile ApplicationContext instance;
	
	public static final String DEFAULT_CXT_FILE_PATH = System.getProperty("user.home");
	public static final String CONTEXT_FILE_NAME = ".editorial" + File.separator + "context.cfg";
	public static final String TEMP_FILE_LOCATION = ".editorial" + File.separator + "tmp";
	public static final int DEFAULT_MAX_HISTORY_COUNT = 15;
	
	private String contextFilePath = DEFAULT_CXT_FILE_PATH;
	private String contextFileName = contextFilePath + File.separator + CONTEXT_FILE_NAME;
	
	private boolean openLastOpenedFiles = false;
	private List<String> openedFiles;
	private List<String> temporaryFiles;
	private int maximumHistoryCount = DEFAULT_MAX_HISTORY_COUNT;
	
	private ApplicationContext(){
		synchronized (ApplicationContext.class) {
			if(null != instance)
				throw new UnsupportedOperationException("Instance is already created...");
		}
		openedFiles = new ArrayList<String>();
	}

	public static ApplicationContext getInstance() {
		if(null == instance){
			synchronized (ApplicationContext.class) {
				if(null == instance){
					instance = new ApplicationContext();
				}
			}
		}
		return instance;
	}
	
	private Object readResolve()
    		throws ObjectStreamException{
		return instance;
	}
	
	public boolean isOpenLastOpenedFiles() {
		return openLastOpenedFiles;
	}

	public void setOpenLastOpenedFiles(boolean openLastOpenedFiles) {
		this.openLastOpenedFiles = openLastOpenedFiles;
	}

	public List<String> getOpenedFiles() {
		return openedFiles;
	}

	public List<String> getTemporaryFiles() {
		return temporaryFiles;
	}

	public int getMaximumHistoryCount() {
		return maximumHistoryCount;
	}

	public void setMaximumHistoryCount(int maximumHistoryCount) {
		this.maximumHistoryCount = maximumHistoryCount;
	}

	public String getContextFilePath() {
		return contextFilePath;
	}

	public void setContextFilePath(String contextFilePath) {
		if(null == contextFilePath || contextFilePath.trim().length() == 0)
			contextFilePath = DEFAULT_CXT_FILE_PATH;
		this.contextFilePath = contextFilePath;
	}

	public String getContextFileName() {
		return contextFileName;
	}

	public synchronized void load(){
		
	}
	
	public synchronized void save(){
		
	}
}

