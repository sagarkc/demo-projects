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
	
	private Animal animal;
	private Stack<Animal> animals;
	private boolean visited;
	
	public enum BoxPosition{
		TOP,
		TOP_PEFT,
		LEFT,
		BOTTOM_LEFT,
		BOTTOM, 
		BOTTOM_RIGHT,
		RIGHT,
		TOP_RIGHT
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
	 * @return the animal
	 */
	public Animal getAnimal() {
		return animal;
	}

	/**
	 * @param animal the animal to set
	 */
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	/**
	 * @return the animals
	 */
	public Stack<Animal> getAnimals() {
		return animals;
	}

	/**
	 * @param animals the animals to set
	 */
	public void setAnimals(Stack<Animal> animals) {
		this.animals = animals;
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

	public boolean putAnimal(Animal animal){
		return true;
	}
	
	public boolean removeAnimal(Animal animal){
		return true;
	}
	
	public boolean isEmpty(){
		if(null != animals && animals.size() > 0)
			return false;
		if(null != animal)
			return false;
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
		
		if(null != topLeftBox && topLeftBox.isEmpty())
			return true;
		if(null != topRightBox && topRightBox.isEmpty())
			return true;
		if(null != bottomLeftBox && bottomLeftBox.isEmpty())
			return true;
		if(null != bottomRightBox && bottomRightBox.isEmpty())
			return true;
		
		return false;
	}
}
