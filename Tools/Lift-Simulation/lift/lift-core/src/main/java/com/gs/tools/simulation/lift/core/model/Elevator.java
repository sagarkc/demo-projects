/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Elevator {

	private static final double DEFAULT_CAPACITY = 100D;
	private final int number;
	private final double capacity;
	
	private Set<Floor> blockedFloors;
	private Floor currentFloor;
	private Queue<Floor> requestedFloors;
	
	private ElevatorState state = ElevatorState.READY;
	
	/**
	 * @param number
	 */
	public Elevator(int number) {
		this(number, DEFAULT_CAPACITY);
	}

	/**
	 * @param number
	 * @param capacity
	 */
	public Elevator(int number, double capacity) {
		this.number = number;
		this.capacity = capacity;
		this.blockedFloors = new HashSet<>();
	}
	
	
	
	
}
