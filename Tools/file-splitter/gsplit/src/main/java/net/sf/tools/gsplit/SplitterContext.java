/**
 * 
 */
package net.sf.tools.gsplit;

import java.io.File;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class SplitterContext {

	private static SplitterContext context;
	private SplitterContext(){
		
	}
	
	/**
	 * @return the context
	 */
	public static SplitterContext getContext() {
		if(null != context){
			return context;
		}
		synchronized (SplitterContext.class) {
			context = new SplitterContext();
		}	
		return context;
	}
	
	public File lastOpenedDir = new File(".");
	public String lastOpenedPath = ".";
	
	
}
