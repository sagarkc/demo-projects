/**
 * 
 */
package net.sf.bagh.bandhi.app.undo;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import net.sf.bagh.bandhi.app.board.UIBoard;
import net.sf.bagh.bandhi.core.model.PathOfMove;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UndoMoveEdit extends AbstractUndoableEdit {

	private final UIBoard uiBoard;
	private final PathOfMove lastMove;
	
	public UndoMoveEdit(UIBoard uiBoard, PathOfMove lastMove) {
		this.uiBoard = uiBoard;
		this.lastMove = lastMove;
	}

	@Override
	public void undo() throws CannotUndoException {
		if(null != uiBoard && null != lastMove){
			super.undo();
			uiBoard.undoMove(lastMove);
		}
	}

	@Override
	public void redo() throws CannotRedoException {
		if(null != uiBoard && null != lastMove){
			super.redo();
			uiBoard.redoMove(lastMove);
		}
	}

	@Override
	public String getPresentationName() {
		return "Animal Movement";
	}
	
	
	
}
