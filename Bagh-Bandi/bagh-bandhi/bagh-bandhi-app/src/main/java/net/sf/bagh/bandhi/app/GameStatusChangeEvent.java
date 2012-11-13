/**
 * 
 */
package net.sf.bagh.bandhi.app;

import java.util.EventObject;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class GameStatusChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5634261370133915585L;
	
	
	/**
	 * @param source
	 */
	public GameStatusChangeEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param source
	 * @param oldValue
	 * @param newValue
	 */
	private GameStatusChangeEvent(Object source, Object oldValue,
			Object newValue) {
		super(source);
	}

}
