package net.sf.jxsp.common.util;

public class BrowserEncoderUtil {

	public static String encodeForHtml(String s) {
		StringBuilder sb = new StringBuilder();
		int len = (s == null ? -1 : s.length());
		
		for(int i=0; i<len; i++) {
			char c = s.charAt(i);
			
			if(c == '&') {
				sb.append("&amp;");
			} else if(c == '<') {
				sb.append("&lt;");
			} else if(c == '>') {
				sb.append("&gt;");
			} else if(c == '"') {
				sb.append("&quot;");
			} else if(c == '\'') {
				sb.append("&#x27;");
			} else if(c == '/') {
				sb.append("&#x2F;");
			} else {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
	public static String encodeForAttribute(String s) {
		StringBuilder sb = new StringBuilder();
		int len = (s == null ? -1 : s.length());
		
		for(int i=0; i<len; i++) {
			char c = s.charAt(i);
			
			if(c < 256 && !Character.isLetterOrDigit((int)c)) {
				sb.append("&#");
				sb.append((int)c);
				sb.append(';');
			} else {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
}
