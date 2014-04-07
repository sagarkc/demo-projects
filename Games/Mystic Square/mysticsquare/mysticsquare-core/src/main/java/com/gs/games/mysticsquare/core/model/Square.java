/**
 * 
 */
package com.gs.games.mysticsquare.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Square<T> {

	private int x;
	private int y;
	
	private T value;
	
	private Box<T> containerBox;
	
	/**
	 * @param x
	 * @param y
	 */
	public Square(int x, int y) {
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
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @return the containerBox
	 */
	public Box<T> getContainerBox() {
		return containerBox;
	}

	/**
	 * @param containerBox the containerBox to set
	 */
	public void setContainerBox(Box<T> containerBox) {
		this.containerBox = containerBox;
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
		if (!(obj instanceof Square)) {
			return false;
		}
		Square other = (Square) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	
	
	
}
