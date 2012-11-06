/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import java.util.Stack;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Box {

	private int x;
	private int y;
	 
	private Box topBox;
	private Box leftBox;
	private Box bottomBox;
	private Box rightBox;
	
	private Box topLeftBox;
	private Box topRightBox;
	private Box bottomLeftBox;
	private Box bottomRightBox;
	
	private Player player;
	private Stack<Player> players;
	private boolean visited;
	
	
	public Box(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the players
	 */
	public Stack<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(Stack<Player> players) {
		this.players = players;
	}

	/**
	 * @return the topBox
	 */
	public Box getTopBox() {
		return topBox;
	}

	/**
	 * @param topBox the topBox to set
	 */
	public void setTopBox(Box topBox) {
		this.topBox = topBox;
	}

	/**
	 * @return the leftBox
	 */
	public Box getLeftBox() {
		return leftBox;
	}

	/**
	 * @param leftBox the leftBox to set
	 */
	public void setLeftBox(Box leftBox) {
		this.leftBox = leftBox;
	}

	/**
	 * @return the bottomBox
	 */
	public Box getBottomBox() {
		return bottomBox;
	}

	/**
	 * @param bottomBox the bottomBox to set
	 */
	public void setBottomBox(Box bottomBox) {
		this.bottomBox = bottomBox;
	}

	/**
	 * @return the rightBox
	 */
	public Box getRightBox() {
		return rightBox;
	}

	/**
	 * @param rightBox the rightBox to set
	 */
	public void setRightBox(Box rightBox) {
		this.rightBox = rightBox;
	}

	/**
	 * @return the topLeftBox
	 */
	public Box getTopLeftBox() {
		return topLeftBox;
	}

	/**
	 * @param topLeftBox the topLeftBox to set
	 */
	public void setTopLeftBox(Box topLeftBox) {
		this.topLeftBox = topLeftBox;
	}

	/**
	 * @return the topRightBox
	 */
	public Box getTopRightBox() {
		return topRightBox;
	}

	/**
	 * @param topRightBox the topRightBox to set
	 */
	public void setTopRightBox(Box topRightBox) {
		this.topRightBox = topRightBox;
	}

	/**
	 * @return the bottomLeftBox
	 */
	public Box getBottomLeftBox() {
		return bottomLeftBox;
	}

	/**
	 * @param bottomLeftBox the bottomLeftBox to set
	 */
	public void setBottomLeftBox(Box bottomLeftBox) {
		this.bottomLeftBox = bottomLeftBox;
	}

	/**
	 * @return the bottomRightBox
	 */
	public Box getBottomRightBox() {
		return bottomRightBox;
	}

	/**
	 * @param bottomRightBox the bottomRightBox to set
	 */
	public void setBottomRightBox(Box bottomRightBox) {
		this.bottomRightBox = bottomRightBox;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Box [x=" + x + ", y=" + y + "]";
	}

	
	
}
