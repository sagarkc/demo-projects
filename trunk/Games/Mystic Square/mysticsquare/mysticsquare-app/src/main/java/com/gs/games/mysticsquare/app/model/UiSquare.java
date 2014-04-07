/**
 * 
 */
package com.gs.games.mysticsquare.app.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import com.gs.games.mysticsquare.app.MysticSquare;
import com.gs.games.mysticsquare.app.board.Drawable;
import com.gs.games.mysticsquare.app.board.FontSizeEnum;
import com.gs.games.mysticsquare.app.board.SquareImageEnum;
import com.gs.games.mysticsquare.app.board.SquareSizeEnum;
import com.gs.games.mysticsquare.app.ui.GraphicsUtil;
import com.gs.games.mysticsquare.core.model.Square;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UiSquare extends Square<Integer> implements MouseListener,
	MouseMotionListener, Drawable{

	
	private int x, y;
	
	private ImageIcon bgImage = SquareImageEnum.getValue(UIBoard.sizeFactorEnum).getImage();
	
	
	
	/**
	 * @param x
	 * @param y
	 */
	public UiSquare(int x, int y) {
		super(x, y);
	}



	/* (non-Javadoc)
	 * @see com.gs.games.mysticsquare.app.board.Drawable#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) {
		if(null == g)
			return;
		g.drawImage(bgImage.getImage(), x, y, 
				SquareSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth(), 
				SquareSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight(), null);
		
		g.setColor(Color.WHITE);
		Font font = new Font(MysticSquare.bitstreamFont.getFontName(),
				java.awt.Font.BOLD, FontSizeEnum.getValue(UIBoard.sizeFactorEnum).getSize());
		g.setFont(font);
		int textHeight = GraphicsUtil.calculateTextHeight(g);
		int textWidth = GraphicsUtil.calculateTextWidth(g, getValue().toString());
		g.drawString(getValue().toString(), 
				x + (SquareSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth() / 2) - (textWidth / 2) + 2, 
				y + (SquareSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight() / 2) + (textHeight / 4) -2  
			);
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
