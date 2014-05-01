/**
 * 
 */
package com.gs.tools.simulation.lift.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.gs.tools.simulation.lift.core.model.Building;
import com.gs.tools.simulation.lift.core.model.Floor;
import com.gs.tools.simulation.lift.core.model.FloorButton;
import com.gs.tools.simulation.lift.core.model.Lift;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class BuildingFactory {

	private static BuildingFactory instance;
	private final Map<String, Building> buildingCache ;
	private final ReentrantLock buildingLock = new ReentrantLock();
	
	private BuildingFactory() {
		buildingCache = new HashMap<String, Building>();
	}
	
	public static BuildingFactory getInstance() {
		if(null == instance){
			synchronized (BuildingFactory.class) {
				if(null == instance){
					instance = new BuildingFactory();
				}
			}
		}
		return instance;
	}
	
	public synchronized void resetCache(){
		if(null != instance && null != buildingCache){
			buildingCache.clear();
		}
	}

	public Building createBuilding(String name, int floors, int lifts){
		try{
			if(buildingCache.containsKey(name))
				return buildingCache.get(name);
			buildingLock.lock();
			Building building = new Building(name);
			Floor gf = null;
			for (int i = 0; i < floors; i++) {
				Floor floor = new Floor(i);
				floor.setButton(new FloorButton("FB"+i));
				if(i==0)
					gf = floor;
				building.getFloors().add(floor);
			}
			for (int i = 0; i < lifts; i++) {
				Lift lift = new Lift(i, 100D);
				lift.setLocation(gf);
				building.getLifts().add(lift);
			}
			buildingCache.put(name, building);
			return building;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			buildingLock.unlock();
		}
		return null;
	}
	
	public Building getBuilding(String name){
		try{
			Building building = buildingCache.get(name);
			return building;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
}
