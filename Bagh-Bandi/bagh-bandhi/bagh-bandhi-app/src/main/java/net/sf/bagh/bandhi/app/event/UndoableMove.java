/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface UndoableMove {

	String UNDO_MOVE_NAME = "UNDO_MOVE";
	String REDO_MOVE_NAME = "REDO_MOVE";
	
	void undo() throws CannotUndoException;
	
	boolean canUndo();
	
	void redo() throws CannotRedoException;
	
	boolean canRedo();
	
	void die();
}
