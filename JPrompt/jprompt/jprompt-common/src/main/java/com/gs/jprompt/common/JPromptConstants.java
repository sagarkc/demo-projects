package com.gs.jprompt.common;

public interface JPromptConstants {

	String FILE_SEPARATOR = System.getProperty("file.separator");
	
	String LINE_SEPARATOR = System.getProperty("line.separator");
	
	String OS_NAME = System.getProperty("os.name");
	
	String OS_ARCH = System.getProperty("os.arch");
	
	String OS_VERSION = System.getProperty("os.version");
	
	String USER_DIR = System.getProperty("user.dir");
	
	String USER_HOME = System.getProperty("user.home");
	
	String USER_NAME = System.getProperty("user.name");
	
	char DEFAULT_PROMPT_CHAR = '>';
	
	String DEFAULT_PROMPT = USER_NAME;
}
