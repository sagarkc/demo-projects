/**
 * 
 */
package com.gs.games.sudoku.core.model;

/**
 * A cell must have a (x,y) co-ordinate.
 * The cell may or mey not have a value.
 * The value must be >=1 and <= 9. By default the value is 0
 * to represent an empty cell.
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Cell extends Box{
	
	private int value = ModelConstants.EMPTY_CELL_VALUE;
	private boolean valueProvided;
	
	/**
	 * @param x
	 * @param y
	 */
	public Cell(int x, int y) {
		super(x, y);
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		if(value >= ModelConstants.MIN_VALUE && value <= ModelConstants.MAX_VALUE)
			this.value = value;
	}

	/**
	 * @return the valueProvided
	 */
	public boolean isValueProvided() {
		return valueProvided;
	}

	/**
	 * @param valueProvided the valueProvided to set
	 */
	public void setValueProvided(boolean valueProvided) {
		this.valueProvided = valueProvided;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(getX()).append(", ").append(getY())
				.append(": ").append(value).append("]");
		return builder.toString();
	}
	
	
	public boolean isEmpty(){
		return (value == ModelConstants.EMPTY_CELL_VALUE);
	}
}
