/** ***************************************************************************
 *		Reading Pad	
 *	
 *	File	: FileRWUtil.java
 *	Type	: com.gs.readingpad.util.io.FileRWUtil
 *	Date	: Jan 21, 2010	5:45:13 PM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.readingpad.util.io;

import java.io.Serializable;

/**
 * @author sabuj.das
 *
 */
public class FileRWUtil {

	public static <T extends Serializable> T readAsObject(String fileName){
		return null;
	}
	
	public static String readAsString(String fileName){
		return "";
	}
	
	public static <T extends Serializable> boolean writeAsObject(String fileName, T data){
		return false;
	}
	
	public static boolean writeAsString(String fileName, String data){
		return false;
	}
}
