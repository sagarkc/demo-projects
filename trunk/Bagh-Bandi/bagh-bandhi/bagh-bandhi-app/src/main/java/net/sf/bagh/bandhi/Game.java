/**
 * 
 */
package net.sf.bagh.bandhi;

import java.io.Serializable;

import net.sf.bagh.bandhi.app.board.UIBoard;
import net.sf.bagh.bandhi.core.model.PathOfMove;
import net.sf.bagh.bandhi.core.util.PushStack;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4827640103425248896L;
	private GameStatusEnum gameStatus = GameStatusEnum.UNKNOWN;
	private final UIBoard gameBoard;
	private final DifficultyLevelEnum difficultyLevel;
	
	public Game(DifficultyLevelEnum difficultyLevel, UIBoard gameBoard) {
		this.gameStatus = GameStatusEnum.PLAYING;
		this.gameBoard = gameBoard;
		this.difficultyLevel = difficultyLevel;
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
	
	
}
