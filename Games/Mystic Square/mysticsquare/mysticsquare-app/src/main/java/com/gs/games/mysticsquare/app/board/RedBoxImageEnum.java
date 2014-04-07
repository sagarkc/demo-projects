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
public enum RedBoxImageEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, "box_red-32x32.png"), 
	SHORT(SizeFactorEnum.SHORT, "box_red-48x48.png"), 
	NORMAL(SizeFactorEnum.NORMAL, "box_red-64x64.png"), 
	LARGE(SizeFactorEnum.LARGE, "box_red-128x128.png"), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, "box_red-256x256.png");

	private final SizeFactorEnum sizeFactor;
	private final String imageFileName;
	private ImageIcon image;

	/**
	 * @param sizeFactor
	 * @param imageFileName
	 */
	private RedBoxImageEnum(SizeFactorEnum sizeFactor, String imageFileName) {
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

	public static RedBoxImageEnum getValue(SizeFactorEnum boardSize) {
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
