/**
 * 
 */
package net.sf.bagh.bandhi.app;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum AnimalSizeEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, 24, 24), 
	SHORT(SizeFactorEnum.SHORT, 32, 32),
	NORMAL(SizeFactorEnum.NORMAL, 48, 48),
	LARGE(SizeFactorEnum.LARGE, 64, 64), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, 128, 128);
	
	private final SizeFactorEnum sizeFactor;
	private final int width;
	private final int height;
	
	/**
	 * @param sizeFactor
	 * @param width
	 * @param height
	 */
	private AnimalSizeEnum(SizeFactorEnum sizeFactor, int width, int height) {
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
	
	public static AnimalSizeEnum getValue(SizeFactorEnum boardSize) {
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
