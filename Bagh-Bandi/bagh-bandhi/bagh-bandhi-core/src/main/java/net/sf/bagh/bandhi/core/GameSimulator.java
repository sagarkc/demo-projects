/**
 * 
 */
package net.sf.bagh.bandhi.core;

import net.sf.bagh.bandhi.core.exception.EndOfGameException;
import net.sf.bagh.bandhi.core.exception.InvalidMoveException;
import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Board;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.Player;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class GameSimulator {

	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Board board;
	
	private Player previousPlayer;
	private Player currentPlayer;
	private Player nextPlayer;

	public GameSimulator(Player firstPlayer, Player secondPlayer, Board board) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.board = board;
	}

	/**
	 * @return the previousPlayer
	 */
	public Player getPreviousPlayer() {
		return previousPlayer;
	}

	/**
	 * @param previousPlayer the previousPlayer to set
	 */
	public void setPreviousPlayer(Player previousPlayer) {
		this.previousPlayer = previousPlayer;
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
	 * @return the nextPlayer
	 */
	public Player getNextPlayer() {
		return nextPlayer;
	}

	/**
	 * @param nextPlayer the nextPlayer to set
	 */
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	/**
	 * @return the firstPlayer
	 */
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * @return the secondPlayer
	 */
	public Player getSecondPlayer() {
		return secondPlayer;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	
	// step 1
	public abstract Animal selectAnimal(Player player);
	// step 2
	public abstract Box selectTargetBox(Player player);
	// step 3
	public boolean validateTargetBox(Player player, Animal animal, Box box){
		
		return true;
	}
	// step 4
	public abstract boolean doNextMove(Player player, Animal animal, Box from, Box to) throws InvalidMoveException;

	/**
	 * @param player
	 * @param animal
	 * @param box
	 * @throws InvalidMoveException
	 */
	public abstract boolean doNextMove(Player player, Animal animal, Box box) throws InvalidMoveException, EndOfGameException ;
}
