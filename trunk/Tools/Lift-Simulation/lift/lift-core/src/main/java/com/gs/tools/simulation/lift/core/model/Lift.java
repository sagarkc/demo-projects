/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Lift {

	public static final String NAME_PREFIX = "LIFT#";
	public static final DecimalFormat TWO_DIGIT_FORMAT
		= new DecimalFormat("00");
	
	private final int liftNumber;
	private final double capacity;
	
	private String name;
	private LiftState state;
	private Floor location;
	private Door door;
	private List<Floor> blockedFloors;
	private Set<Button> buttons;
	
	/**
	 * @param liftNumber
	 * @param capacity
	 */
	public Lift(int liftNumber, double capacity) {
		this.liftNumber = liftNumber;
		this.capacity = capacity;
		this.name = NAME_PREFIX + TWO_DIGIT_FORMAT.format(liftNumber);
		this.door = new Door();
		this.state = LiftState.IDLE;
		this.blockedFloors = new ArrayList<Floor>();
		this.buttons = new TreeSet<Button>();
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
	 * @return the liftNumber
	 */
	public int getLiftNumber() {
		return liftNumber;
	}

	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	public LiftState getState() {
		return state;
	}

	public void setState(LiftState state) {
		this.state = state;
	}

	public Floor getLocation() {
		return location;
	}

	public void setLocation(Floor location) {
		this.location = location;
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	public List<Floor> getBlockedFloors() {
		return blockedFloors;
	}

	public void setBlockedFloors(List<Floor> blockedFloors) {
		this.blockedFloors = blockedFloors;
	}

	public Set<Button> getButtons() {
		return buttons;
	}

	public void setButtons(Set<Button> buttons) {
		this.buttons = buttons;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lift [liftNumber=");
		builder.append(liftNumber);
		builder.append(", name=");
		builder.append(name);
		builder.append(", capacity=");
		builder.append(capacity);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + liftNumber;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Lift)) {
			return false;
		}
		Lift other = (Lift) obj;
		if (liftNumber != other.liftNumber) {
			return false;
		}
		return true;
	}
	
	
	
	
}
