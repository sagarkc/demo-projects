/**
 * 
 */
package com.gs.games.sudoku.desktop.event;

import java.util.EventObject;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class BaseUiEvent<O, N> extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4629583692182678176L;
	
	private O oldValue;
	private N newValue;
	
	/**
	 * @param source
	 */
	public BaseUiEvent(Object source) {
		super(source);
	}

	/**
	 * @return the oldValue
	 */
	public O getOldValue() {
		return oldValue;
	}

	/**
	 * @param oldValue the oldValue to set
	 */
	public void setOldValue(O oldValue) {
		this.oldValue = oldValue;
	}

	/**
	 * @return the newValue
	 */
	public N getNewValue() {
		return newValue;
	}

	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue(N newValue) {
		this.newValue = newValue;
	}

	
}
