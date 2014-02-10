/**
 * 
 */
package com.gs.games.sudoku.desktop.model;

import java.awt.Dimension;
import java.awt.Point;

import com.gs.games.sudoku.core.model.Cell;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UiCell extends Cell {

	private String name;
	private Dimension size;
	private Point location;
	
	/**
	 * @param x
	 * @param y
	 */
	public UiCell(int x, int y) {
		super(x, y);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
