/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import net.sf.bagh.bandhi.core.model.Animal.AnimalType;

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
	
	

	public Animal removeAnimal(Animal animal){
		
		return animal;
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
	
	public boolean isEmptyNeighbour(Box other){
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
		
		if(other.equals(topLeftBox))
			return true;
		if(other.equals(topRightBox))
			return true;
		if(other.equals(bottomLeftBox))
			return true;
		if(other.equals(bottomRightBox))
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
		
		if(null != topLeftBox && topLeftBox.isEmpty())
			boxs.add(topLeftBox);
		if(null != topRightBox && topRightBox.isEmpty())
			boxs.add(topRightBox);
		if(null != bottomLeftBox && bottomLeftBox.isEmpty())
			boxs.add(bottomLeftBox);
		if(null != bottomRightBox && bottomRightBox.isEmpty())
			boxs.add(bottomRightBox);
		
		return boxs;
	}
	
	public boolean hasAnimal(){
		if(null != animals && animals.size() > 0)
			return true;
		return (null != animal);
	}
	
	public AnimalType getAnimalType(){
		if(!hasAnimal())
			return AnimalType.NONE;
		if(null != animals && animals.size() > 0){
			Animal animal = animals.peek();
			if(null != animal && animal instanceof Goat)
				return AnimalType.GOAT;
			if(null != animal && animal instanceof Tiger)
				return AnimalType.TIGER;
		}
		if(null != animal && animal instanceof Goat)
			return AnimalType.GOAT;
		if(null != animal && animal instanceof Tiger)
			return AnimalType.TIGER;
		return AnimalType.NONE;
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
		
		if(null != topLeftBox && toBox.equals(topLeftBox.topLeftBox)){
			return topLeftBox;
		}
		if(null != topRightBox && toBox.equals(topRightBox.topRightBox)){
			return topRightBox;
		}
		if(null != bottomLeftBox && toBox.equals(bottomLeftBox.bottomLeftBox)){
			return bottomLeftBox;
		}
		if(null != bottomRightBox && toBox.equals(bottomRightBox.bottomRightBox)){
			return bottomRightBox;
		}
		
		return null;
	}
}
