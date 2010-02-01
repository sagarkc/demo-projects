/** ***************************************************************************
 *		Reading Pad	
 *	
 *	File	: ReadingPadContext.java
 *	Type	: com.gs.readingpad.common.context.ReadingPadContext
 *	Date	: Jan 21, 2010	5:23:55 PM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.readingpad.common.context;

import java.io.Serializable;

/**
 * @author sabuj.das
 *
 */
public class ReadingPadContext implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7250024455131560416L;

	private static ReadingPadContext instance;
	
	private ReadingPadContext() {
		
	}
	
	public static ReadingPadContext getInstance(){
		if(instance == null)
			instance = new ReadingPadContext();
		return instance;
	}
}
