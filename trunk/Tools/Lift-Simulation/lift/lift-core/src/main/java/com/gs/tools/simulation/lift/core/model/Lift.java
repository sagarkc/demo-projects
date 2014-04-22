/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

import java.text.DecimalFormat;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Lift {

	public static final String NAME_PREFIX = "LIFT#";
	public static final DecimalFormat THREE_DIGIT_FORMAT
		= new DecimalFormat("00");
	
	private final int liftNumber;
	private String name;
	
	private final double capacity;
	private int maxPassangerCount;
	
	/**
	 * @param liftNumber
	 * @param capacity
	 */
	public Lift(int liftNumber, double capacity) {
		this.liftNumber = liftNumber;
		this.capacity = capacity;
		this.name = NAME_PREFIX + THREE_DIGIT_FORMAT.format(liftNumber);
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

	/**
	 * @return the maxPassangerCount
	 */
	public int getMaxPassangerCount() {
		return maxPassangerCount;
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
