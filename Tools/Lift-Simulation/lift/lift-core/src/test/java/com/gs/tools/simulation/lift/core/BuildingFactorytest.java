/**
 * 
 */
package com.gs.tools.simulation.lift.core;

import com.gs.tools.simulation.lift.core.model.Building;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class BuildingFactorytest {

	
	public static void main(String[] args) {
		final BuildingFactory buildingFactory = BuildingFactory.getInstance();
		
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Building building = buildingFactory.createBuilding("A", 10, 3);
				System.out.println(building.hashCode());
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Building building = buildingFactory.createBuilding("A", 10, 3);
				System.out.println(building.hashCode());
			}
		});
		
		t1.start();
		t2.start();
	}
}
