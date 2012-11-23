/**
 * 
 */
package net.sf.bagh.bandhi;

import java.io.Serializable;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import net.sf.bagh.bandhi.app.board.UIBoard;
import net.sf.bagh.bandhi.app.event.RedoMoveEvent;
import net.sf.bagh.bandhi.app.event.RedoMoveEventManager;
import net.sf.bagh.bandhi.app.event.UndoMoveEvent;
import net.sf.bagh.bandhi.app.event.UndoMoveEventManager;
import net.sf.bagh.bandhi.app.event.UndoableMove;
import net.sf.bagh.bandhi.core.model.PathOfMove;
import net.sf.bagh.bandhi.core.util.PushStack;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Game implements Serializable, UndoableMove{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4827640103425248896L;
	private GameStatusEnum gameStatus = GameStatusEnum.UNKNOWN;
	private final UIBoard gameBoard;
	private final DifficultyLevelEnum difficultyLevel;
	private transient PushStack<PathOfMove> undoableMoves; 
	private transient PushStack<PathOfMove> redoableMoves; 
	
	private boolean hasBeenDone;
	private boolean alive;
	
	public Game(DifficultyLevelEnum difficultyLevel, UIBoard gameBoard) {
		this.gameStatus = GameStatusEnum.PLAYING;
		this.gameBoard = gameBoard;
		this.difficultyLevel = difficultyLevel;
		this.undoableMoves = new PushStack<PathOfMove>(difficultyLevel.getMaxUndoableMoves());
		this.redoableMoves = new PushStack<PathOfMove>(difficultyLevel.getMaxUndoableMoves());
		this.hasBeenDone = true;
		this.alive = true;
	}

	/**
	 * @return the gameStatus
	 */
	public GameStatusEnum getGameStatus() {
		return gameStatus;
	}

	/**
	 * @param gameStatus the gameStatus to set
	 */
	public void setGameStatus(GameStatusEnum gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * @return the gameBoard
	 */
	public UIBoard getGameBoard() {
		return gameBoard;
	}

	/**
	 * @return the undoableMoves
	 */
	public PushStack<PathOfMove> getUndoableMoves() {
		return undoableMoves;
	}

	/**
	 * @param undoableMoves the undoableMoves to set
	 */
	public void setUndoableMoves(PushStack<PathOfMove> undoableMoves) {
		this.undoableMoves = undoableMoves;
	}

	/**
	 * @return the redoableMoves
	 */
	public PushStack<PathOfMove> getRedoableMoves() {
		return redoableMoves;
	}

	/**
	 * @param redoableMoves the redoableMoves to set
	 */
	public void setRedoableMoves(PushStack<PathOfMove> redoableMoves) {
		this.redoableMoves = redoableMoves;
	}

	/**
	 * @return the difficultyLevel
	 */
	public DifficultyLevelEnum getDifficultyLevel() {
		return difficultyLevel;
	}

	@Override
	public void undo() throws CannotUndoException {
		if (!canUndo()) {
		    throw new CannotUndoException();
		}
		PathOfMove lastMove = getUndoableMoves().pop();
		if(null == lastMove)
			return;
		boolean moved = getGameBoard().undoMove(lastMove);
		if(moved){
			getRedoableMoves().push(lastMove);
			/*UndoMoveEvent event = new UndoMoveEvent(this, new Integer(getUndoableMoves().size()));
			UndoMoveEventManager.getInstance().fireUndoMoveEvent(event);*/
			hasBeenDone = false;
		} else {
			hasBeenDone = true;
		}
	}

	@Override
	public boolean canUndo() {
		return alive && hasBeenDone;
	}

	@Override
	public void redo() throws CannotRedoException {
		if (!canRedo()) {
		    throw new CannotRedoException();
		}
		PathOfMove lastMove = getRedoableMoves().pop();
		if(null == lastMove)
			return;
		boolean moved = getGameBoard().redoMove(lastMove);
		if(moved){
			getUndoableMoves().push(lastMove);
			/*RedoMoveEvent event = new RedoMoveEvent(this, new Integer(getRedoableMoves().size()));
			RedoMoveEventManager.getInstance().fireRedoMoveEvent(event);*/
			hasBeenDone = true;
		} else {
			hasBeenDone = false;
		}
	}

	@Override
	public boolean canRedo() {
		return alive && !hasBeenDone;
	}

	@Override
	public void die() {
		alive = false;
	}
	
	
}
