/**
 * 
 */
package net.sf.bagh.bandhi.app;

import javax.swing.ImageIcon;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public enum TigerImageEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, "circle-red-24x24.png"), 
	SHORT(SizeFactorEnum.SHORT, "circle-red-32x32.png"), 
	NORMAL(SizeFactorEnum.NORMAL, "circle-red-48x48.png"), 
	LARGE(SizeFactorEnum.LARGE, "circle-red-64x64.png"), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, "circle-red-128x128.png");

	private final SizeFactorEnum sizeFactor;
	private final String imageFileName;
	private ImageIcon imageIcon;

	/**
	 * @param sizeFactor
	 * @param imageFileName
	 */
	private TigerImageEnum(SizeFactorEnum sizeFactor, String imageFileName) {
		this.sizeFactor = sizeFactor;
		this.imageFileName = imageFileName;
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

	public static TigerImageEnum getValue(SizeFactorEnum boardSize) {
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
