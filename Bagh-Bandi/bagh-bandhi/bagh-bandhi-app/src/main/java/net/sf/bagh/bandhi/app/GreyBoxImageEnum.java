/**
 * 
 */
package net.sf.bagh.bandhi.app;

import javax.swing.ImageIcon;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public enum GreyBoxImageEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, "box_grey-32x32.png", 32, 32), 
	SHORT(SizeFactorEnum.SHORT, "box_grey-48x48.png", 48, 48), 
	NORMAL(SizeFactorEnum.NORMAL, "box_grey-64x64.png", 64, 64), 
	LARGE(SizeFactorEnum.LARGE, "box_grey-128x128.png", 128, 128), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, "box_grey-256x256.png", 256, 256);

	private final SizeFactorEnum sizeFactor;
	private final String imageFileName;
	private ImageIcon imageIcon;
	private final int width;
	private final int height;


	/**
	 * @param sizeFactor
	 * @param imageFileName
	 * @param width
	 * @param height
	 */
	private GreyBoxImageEnum(SizeFactorEnum sizeFactor, String imageFileName,
			int width, int height) {
		this.sizeFactor = sizeFactor;
		this.imageFileName = imageFileName;
		this.width = width;
		this.height = height;
		this.imageIcon = new ImageIcon(getClass().getResource(
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
		return imageIcon;
	}

	

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	public static GreyBoxImageEnum getValue(SizeFactorEnum boardSize) {
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
