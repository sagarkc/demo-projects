/**
 * 
 */
package net.sf.bagh.bandhi;

import net.sf.bagh.bandhi.app.event.RedoMoveEvent;
import net.sf.bagh.bandhi.app.event.RedoMoveEventManager;
import net.sf.bagh.bandhi.app.event.UndoMoveEvent;
import net.sf.bagh.bandhi.app.event.UndoMoveEventManager;
import net.sf.bagh.bandhi.core.GameEngine;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.PathOfMove;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class GamePlayManager {

	private static final GameEngine gameEngine = GameEngine.getEngine();
	
	private Game game;
	
	private static GamePlayManager instance;
	private GamePlayManager(){}
	
	/**
	 * @return the instance
	 */
	public static GamePlayManager getInstance() {
		if(null == instance){
			synchronized (GamePlayManager.class) {
				instance = new GamePlayManager();
			}
		}
		return instance;
	}
	
	public boolean startNewGame(Game game, boolean replace){
		if(null == this.game){
			this.game = game;
			return true;
		}
		if(!replace){
			return false;
		}
		this.game = game;
		return true;
	}
	
	public void removeGame(){
		this.game = null;
	}
	
	public boolean isGameInPlay(){
		return (null != game 
				&& (GameStatusEnum.PLAYING.equals(game.getGameStatus())
						|| GameStatusEnum.PAUSED.equals(game.getGameStatus()))
				);
	}

	public void addUndoablemove(PathOfMove lastMove){
		if(isGameInPlay()){
			game.getUndoableMoves().push(lastMove);
		}
	}
	
	public void addRedoablemove(PathOfMove lastMove){
		if(isGameInPlay()){
			game.getRedoableMoves().push(lastMove);
		}
	}
	
	public void undoLastMove(){
		if(!isGameInPlay())
			return;
		game.undo();
	}
	
	public void redoLastMove(){
		if(!isGameInPlay())
			return;
		game.redo();
	}

	/**
	 * @return
	 */
	public boolean canUndo() {
		if(!isGameInPlay())
			return false;
		return game.canUndo();
	}

	/**
	 * @return
	 */
	public boolean canRedo() {
		if(!isGameInPlay())
			return false;
		return game.canRedo();
	}
	
	
}
