/**
 * 
 */
package net.sf.bagh.bandhi.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class StringUtil {

	public static boolean hasValidContent(String str) {
		if (str == null)
			return false;
		return (str.trim().length() > 0);
	}

	public static String getFirstWord(String str) {
		if (!hasValidContent(str))
			return "";
		str = str.trim();
		return str.split(" ")[0];
	}

	public static String mostCommonWord(String input) {
		if (null == input || input.trim().length() == 0)
			return null;
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		String[] splitted = input.split(" ");
		for (String s : splitted) {
			if (wordCountMap.containsKey(s)) {
				Integer v = wordCountMap.get(s);
				v = v + 1;
				wordCountMap.put(s, v);
			} else {
				wordCountMap.put(s, 1);
			}
		}
		String commonWord = "";
		Integer maxCount = 0;
		Set<String> keySet = wordCountMap.keySet();
		for (String key : keySet) {
			Integer v = wordCountMap.get(key);
			if (v.intValue() > maxCount.intValue()) {
				maxCount = v;
				commonWord = key;
			}
		}
		return commonWord;
	}

	public static String convertToString(byte[] rawData) {
		return convertToString(rawData, true);
	}

	public static String convertToString(byte[] rawData, boolean stopAtZero) {
		if (null == rawData || rawData.length <= 0)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < rawData.length; i++) {
			if (rawData[i] == 0 && stopAtZero)
				break;
			char ch = (char) rawData[i];
			buffer.append(ch);
		}
		return buffer.toString();
	}

	public static String getCsvString(String text) {
		if (!hasValidContent(text)) {
			return "";
		}
		if (text.contains(",") || text.contains("\n") || text.contains("\n\r")
				|| text.startsWith("e") || text.startsWith("E")) {
			return getCsvString(text, true);
		}
		return text;
	}

	public static String getCsvString(String text, boolean userQuote) {
		text = text.replaceAll("\"", "\"\"");
		return "\"" + text + "\"";
	}
}
