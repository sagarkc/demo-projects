/**
 * 
 */
package com.gs.games.sudoku.core.model;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Column {

	public static final int CELL_COUNT = 9;
	private final int number;
	private final Cell[] cells = new Cell[CELL_COUNT];
	
	/**
	 * @param number
	 */
	public Column(int number) {
		this.number = number;
	}
	
	public void setCell(Cell cell){
		if(null != cell)
			cells[cell.getY()] = cell;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the cells
	 */
	public Cell[] getCells() {
		return cells;
	}
	
	public Cell getCell(int pos){
		if(pos >= 0 && pos <= 9)
			return cells[pos];
		return null;
	}
	
}
