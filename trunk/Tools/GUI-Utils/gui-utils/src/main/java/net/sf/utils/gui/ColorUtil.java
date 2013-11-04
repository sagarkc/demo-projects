/**
 * 
 */
package net.sf.utils.gui;

import java.awt.Color;


/**
 * @author sabuj.das
 * 
 */
public class ColorUtil {

	private static final char VALID_COLOR_CODE_PREFIX = '#';

	/**
	 * Returns the color code string with # prefixed.
	 * @param color
	 * @return
	 */
	public static String encodeColor(Color color) {
		if (color == null)
			return "";
		char[] hexCodeArray = new char[7];
		hexCodeArray[0] = VALID_COLOR_CODE_PREFIX;
		String red = Integer.toHexString(color.getRed());
		if (red.length() <= 0)
			red = "00";
		if (red.length() >= 2) {
			hexCodeArray[1] = red.charAt(0);
			hexCodeArray[2] = red.charAt(1);
		} else if (red.length() == 1) {
			hexCodeArray[1] = '0';
			hexCodeArray[2] = red.charAt(0);
		}
		String green = Integer.toHexString(color.getGreen());
		if (green.length() <= 0)
			green = "00";
		if (green.length() >= 2) {
			hexCodeArray[3] = green.charAt(0);
			hexCodeArray[4] = green.charAt(1);
		} else if (green.length() == 1) {
			hexCodeArray[3] = '0';
			hexCodeArray[4] = green.charAt(0);
		}
		String blue = Integer.toHexString(color.getBlue());
		if (blue.length() <= 0)
			blue = "00";
		if (blue.length() >= 2) {
			hexCodeArray[5] = blue.charAt(0);
			hexCodeArray[6] = blue.charAt(1);
		} else if (blue.length() == 1) {
			hexCodeArray[5] = '0';
			hexCodeArray[6] = blue.charAt(0);
		}
		return new String(hexCodeArray);
	}

	/**
	 * Convert the HEX color code to a Color object. The HEX code should be
	 * prefixed by #. Any other prefix will be considered as a invalid color
	 * code and Color.BLACK will be returned. If the length of the code is < 7,
	 * it will return Color.BLACK
	 * 
	 * @param colorCode
	 * @return
	 */
	public static Color decode(String colorCode) {
		if (null == colorCode || colorCode.length() <= 0) {
			return Color.BLACK;
		}
		if (colorCode.length() < 7) {
			return Color.BLACK;
		}
		char[] codeChars = colorCode.toCharArray();

		if (VALID_COLOR_CODE_PREFIX != codeChars[0]) {
			return Color.BLACK;
		}

		int r = 0, g = 0, b = 0;

		try {
			r = Integer.parseInt("" + codeChars[1] + codeChars[2], 16);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			g = Integer.parseInt("" + codeChars[3] + codeChars[4], 16);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			b = Integer.parseInt("" + codeChars[5] + codeChars[6], 16);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new Color(r, g, b);
	}
}
