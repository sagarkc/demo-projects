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

	void undo() throws CannotUndoException;
	
	boolean canUndo();
	
	void redo() throws CannotRedoException;
	
	boolean canRedo();
	
}
