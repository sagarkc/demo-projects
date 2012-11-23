/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import java.util.EventObject;

import net.sf.bagh.bandhi.core.model.PathOfMove;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class RedoMoveEvent extends EventObject {
	
	private final PathOfMove lastMove;
	
	public RedoMoveEvent(Object source, PathOfMove data) {
		super(source);
		this.lastMove = data;
	}

	/**
	 * @return the data
	 */
	public PathOfMove getData() {
		return lastMove;
	}
	
}
