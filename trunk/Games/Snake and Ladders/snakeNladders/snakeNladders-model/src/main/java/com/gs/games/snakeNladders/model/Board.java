/**
 * 
 */
package com.gs.games.snakeNladders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Board {

	public static final int DEFAULT_BOARD_SIZE = 10;
	
	private List<Player> players = new LinkedList<>();
	private List<Square> squares = new ArrayList<>();
	private Map<Integer, Square> squareNumberMap = new HashMap<Integer, Square>();
	private int boardSize = DEFAULT_BOARD_SIZE;
	
	private List<Navigator> navigators = new ArrayList<>(); 
	
	public Board() {
		this(DEFAULT_BOARD_SIZE);
	}

	/**
	 * @param boardSize
	 */
	public Board(int boardSize) {
		this.boardSize = boardSize;
	}
	
	public static Board getDefaultBoard(){
		Board board = new Board();
		int size = board.boardSize * board.boardSize;
		for(int i=1; i <= size; i++){
			if(i == 1){
				EntrySquare entrySquare = new EntrySquare(i);
				board.squares.add(entrySquare);
				board.squareNumberMap.put(i, entrySquare);
			} else if(i == size){
				ExitSquare exitSquare = new ExitSquare(i);
				board.squares.add(exitSquare);
				board.squareNumberMap.put(i, exitSquare);
			} else {
				
			}
		}
		
		
		
		return board;
	}
}
