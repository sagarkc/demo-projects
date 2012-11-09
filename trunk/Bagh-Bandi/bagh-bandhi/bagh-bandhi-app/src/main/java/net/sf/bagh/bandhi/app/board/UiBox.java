/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

import javax.swing.ImageIcon;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Box;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class UiBox extends Box implements Drawable{

	public static int WIDTH = 130;
	public static int HEIGHT = 130;
	
	private Point position;
	private ImageIcon bgImage;
	private int borderThickness;
	private Color borderColor;
	

	public UiBox(int x, int y) {
		super(x, y);
		position = new Point();
		bgImage = new ImageIcon(getClass().getResource(
				"/images/box_grey-128x128.png"));
		borderColor = Color.black;
		borderThickness = 2;
	}



	public void draw(Graphics g) {
		if(g == null)
			return;
		g.drawImage(bgImage.getImage(), position.x, position.y, 128, 128, null);
		Stack<Animal> animals = getAnimals();
		if(null != animals && animals.size() > 0){
			Animal animal = animals.peek();
			if(null != animal){
				if(animal instanceof UITiger){
					((UITiger) animal).draw(g);
				}
				else if(animal instanceof UIGoat){
					((UIGoat) animal).draw(g);
				}
			}
		}
		Animal animal = getAnimal();
		if(null != animal){
			if(animal instanceof UITiger){
				((UITiger) animal).draw(g);
			}
			else if(animal instanceof UIGoat){
				((UIGoat) animal).draw(g);
			}
		}
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
	 * @return the bgImage
	 */
	public ImageIcon getBgImage() {
		return bgImage;
	}

	/**
	 * @param bgImage the bgImage to set
	 */
	public void setBgImage(ImageIcon bgImage) {
		this.bgImage = bgImage;
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

}
