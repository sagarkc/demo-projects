/**
 * 
 */
package net.sf.bagh.bandhi.app;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum BoxSizeEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, 32, 32), 
	SHORT(SizeFactorEnum.SHORT, 48, 48),
	NORMAL(SizeFactorEnum.NORMAL, 64, 64),
	LARGE(SizeFactorEnum.LARGE, 130, 130), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, 0, 0);
	
	private final SizeFactorEnum sizeFactor;
	private final int width;
	private final int height;
	
	/**
	 * @param sizeFactor
	 * @param width
	 * @param height
	 */
	private BoxSizeEnum(SizeFactorEnum sizeFactor, int width, int height) {
		this.sizeFactor = sizeFactor;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the sizeFactor
	 */
	public SizeFactorEnum getSizeFactor() {
		return sizeFactor;
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
	
	public static BoxSizeEnum getValue(SizeFactorEnum boardSize) {
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
