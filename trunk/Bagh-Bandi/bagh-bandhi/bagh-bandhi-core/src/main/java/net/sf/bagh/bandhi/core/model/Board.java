/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Board {

	private char[][] board = new char[5][5];
	private Player[] goats;
	private Player[] tigers;
	
	public Board(Player[] tigers, Player[] goats) {
		this.tigers = tigers;
		this.goats = goats;
		
		initBoard();
	}

	/**
	 * 
	 */
	private void initBoard() {
		
	}
	
	
	

}
