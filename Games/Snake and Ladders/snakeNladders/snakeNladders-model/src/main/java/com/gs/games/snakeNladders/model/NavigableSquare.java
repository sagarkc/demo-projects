/**
 * 
 */
package com.gs.games.snakeNladders.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class NavigableSquare extends Square {

	private final Navigator navigator;
	
	/**
	 * @param number
	 */
	public NavigableSquare(int number, Navigator navigator) {
		super(number);
		this.navigator = navigator;
	}

}
