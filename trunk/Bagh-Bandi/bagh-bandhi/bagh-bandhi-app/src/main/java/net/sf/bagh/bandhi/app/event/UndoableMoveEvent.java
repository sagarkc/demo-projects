/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import java.util.EventObject;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class UndoableMoveEvent extends EventObject {

	private final int undoableMoveCount;
	private final int redoableMoveCount;
	
	public UndoableMoveEvent(Object source) {
		super(source);
		this.undoableMoveCount = 0;
		this.redoableMoveCount = 0;
	}

	public UndoableMoveEvent(Object source, int undoableMoveCount,
			int redoableMoveCount) {
		super(source);
		this.undoableMoveCount = undoableMoveCount;
		this.redoableMoveCount = redoableMoveCount;
	}

	/**
	 * @return the undoableMoveCount
	 */
	public int getUndoableMoveCount() {
		return undoableMoveCount;
	}

	/**
	 * @return the redoableMoveCount
	 */
	public int getRedoableMoveCount() {
		return redoableMoveCount;
	}

	
	
}
