package com.gs.jprompt.common;

public final class JPromptContext {

	private static JPromptContext context;
	
	private JPromptContext(){}

	public static JPromptContext getContext() {
		synchronized (JPromptContext.class) {
			if(null == context){
				context = new JPromptContext();
			}
		}
		
		return context;
	}
	
	public String lastBrowsedPath = ".";
	
}
