/**
 * 
 */
package com.gs.games.sudoku.desktop.event;

import com.gs.games.sudoku.desktop.model.UiCell;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class CellSelectionEvent extends BaseUiEvent<UiCell, UiCell> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8808978003497646255L;
	
	/**
	 * @param source
	 */
	public CellSelectionEvent(Object source) {
		super(source);
	}

	

	
}
