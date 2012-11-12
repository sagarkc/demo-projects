/**
 * 
 */
package net.sf.bagh.bandhi.app;

import javax.swing.ImageIcon;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public enum GoatImageEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, "circle-blue-24x24.png"), 
	SHORT(SizeFactorEnum.SHORT, "circle-blue-32x32.png"), 
	NORMAL(SizeFactorEnum.NORMAL, "circle-blue-48x48.png"), 
	LARGE(SizeFactorEnum.LARGE, "circle-blue-64x64.png"), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, "circle-blue-128x128.png");

	private final SizeFactorEnum sizeFactor;
	private final String imageFileName;
	private ImageIcon image;

	/**
	 * @param sizeFactor
	 * @param imageFileName
	 */
	private GoatImageEnum(SizeFactorEnum sizeFactor, String imageFileName) {
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

	public static GoatImageEnum getValue(SizeFactorEnum boardSize) {
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
