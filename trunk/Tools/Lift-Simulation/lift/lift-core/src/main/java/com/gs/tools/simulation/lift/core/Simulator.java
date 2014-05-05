/**
 * 
 */
package com.gs.tools.simulation.lift.core;

import com.gs.tools.simulation.lift.core.model.Building;
import com.gs.tools.simulation.lift.core.model.Button;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Simulator {

	private final Building building;

	/**
	 * @param building
	 */
	public Simulator(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}
	
	public synchronized void requestLift(int floorNumber){
		final Button button = building.getFloorButton(floorNumber);
		if(null != button)
			button.press();
	}
	
}
