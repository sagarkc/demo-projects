/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.gs.tools.simulation.lift.core.Controller;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Building {

	private final String name;
	private Set<Floor> floors;
	private Set<Elevator> elevators;
	private final Controller controller;
	
	/**
	 * @param name
	 */
	public Building(String name) {
		this.name = name;
		this.floors = new LinkedHashSet<Floor>();
		this.elevators = new HashSet<>();
		this.controller = new Controller(this);
	}

	public String getName() {
		return name;
	}

	public Set<Floor> getFloors() {
		return floors;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Building)) {
			return false;
		}
		Building other = (Building) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Building [name= ");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	public Button getFloorButton(int floorNumber){
		Iterator<Floor> it = floors.iterator();
		while(it.hasNext()){
			Floor f = it.next();
			if(f.getFloorNumber() == floorNumber)
				return f.getButton();
		}
		return null;
	}
	
	
}
