/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import java.util.Stack;

/**
 * 
 * 
 * 
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Board {

	private Box[][] boxes = new Box[5][5];
	private Player[] goats;
	private Player[] tigers;
	private Player selectedPlayer;
	private Player lastMovedPlayer;
	private Player nextPlayerToMove;
	
	public Board(Player[] tigers, Player[] goats) {
		this.tigers = tigers;
		this.goats = goats;
		
		initBoard();
	}

	/**
	 * @return the boxes
	 */
	public Box[][] getBoxes() {
		return boxes;
	}

	/**
	 * @param boxes the boxes to set
	 */
	public void setBoxes(Box[][] boxes) {
		this.boxes = boxes;
	}

	/**
	 * @return the goats
	 */
	public Player[] getGoats() {
		return goats;
	}

	/**
	 * @param goats the goats to set
	 */
	public void setGoats(Player[] goats) {
		this.goats = goats;
	}

	/**
	 * @return the tigers
	 */
	public Player[] getTigers() {
		return tigers;
	}

	/**
	 * @param tigers the tigers to set
	 */
	public void setTigers(Player[] tigers) {
		this.tigers = tigers;
	}

	/**
	 * @return the selectedPlayer
	 */
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	/**
	 * @param selectedPlayer the selectedPlayer to set
	 */
	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	/**
	 * @return the lastMovedPlayer
	 */
	public Player getLastMovedPlayer() {
		return lastMovedPlayer;
	}

	/**
	 * @param lastMovedPlayer the lastMovedPlayer to set
	 */
	public void setLastMovedPlayer(Player lastMovedPlayer) {
		this.lastMovedPlayer = lastMovedPlayer;
	}

	/**
	 * @return the nextPlayerToMove
	 */
	public Player getNextPlayerToMove() {
		return nextPlayerToMove;
	}

	/**
	 * @param nextPlayerToMove the nextPlayerToMove to set
	 */
	public void setNextPlayerToMove(Player nextPlayerToMove) {
		this.nextPlayerToMove = nextPlayerToMove;
	}

	/**
	 * 
	 */
	private void initBoard() {
		// init the boxes
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				boxes[i][j] = new Box(i, j);
			}
		}
		// add neighbours 
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				addNaighbour(boxes[i][j]);
			}
		}
		
		putTigers();
		putGoats();
	}
	
	
	/**
	 * 
	 */
	private void putGoats() {
		Stack<Player> players1 = new Stack<Player>();
		for(int i=0; i < 5; i++){
			players1.push(goats[i]);
		}
		boxes[1][0].setPlayers(players1);
		
		Stack<Player> players2 = new Stack<Player>();
		for(int i=5; i < 10; i++){
			players2.push(goats[i]);
		}
		boxes[1][2].setPlayers(players2);
		
		Stack<Player> players3 = new Stack<Player>();
		for(int i=10; i < 15; i++){
			players3.push(goats[i]);
		}
		boxes[3][0].setPlayers(players3);
		
		Stack<Player> players4 = new Stack<Player>();
		for(int i=15; i < 20; i++){
			players4.push(goats[i]);
		}
		boxes[1][2].setPlayers(players4);
	}

	/**
	 * 
	 */
	private void putTigers() {
		boxes[2][1].setPlayer(tigers[0]);
		boxes[2][3].setPlayer(tigers[1]);
	}

	private void addNaighbour(final Box box){
		if(null == box){
			return;
		}
		
		if(box.getX() == 0 && box.getY() == 0){
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
			box.setBottomRightBox(boxes[box.getX()+1][box.getY()+1]);
		} 
		if(box.getX() == 0 && box.getY() > 0 && box.getY() < 4){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
			if(box.getY() == 2){
				box.setTopRightBox(boxes[box.getX()+1][box.getY()-1]);
				box.setBottomRightBox(boxes[box.getX()+1][box.getY()+1]);
			}
		} 
		if(box.getX() == 0 && box.getY() == 4){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			box.setTopRightBox(boxes[box.getX()+1][box.getY()-1]);
		}
		
		
		if(box.getX() > 0 && box.getX() < 4 && box.getY() == 0){
			box.setLeftBox(boxes[box.getX()-1][box.getY()]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
			if(box.getX() == 2){
				box.setBottomLeftBox(boxes[box.getX()-1][box.getY()+1]);
				box.setBottomRightBox(boxes[box.getX()+1][box.getY()+1]);
			}
		} 
		if(box.getX() == 4 && box.getY() == 0){
			box.setLeftBox(boxes[box.getX()-1][box.getY()]);
			box.setBottomLeftBox(boxes[box.getX()-1][box.getY()+1]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
		}
		
		if(box.getX() == 4 && box.getY() > 0 && box.getY() < 4){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setLeftBox(boxes[box.getX()-1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
			if(box.getY() == 2){
				box.setTopLeftBox(boxes[box.getX()-1][box.getY()-1]);
				box.setBottomLeftBox(boxes[box.getX()-1][box.getY()+1]);
			}
		}
		
		if(box.getX() > 0 && box.getX() < 4 && box.getY() == 4){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setLeftBox(boxes[box.getX()-1][box.getY()]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			if(box.getX() == 2){
				box.setTopLeftBox(boxes[box.getX()-1][box.getY()-1]);
				box.setTopRightBox(boxes[box.getX()+1][box.getY()-1]);
			}
		}
		
		if((box.getX() == 1 && box.getY() == 1)
				|| (box.getX() == 1 && box.getY() == 3)
				|| (box.getX() == 3 && box.getY() == 1)
				|| (box.getX() == 3 && box.getY() == 3)
				|| (box.getX() == 2 && box.getY() == 2)){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setLeftBox(boxes[box.getX()-1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
			
			box.setTopLeftBox(boxes[box.getX()-1][box.getY()-1]);
			box.setTopRightBox(boxes[box.getX()+1][box.getY()-1]);
			box.setBottomLeftBox(boxes[box.getX()-1][box.getY()+1]);
			box.setBottomRightBox(boxes[box.getX()+1][box.getY()+1]);
		}
		
		if((box.getX() == 2 && box.getY() == 1)
				|| (box.getX() == 1 && box.getY() == 2)
				|| (box.getX() == 2 && box.getY() == 3)
				|| (box.getX() == 3 && box.getY() == 2)){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setLeftBox(boxes[box.getX()-1][box.getY()]);
			box.setBottomBox(boxes[box.getX()][box.getY()+1]);
			box.setRightBox(boxes[box.getX()+1][box.getY()]);
		}
	}
	
	public void movePlayer(Player player, Box toBox){
		if(player.equals(lastMovedPlayer)){
			System.out.println("This player is already moved!");
			return;
		}
		player.moveTo(toBox);
	}

}
