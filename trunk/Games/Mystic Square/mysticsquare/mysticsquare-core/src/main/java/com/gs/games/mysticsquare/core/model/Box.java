/**
 * 
 */
package com.gs.games.mysticsquare.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Box<T> {

	private int x;
	private int y;
	 
	private Box topBox;
	private Box leftBox;
	private Box bottomBox;
	private Box rightBox;
	
	private Square<T> square;
	private boolean visited;
	
	public enum BoxPosition{
		TOP,
		LEFT,
		BOTTOM, 
		RIGHT
	}
	
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
	 * @return the square
	 */
	public Square<T> getSquare() {
		return square;
	}

	/**
	 * @param square the square to set
	 */
	public void setSquare(Square<T> square) {
		this.square = square;
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



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Box [x=" + x + ", y=" + y + "]";
	}

	
	
	
	
	public boolean isEmpty(){
		
		return true;
	}
	
	public boolean hasEmptyNeighbour(){
		if(null != topBox && topBox.isEmpty())
			return true;
		if(null != leftBox && leftBox.isEmpty())
			return true;
		if(null != bottomBox && bottomBox.isEmpty())
			return true;
		if(null != rightBox && rightBox.isEmpty())
			return true;
		
		
		return false;
	}
	
	public boolean isEmptyNeighbor(Box other){
		if(null == other){
			return false;
		}
		if(!other.isEmpty()){
			return false;
		}
			
		if(other.equals(topBox))
			return true;
		if(other.equals(leftBox))
			return true;
		if(other.equals(bottomBox))
			return true;
		if(other.equals(rightBox))
			return true;
		
		
		return false;
	}
	
	public List<Box> getEmptyNeighbours(){
		List<Box> boxs = new ArrayList<Box>();
		if(null != topBox && topBox.isEmpty())
			boxs.add(topBox);
		if(null != leftBox && leftBox.isEmpty())
			boxs.add(leftBox);
		if(null != bottomBox && bottomBox.isEmpty())
			boxs.add(bottomBox);
		if(null != rightBox && rightBox.isEmpty())
			boxs.add(rightBox);
		
		
		return boxs;
	}
	
	
	
	public Box getCommonNeighbourOnPath(Box toBox) {
		if(null == toBox)
			return null;
		
		if(null != topBox && toBox.equals(topBox.topBox)){
			return topBox;
		}
		if(null != leftBox && toBox.equals(leftBox.leftBox)){
			return leftBox;
		}
		if(null != bottomBox && toBox.equals(bottomBox.bottomBox)){
			return bottomBox;
		}
		if(null != rightBox && toBox.equals(rightBox.rightBox)){
			return rightBox;
		}
		
		
		return null;
	}
}
