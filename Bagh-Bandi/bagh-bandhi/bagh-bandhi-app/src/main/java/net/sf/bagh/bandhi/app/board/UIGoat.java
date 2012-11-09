/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import net.sf.bagh.bandhi.core.model.Goat;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UIGoat extends Goat implements MouseListener, MouseMotionListener, Drawable {

	public static final int WIDTH = 48;
	public static final int HEIGHT = 48;
	private int x, y;
	private ImageIcon bgImage = new ImageIcon(getClass().getResource(
			"/images/circle_blue-48x48.png"));
	
	/**
	 * 
	 */
	public UIGoat() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param number
	 */
	public UIGoat(String name, int number) {
		super(name, number);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.app.board.Drawable#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) {
		if(null == g)
			return;
		g.drawImage(bgImage.getImage(), x, y, 48, 48, null);
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
