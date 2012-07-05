package com.gmail.sabuj.career.common.util;


/**
 * The Class StringUtil.
 *
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 */
public class StringUtil {

	/**
	 * Checks for valid content.
	 *
	 * @param str the str
	 * @return true, if successful
	 */
	public static boolean hasValidContent(String str) {
		if (str == null)
			return false;
		return (str.trim().length() > 0);
	}
	
	
	public static String getCsvString(String text){
		if(!hasValidContent(text)){
			return "";
		}
		if(text.contains(",")
				|| text.contains("\n")
				|| text.contains("\n\r")
				|| text.startsWith("e")
				|| text.startsWith("E")){
			return getCsvString(text, true);
		}
		return text;
	}
	
	public static String getCsvString(String text, boolean userQuote){
		text = text.replaceAll("\"", "\"\"");
		return "\"" + text + "\"";
	}
	
	public static boolean stringsEqual(String s1,String s2){
		boolean stringsEqual = false;
		if(s1 == null && s2 == null){
			return true;
		}else if(s1 == null || s2 == null){
			return false;
	    }else if(s1.equalsIgnoreCase(s2)){
	    	 return true;
	    }
		return stringsEqual;
   }
}
