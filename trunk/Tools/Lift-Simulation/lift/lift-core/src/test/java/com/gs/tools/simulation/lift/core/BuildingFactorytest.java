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
public class BuildingFactorytest {

	
	public static void main(String[] args) {
		final BuildingFactory buildingFactory = BuildingFactory.getInstance();
		final Building building = buildingFactory.createBuilding("A", 10, 3);
		
		
		
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				building.requestLift(1);
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				
			}
		});
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
