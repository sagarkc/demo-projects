/**
 * 
 */
package net.sf.bagh.bandhi.app;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public enum GreenBoxImageEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, "box_green-32x32.png"), 
	SHORT(SizeFactorEnum.SHORT, "box_green-48x48.png"), 
	NORMAL(SizeFactorEnum.NORMAL, "box_green-64x64.png"), 
	LARGE(SizeFactorEnum.LARGE, "box_green-128x128.png"), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, "box_green-256x256.png");

	private final SizeFactorEnum sizeFactor;
	private final String imageFileName;
	private ImageIcon image;

	/**
	 * @param sizeFactor
	 * @param imageFileName
	 */
	private GreenBoxImageEnum(SizeFactorEnum sizeFactor, String imageFileName) {
		this.sizeFactor = sizeFactor;
		this.imageFileName = imageFileName;
		this.image = new ImageIcon(getClass().getResource(
				"/images/" + imageFileName));
	}

	/**
	 * @return the sizeFactor
	 */
	public SizeFactorEnum getSizeFactor() {
		return sizeFactor;
	}

	/**
	 * @return the imageFileName
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	public ImageIcon getImage() {
		return image;
	}

	public static GreenBoxImageEnum getValue(SizeFactorEnum boardSize) {
		if (SizeFactorEnum.EXTRA_SHORT == boardSize) {
			return EXTRA_SHORT;
		}
		if (SizeFactorEnum.SHORT == boardSize) {
			return SHORT;
		}
		if (SizeFactorEnum.LARGE == boardSize) {
			return LARGE;
		}
		if (SizeFactorEnum.EXTRA_LARGE == boardSize) {
			return EXTRA_LARGE;
		}
		return NORMAL;
	}
}
