/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class Passenger {

	private final int id;

	private PassengerLocation location;
	private Floor floor;

	/**
	 * @param id
	 */
	public Passenger(int id) {
		this.id = id;
	}

	public PassengerLocation getLocation() {
		return location;
	}

	public void setLocation(PassengerLocation location) {
		this.location = location;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (!(obj instanceof Passenger)) {
			return false;
		}
		Passenger other = (Passenger) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passenger [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}
