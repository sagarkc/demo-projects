/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;

import javax.swing.ImageIcon;

import net.sf.bagh.bandhi.AppConstants;
import net.sf.bagh.bandhi.app.BoxImageEnum;
import net.sf.bagh.bandhi.app.BoxSizeEnum;
import net.sf.bagh.bandhi.app.GreyBoxImageEnum;
import net.sf.bagh.bandhi.app.ImageSizeEnum;
import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Box;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class UiBox extends Box implements Drawable{

	
	private Point position;
	private ImageIcon bgImage;
	private Color backgroundColor;
	private int borderThickness;
	private Color borderColor;
	

	public UiBox(int x, int y) {
		super(x, y);
		position = new Point();
		bgImage = GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage();
		borderColor = Color.black;
		borderThickness = 2;
		if((x+y) % 2 == 0){
			backgroundColor = AppConstants.DEFAULT_DARK_BACKGROUND;
		} else {
			backgroundColor = AppConstants.DEFAULT_LIGHT_BACKGROUND;
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

	public Color getBackgroundColor() {
		return backgroundColor;
	}


	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public void draw(Graphics g) {
		if(g == null)
			return;
		/*g.drawImage(bgImage.getImage(), position.x, position.y, 
				ImageSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth(), 
				ImageSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight(), null);*/
		
		g.setColor(getBackgroundColor());
		
		g.fillRect(position.x, position.y, 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth(), 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight());
		
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
	 * @param graphics
	 */
	public void drawAll(Graphics g) {
		
	}


	public void setDefaultBackgroundColor() {
		if((getX()+getY()) % 2 == 0){
			backgroundColor = AppConstants.DEFAULT_DARK_BACKGROUND;
		} else {
			backgroundColor = AppConstants.DEFAULT_LIGHT_BACKGROUND;
		}
	}


	/*protected UiBox getCommonNeighbourOnPath(UiBox toBox) {
		return super.getCommonNeighbourOnPath(toBox);
	}*/

}
