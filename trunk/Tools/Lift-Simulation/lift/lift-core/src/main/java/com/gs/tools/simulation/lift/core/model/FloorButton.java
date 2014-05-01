/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class FloorButton extends Button{

	private Floor location;

	/**
	 * @param name
	 */
	public FloorButton(String name) {
		super(name);
	}

	public Floor getLocation() {
		return location;
	}

	public void setLocation(Floor location) {
		this.location = location;
	}

	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
	
}
