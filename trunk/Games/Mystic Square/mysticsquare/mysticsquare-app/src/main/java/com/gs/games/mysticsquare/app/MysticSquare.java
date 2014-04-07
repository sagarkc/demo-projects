/**
 * 
 */
package com.gs.games.mysticsquare.app;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.UIManager;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class MysticSquare {

	public static final int FRAME_WIDTH = 820;
	public static final int FRAME_HEIGHT = 730;

	/**
	 * Load the custom font
	 */
	public static Font bitstreamFont;
	static {
		try {
			bitstreamFont = Font.createFont(Font.TRUETYPE_FONT,
					MysticSquare.class
							.getResourceAsStream("/fonts/VeraMoBd.ttf"));
			bitstreamFont = new Font(bitstreamFont.getFontName(),
					java.awt.Font.BOLD, 13);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception ex) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {

			}
		}
		
	}

}
