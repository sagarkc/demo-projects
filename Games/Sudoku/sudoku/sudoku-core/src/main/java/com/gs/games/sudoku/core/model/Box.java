/**
 * 
 */
package com.gs.games.sudoku.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class Box {

	private final int x;
	private final int y;
	
	/**
	 * @param x
	 * @param y
	 */
	public Box(int x, int y) {
		if(x < ModelConstants.MIN_X || x > ModelConstants.MAX_X
				|| y < ModelConstants.MIN_Y || y > ModelConstants.MAX_Y)
			throw new IllegalArgumentException("Invalid cell identity provided: " + x + ", " + y);
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Box)) {
			return false;
		}
		Box other = (Box) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	
	
}
