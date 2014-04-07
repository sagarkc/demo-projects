/**
 * 
 */
package com.gs.games.mysticsquare.app.ui;

import java.awt.Graphics;

import com.gs.utils.text.StringUtil;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class GraphicsUtil {

	public static int calculateTextWidth(final Graphics graphics, final String text) {
		int width = 0;
		int charWidth = graphics.getFontMetrics().charWidth('H');
		if (StringUtil.hasValidContent(text)) {
			width = charWidth * (text.length() + 1);
		}
		return width;
	}

	public static int calculateTextHeight(final Graphics graphics) {
		return graphics.getFontMetrics().getHeight() + 5;
	}

}
