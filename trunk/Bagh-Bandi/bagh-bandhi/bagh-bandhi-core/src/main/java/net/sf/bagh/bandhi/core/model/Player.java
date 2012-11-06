/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class Player {

	private char symbol = '-';
	
	/**
	 * 
	 */
	public Player() {
		// TODO Auto-generated constructor stub
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

}
