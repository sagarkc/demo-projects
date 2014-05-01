/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Building {

	private final String name;
	private Set<Floor> floors;
	private Set<Lift> lifts;
	
	/**
	 * @param name
	 */
	public Building(String name) {
		this.name = name;
		this.floors = new LinkedHashSet<Floor>();
		this.lifts = new HashSet<Lift>();
	}

	public String getName() {
		return name;
	}

	public Set<Floor> getFloors() {
		return floors;
	}

	public Set<Lift> getLifts() {
		return lifts;
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
	
	
	
}
