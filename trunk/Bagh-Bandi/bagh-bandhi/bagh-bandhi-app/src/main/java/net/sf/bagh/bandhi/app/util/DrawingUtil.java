/**
 * 
 */
package net.sf.bagh.bandhi.app.util;

import java.awt.Graphics;

import net.sf.bagh.bandhi.core.util.StringUtil;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class DrawingUtil {

	public static int calculateTextWidth(Graphics graphics, String text) {
		int width = 0;
		int charWidth = graphics.getFontMetrics().charWidth('H');
		if (StringUtil.hasValidContent(text)) {
			width = charWidth * (text.length() + 1);
		}
		return width;
	}

	public static int calculateTextHeight(Graphics graphics) {
		return graphics.getFontMetrics().getHeight() + 5;
	}

}
