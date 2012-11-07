/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import net.sf.bagh.bandhi.core.activity.Movable;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class Animal implements Movable{

	private char symbol = '-';
	private String name;
	private int number;
	private boolean canCapture;
	
	public enum AnimalType{
		TIGER, GOAT, NONE
	};
	
	/**
	 * 
	 */
	public Animal() {
		this("Player_0", 0);
	}

	public Animal(String name, int number) {
		this.name = name;
		this.number = number;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the canCapture
	 */
	public boolean isCanCapture() {
		return canCapture;
	}

	/**
	 * @param canCapture the canCapture to set
	 */
	public void setCanCapture(boolean canCapture) {
		this.canCapture = canCapture;
	}

	/**
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [symbol=" + symbol + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + symbol;
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
		if (!(obj instanceof Animal)) {
			return false;
		}
		Animal other = (Animal) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		if (symbol != other.symbol) {
			return false;
		}
		return true;
	}

	

	
}
