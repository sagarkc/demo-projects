/**
 * 
 */
package com.gs.games.snakeNladders.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class Navigator {

	public static enum Type{SNAKE, LADDER};
	
	private Square source;
	private Square destination;
	
	private final Type type;

	/**
	 * @param type
	 */
	public Navigator(Type type) {
		this.type = type;
	}
	
	public abstract void navigate();

	public Square getSource() {
		return source;
	}

	public void setSource(Square source) {
		this.source = source;
	}

	public Square getDestination() {
		return destination;
	}

	public void setDestination(Square destination) {
		this.destination = destination;
	}

	public Type getType() {
		return type;
	}
	
	
}
