/**
 * 
 */
package com.gs.games.mysticsquare.app.board;

import javax.swing.ImageIcon;

import com.gs.games.mysticsquare.app.SizeFactorEnum;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public enum SquareImageEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, "circle-green-24x24.png"), 
	SHORT(SizeFactorEnum.SHORT, "circle-green-32x32.png"), 
	NORMAL(SizeFactorEnum.NORMAL, "circle-green-48x48.png"), 
	LARGE(SizeFactorEnum.LARGE, "circle-green-64x64.png"), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, "circle-green-128x128.png");

	private final SizeFactorEnum sizeFactor;
	private final String imageFileName;
	private ImageIcon imageIcon;

	/**
	 * @param sizeFactor
	 * @param imageFileName
	 */
	private SquareImageEnum(SizeFactorEnum sizeFactor, String imageFileName) {
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

	public static SquareImageEnum getValue(SizeFactorEnum boardSize) {
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
