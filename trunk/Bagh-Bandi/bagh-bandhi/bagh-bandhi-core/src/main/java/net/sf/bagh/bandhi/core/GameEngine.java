/**
 * 
 */
package net.sf.bagh.bandhi.core;

import java.util.Stack;

import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;
import net.sf.bagh.bandhi.core.model.Human;
import net.sf.bagh.bandhi.core.model.PathOfMove;
import net.sf.bagh.bandhi.core.model.Player;

/**
 * Game Engine
 * 
 * @author Sabuj Das | sabuj.das@gmail.com
 *  
 */
public final class GameEngine {

	private static GameEngine engine;
	private GameEngine(){}
	
	/* ----------------------------------------------------------------- */
	
	public static final Human MANUAL_TIGER_PLAYER = new Human("A", AnimalType.TIGER);
	public static final Human MANUAL_GOAT_PLAYER = new Human("B", AnimalType.GOAT);
	
	private GameSimulator gameSimulator;
	private Player currentPlayer = MANUAL_TIGER_PLAYER;
	private Human firstPlayer = MANUAL_TIGER_PLAYER;
	private Human secondPlayer = MANUAL_GOAT_PLAYER;
	private Player[] players = new Player[2];
	private int currentPlayerIndex = 0;
	
	private Stack<PathOfMove> movePaths = new Stack<PathOfMove>();
	/* ----------------------------------------------------------------- */
	
	
	
	/**
	 * @return the engine
	 */
	public static GameEngine getEngine() {
		if(null != engine)
			return engine;
		synchronized (GameEngine.class) {
			engine = new GameEngine();
		}
		return engine;
	}
	
	public static void resetEngine() {
		synchronized (GameEngine.class) {
			engine = new GameEngine();
		}
	}
	
	/**
	 * @return the gameSimulator
	 */
	public GameSimulator getGameSimulator() {
		return gameSimulator;
	}
	
	/**
	 * @param gameSimulator the gameSimulator to set
	 */
	public void setGameSimulator(GameSimulator gameSimulator) {
		this.gameSimulator = gameSimulator;
	}

	/**
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the firstPlayer
	 */
	public Human getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * @param firstPlayer the firstPlayer to set
	 */
	public void setFirstPlayer(Human firstPlayer) {
		this.firstPlayer = firstPlayer;
		this.players[0] = this.firstPlayer;
	}

	/**
	 * @return the secondPlayer
	 */
	public Human getSecondPlayer() {
		return secondPlayer;
	}

	/**
	 * @param secondPlayer the secondPlayer to set
	 */
	public void setSecondPlayer(Human secondPlayer) {
		this.secondPlayer = secondPlayer;
		this.players[1] = this.secondPlayer;
	}


	/**
	 * @return the currentPlayerIndex
	 */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	/**
	 * @param currentPlayerIndex the currentPlayerIndex to set
	 */
	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}
	
	/**
	 * @return the movePaths
	 */
	public Stack<PathOfMove> getMovePaths() {
		return movePaths;
	}

	/**
	 * @param movePaths the movePaths to set
	 */
	public void setMovePaths(Stack<PathOfMove> movePaths) {
		this.movePaths = movePaths;
	}

	public void shiftNextPlayer(){
		if(currentPlayerIndex == 0){
			currentPlayerIndex = 1;
		} else if(currentPlayerIndex == 1){
			currentPlayerIndex = 0;
		}
		currentPlayer = players[currentPlayerIndex];
	}

	/**
	 * @param animal
	 * @return
	 */
	public boolean isFirstMove(Animal animal) {
		if(null != animal && getMovePaths().size() > 0 ){
			PathOfMove lastMove = getMovePaths().peek();
			// has one last move by same animal
			if(null != lastMove && animal.equals(lastMove.getAnimal())){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param animal
	 * @return
	 */
	public PathOfMove getLastMove(Animal animal) {
		if(null != animal && getMovePaths().size() > 0 ){
			PathOfMove lastMove = getMovePaths().peek();
			// has one last move by same animal
			if(null != lastMove && animal.equals(lastMove.getAnimal())){
				return lastMove;
			}
		}
		return null;
	}

	/**
	 * @param animalType
	 */
	public PathOfMove getLastMove() {
		if(null != getMovePaths() && getMovePaths().size() > 0 ){
			return getMovePaths().peek();
		}
		return null;
	}

}
