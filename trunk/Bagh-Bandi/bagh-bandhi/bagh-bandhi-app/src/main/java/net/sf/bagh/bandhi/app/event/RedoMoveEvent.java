/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import java.util.EventObject;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class RedoMoveEvent extends EventObject {
	
	private final Object data;
	
	public RedoMoveEvent(Object source, Object data) {
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
