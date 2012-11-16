/**
 * 
 */
package net.sf.bagh.bandhi.core.tree;

import net.sf.bagh.bandhi.core.model.Board;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Node {

	private Board board;
	private int turn;
	private Node son;
	private Node next;
	

	public Node(Board board) {
		this.board = board;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * @return the son
	 */
	public Node getSon() {
		return son;
	}

	/**
	 * @param son the son to set
	 */
	public void setSon(Node son) { 
		this.son = son;
	}

	/**
	 * @return the next
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
