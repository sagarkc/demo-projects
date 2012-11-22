/**
 * 
 */
package net.sf.bagh.bandhi;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum DifficultyLevelEnum {

	LOW(1, "LOW", 50),
	MODERATE(2, "MODERATE", 5),
	HIGH(1, "HIGH", 1);
	
	private final int value;
	private final String name;
	private final int maxUndoableMoves;
	
	private DifficultyLevelEnum(int value, String name, int maxUndoableMoves) {
		this.value = value;
		this.name = name;
		this.maxUndoableMoves = maxUndoableMoves;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the maxUndoableMoves
	 */
	public int getMaxUndoableMoves() {
		return maxUndoableMoves;
	}
	
	
	
}
