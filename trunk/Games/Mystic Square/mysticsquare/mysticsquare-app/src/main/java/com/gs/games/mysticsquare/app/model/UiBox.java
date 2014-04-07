/**
 * 
 */
package com.gs.games.mysticsquare.app.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import com.gs.games.mysticsquare.app.board.BoxSizeEnum;
import com.gs.games.mysticsquare.app.board.Drawable;
import com.gs.games.mysticsquare.core.model.Box;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UiBox extends Box<Integer> implements Drawable{

	
	private Point position;
	private Color backgroundColor;
	private Color borderColor;
	private int borderThickness;
	

	public UiBox(int x, int y) {
		super(x, y);
		position = new Point();
		borderColor = Color.decode("0xE3B074");
		borderThickness = 2;
		
	}


	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * @return the borderThickness
	 */
	public int getBorderThickness() {
		return borderThickness;
	}

	/**
	 * @param borderThickness the borderThickness to set
	 */
	public void setBorderThickness(int borderThickness) {
		this.borderThickness = borderThickness;
	}

	/**
	 * @return the borderColor
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor the borderColor to set
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}


	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public void draw(Graphics g) {
		if(g == null)
			return;
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(hints);
		
		g2d.setColor(getBorderColor());
		g2d.fillRect(position.x, position.y, 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth(), 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight());
		
		g2d.setColor(getBackgroundColor());
		g2d.fillRect(position.x+borderThickness, position.y+borderThickness, 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth()-borderThickness-2, 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight()-borderThickness-2);
		if(getSquare() != null){
			getSquare()
		}
		
	}

	/**
	 * @param graphics
	 */
	public void drawAll(Graphics g) {
		
	}


	public void setDefaultBackgroundColor() {
		
	}


	/*protected UiBox getCommonNeighbourOnPath(UiBox toBox) {
		return super.getCommonNeighbourOnPath(toBox);
	}*/
}
