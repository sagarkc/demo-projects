/**
 * 
 */
package com.gs.games.sudoku.desktop;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class GraphicsUtility {

	public static void setRendererHints(final Graphics2D graphics){
		if(graphics == null)
			return;
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		graphics.setRenderingHints(hints);
	}
	
	/**
	 * @param g
	 * @param string
	 */
	public static int getTextWidth(Graphics2D g, String text) {
		return 0;
	}
	
	/**
	 * @param g
	 * @param string
	 */
	public static int getTextHeight(Graphics2D g, String text) {
		return 0;
	}

}
