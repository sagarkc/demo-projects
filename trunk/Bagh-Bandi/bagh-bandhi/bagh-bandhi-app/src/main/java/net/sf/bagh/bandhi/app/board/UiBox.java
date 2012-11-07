/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;

import net.sf.bagh.bandhi.core.model.Box;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UiBox extends Box {

	private ImageIcon bgImage;
	private int borderThickness;
	private Color borderColor;
	private Point location;
	private Dimension size;
	
	/**
	 * @param x
	 * @param y
	 */
	public UiBox(int x, int y) {
		super(x, y);
		bgImage = new ImageIcon(getClass().getResource("/images/box_grey-128x128.png"));
		borderColor = Color.black;
		borderThickness = 2;
	}

	

}
