/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import java.util.EventObject;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class UndoMoveEvent extends EventObject {
	
	private final Object data;
	
	public UndoMoveEvent(Object source, Object data) {
		super(source);
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	
}
