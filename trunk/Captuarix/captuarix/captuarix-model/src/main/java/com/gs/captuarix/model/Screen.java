/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.captuarix.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public final class Screen {

	public enum CursorType{
		BLACK, BLUE, WHITE, NONE
	}
	
	private final Point location;
	private final Dimension size;

	public Screen(Point location, Dimension size) {
		this.location = location;
		this.size = size;
	}

	public Point getLocation() {
		return location;
	}


	public Dimension getSize() {
		return size;
	}

	
	public static Screen defaultScreen 
			= new Screen(new Point(0,0), 
			Toolkit.getDefaultToolkit().getScreenSize());
	
}
