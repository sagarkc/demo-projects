/* ******************************************************************************
 * 	
 * 	Name	: SystemInfo.java
 * 	Type	: com.gs.jprompt.common.SystemInfo
 * 
 * 	Created	: Feb 21, 2012
 * 	
 * 	Author	: Sabuj Das [ mailto::sabuj.das@gmail.com ]
 * 
 * -----------------------------------------------------------------------------*
 * 																				*
 * Copyright © Sabuj Das 2010 All Rights Reserved. 								*
 * <br/>No part of this document may be reproduced without written 				*
 * consent from the author.														*
 * 																				*
 ****************************************************************************** */

package com.gs.jprompt.common;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public final class SystemInfo {

	public static final String OS_TYPE_WIN = "WINDOWS";
	public static final String OS_TYPE_UNIX = "UNIX";
	public static final String OS_TYPE_MAC = "MAC";
	public static final String OS_TYPE_SOLARIS = "SOLARIS";
	
	public static final String OS_TYPE;
	public static final char PROMPT_CHAR;
	
	static{
		String osType = "";
		char ch = '>';
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.indexOf("win") >= 0){
			osType = OS_TYPE_WIN;
		} else if(osName.indexOf("mac") >= 0){
			osType = OS_TYPE_MAC;
			ch = '$';
		} else if(osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0){
			osType = OS_TYPE_UNIX;
			ch = '$';
		} else if(osName.indexOf("sunos") >= 0){
			osType = OS_TYPE_SOLARIS;
			ch = '$';
		}
		OS_TYPE = osType;
		PROMPT_CHAR = ch;
	}
	
	private SystemInfo(){}
}
