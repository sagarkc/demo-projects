/**
 * 
 */
package com.gs.games.sudoku.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Board {

	private final Block[][] blocks 	= new Block[ModelConstants.BLOCK_COUNT][ModelConstants.BLOCK_COUNT];
	private final Row[] 	rows 	= new Row[ModelConstants.ROW_COUNT];
	private final Column[] 	columns = new Column[ModelConstants.COLUMN_COUNT];
	
	private final Cell[][] 	cells 	= new Cell[ModelConstants.ROW_CELL_COUNT][ModelConstants.COLUMN_CELL_COUNT];
	
	
	
}
