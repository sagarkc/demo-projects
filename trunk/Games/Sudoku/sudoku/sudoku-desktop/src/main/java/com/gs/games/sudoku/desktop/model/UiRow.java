/**
 * 
 */
package com.gs.games.sudoku.desktop.model;

import java.awt.Dimension;
import java.awt.Point;

import com.gs.games.sudoku.core.model.Row;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UiRow extends Row {

	private Dimension size;
	private Point location;
	
	/**
	 * @param number
	 */
	public UiRow(int number) {
		super(number);
	}

	/**
	 * @return the size
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

}
