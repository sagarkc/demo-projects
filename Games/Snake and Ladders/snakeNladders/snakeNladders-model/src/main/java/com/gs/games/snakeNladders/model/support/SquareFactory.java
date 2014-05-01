/**
 * 
 */
package com.gs.games.snakeNladders.model.support;

import com.gs.games.snakeNladders.model.NavigableSquare;
import com.gs.games.snakeNladders.model.Navigator;
import com.gs.games.snakeNladders.model.Square;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class SquareFactory {

	public Square createSquare(int number){
		return new Square(number);
	}
	
	public Square createNavigableSquare(int number, Navigator navigator){
		return new NavigableSquare(number, navigator);
	}
}
