/**
 * 
 */
package com.gs.tools.simulation.lift.core;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.gs.tools.simulation.lift.core.model.Building;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Controller {
	
	private final Building building;
	
	private final Queue<Request> requestQueue;
	private final Queue<Response> responseQueue;
	
	private final ReentrantLock requestLock;
	private final Condition sendRequestCondition;
	private final Condition recieveRequestCondition;
	
	private final ReentrantLock responseLock;
	private final Condition sendRespCondition;
	private final Condition recieveRespCondition;
	
	
	/**
	 * 
	 */
	public Controller(final Building building) {
		this.building = building;
		
		requestQueue = new PriorityQueue<>(10);
		responseQueue = new PriorityQueue<>(10);
		requestLock = new ReentrantLock();
		sendRequestCondition = requestLock.newCondition();
		recieveRequestCondition = requestLock.newCondition();
		
		responseLock = new ReentrantLock();
		sendRespCondition = responseLock.newCondition();
		recieveRespCondition = responseLock.newCondition();
		
	}
	
	
	public Response processRequest(Request request){
		
		return null;
	}
	
}
