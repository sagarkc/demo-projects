/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import java.util.EventObject;

import net.sf.bagh.bandhi.GameStatusEnum;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class GameStatusChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5634261370133915585L;
	private GameStatusEnum gameStatus;
	private Object oldValue;
	private Object newValue;
	
	/**
	 * @param source
	 */
	public GameStatusChangeEvent(Object source) {
		super(source);
	}

	public GameStatusChangeEvent(Object source, GameStatusEnum gameStatus,
			Object oldValue) {
		super(source);
		this.gameStatus = gameStatus;
		this.oldValue = oldValue;
	}

	/**
	 * @param source
	 * @param oldValue
	 * @param newValue
	 */
	public GameStatusChangeEvent(Object source, GameStatusEnum statusEnum,
			Object oldValue,
			Object newValue) {
		super(source);
		this.gameStatus = statusEnum;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * @return the oldValue
	 */
	public Object getOldValue() {
		return oldValue;
	}

	/**
	 * @return the newValue
	 */
	public Object getNewValue() {
		return newValue;
	}

	/**
	 * @return the gameStatus
	 */
	public GameStatusEnum getGameStatus() {
		return gameStatus;
	}

}
