/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Stack;

import javax.swing.ImageIcon;

import net.sf.bagh.bandhi.AppConstants;
import net.sf.bagh.bandhi.BaghBandhiKhela;
import net.sf.bagh.bandhi.app.AnimalSizeEnum;
import net.sf.bagh.bandhi.app.BoxImageEnum;
import net.sf.bagh.bandhi.app.BoxSizeEnum;
import net.sf.bagh.bandhi.app.FontSizeEnum;
import net.sf.bagh.bandhi.app.GreyBoxImageEnum;
import net.sf.bagh.bandhi.app.ImageSizeEnum;
import net.sf.bagh.bandhi.app.util.DrawingUtil;
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
	private Color borderColor;
	private int borderThickness;
	

	public UiBox(int x, int y) {
		super(x, y);
		position = new Point();
		bgImage = GreyBoxImageEnum.getValue(UIBoard.sizeFactorEnum).getImage();
		borderColor = Color.black;
		borderThickness = 2;
		if((x+y) % 2 == 0){
			backgroundColor = AppConstants.DEFAULT_DARK_BACKGROUND;
			borderColor = AppConstants.DEFAULT_DARK_BACKGROUND;
		} else {
			backgroundColor = AppConstants.DEFAULT_LIGHT_BACKGROUND;
			borderColor = AppConstants.DEFAULT_LIGHT_BACKGROUND;
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
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(hints);
		/*g.drawImage(bgImage.getImage(), position.x, position.y, 
				ImageSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth(), 
				ImageSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight(), null);*/
		
		g2d.setColor(getBorderColor());
		g2d.fillRect(position.x, position.y, 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth(), 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight());
		
		g2d.setColor(getBackgroundColor());
		g2d.fillRect(position.x+borderThickness, position.y+borderThickness, 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getWidth()-borderThickness-2, 
				BoxSizeEnum.getValue(UIBoard.sizeFactorEnum).getHeight()-borderThickness-2);
		
		Stack<Animal> animals = getAnimals();
		if(null != animals && animals.size() > 0){
			Animal animal = animals.peek();
			if(null != animal){
				if(animal instanceof UITiger){
					((UITiger) animal).draw(g2d);
				}
				else if(animal instanceof UIGoat){
					((UIGoat) animal).draw(g2d);
				}
			}
			
			g2d.setColor(Color.BLACK);
			Font font = new Font(BaghBandhiKhela.bitstreamFont.getFontName(),
					java.awt.Font.BOLD, FontSizeEnum.getValue(UIBoard.sizeFactorEnum).getSize());
			g2d.setFont(font);
			int textHeight = DrawingUtil.calculateTextHeight(g);
			int textWidth  = DrawingUtil.calculateTextWidth(g, "" + animals.size());
			g2d.drawString("" + animals.size(), 
					getPosition().x + 2, 
					getPosition().y + ((int)(textHeight/1.5))  
				);
			
		}
		Animal animal = getAnimal();
		if(null != animal){
			if(animal instanceof UITiger){
				((UITiger) animal).draw(g2d);
			}
			else if(animal instanceof UIGoat){
				((UIGoat) animal).draw(g2d);
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
