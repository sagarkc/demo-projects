/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

import java.text.DecimalFormat;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Floor {
	
	
	public static final String NAME_PREFIX = "FL#";
	public static final DecimalFormat THREE_DIGIT_FORMAT
		= new DecimalFormat("000");

	private final int floorNumber;
	private String name;
	private Button button;
	
	/**
	 * @param floorNumber
	 */
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		this.name = NAME_PREFIX + THREE_DIGIT_FORMAT.format(floorNumber);
	}

	/**
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
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

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + floorNumber;
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
		if (!(obj instanceof Floor)) {
			return false;
		}
		Floor other = (Floor) obj;
		if (floorNumber != other.floorNumber) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Floor [floorNumber= ");
		builder.append(floorNumber);
		builder.append(", name= ");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
