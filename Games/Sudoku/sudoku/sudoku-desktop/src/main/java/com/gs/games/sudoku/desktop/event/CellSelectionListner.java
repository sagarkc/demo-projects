/**
 * 
 */
package com.gs.games.sudoku.desktop.event;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface CellSelectionListner extends BaseEventListener{

	void cellSelected(CellSelectionEvent event);
	
}
