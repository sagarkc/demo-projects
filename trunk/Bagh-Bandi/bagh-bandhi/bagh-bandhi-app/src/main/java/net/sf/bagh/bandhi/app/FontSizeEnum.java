/**
 * 
 */
package net.sf.bagh.bandhi.app;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum FontSizeEnum {

	EXTRA_SHORT(SizeFactorEnum.EXTRA_SHORT, 9), 
	SHORT(SizeFactorEnum.SHORT, 13),
	NORMAL(SizeFactorEnum.NORMAL, 15),
	LARGE(SizeFactorEnum.LARGE, 21), 
	EXTRA_LARGE(SizeFactorEnum.EXTRA_LARGE, 25);
	
	private final SizeFactorEnum sizeFactor;
	private final int size;
	
	/**
	 * @param sizeFactor
	 * @param width
	 * @param height
	 */
	private FontSizeEnum(SizeFactorEnum sizeFactor, int size) {
		this.sizeFactor = sizeFactor;
		this.size = size;
	}

	/**
	 * @return the sizeFactor
	 */
	public SizeFactorEnum getSizeFactor() {
		return sizeFactor;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	public static FontSizeEnum getValue(SizeFactorEnum boardSize) {
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
