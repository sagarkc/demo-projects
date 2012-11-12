/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import net.sf.bagh.bandhi.core.activity.Captureable;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;

/**
 * 
 * 
 * 
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Board {

	private Box[][] boxes = new Box[5][5];
	private final Goat[] goats;
	private final Tiger[] tigers;
	private Animal selectedAnimal;
	private Animal lastMovedAnimal;
	private Animal nextAnimalToMove;
	private Box selectedSourceBox;
	private Box selectedTargetBox;
	private List<Captureable> capturedGoats;
	private List<Captureable> availableGoats;
	private Map<Animal, Box> animalBoxMap;
	
	public Board(Tiger[] tigers, Goat[] goats) {
		this.tigers = tigers;
		this.goats = goats;
		this.capturedGoats = new ArrayList<Captureable>(20);
		this.availableGoats = new ArrayList<Captureable>(20);
		this.animalBoxMap = new HashMap<Animal, Box>();
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
	public Animal[] getGoats() {
		return goats;
	}

	/**
	 * @return the tigers
	 */
	public Animal[] getTigers() {
		return tigers;
	}

	/**
	 * @return the selectedAnimal
	 */
	public Animal getSelectedAnimal() {
		return selectedAnimal;
	}

	/**
	 * @param selectedAnimal the selectedAnimal to set
	 */
	public void setSelectedAnimal(Animal selectedAnimal) {
		this.selectedAnimal = selectedAnimal;
	}

	/**
	 * @return the lastMovedAnimal
	 */
	public Animal getLastMovedAnimal() {
		return lastMovedAnimal;
	}

	/**
	 * @param lastMovedAnimal the lastMovedAnimal to set
	 */
	public void setLastMovedAnimal(Animal lastMovedAnimal) {
		this.lastMovedAnimal = lastMovedAnimal;
	}

	/**
	 * @return the nextAnimalToMove
	 */
	public Animal getNextAnimalToMove() {
		return nextAnimalToMove;
	}

	/**
	 * @param nextAnimalToMove the nextAnimalToMove to set
	 */
	public void setNextAnimalToMove(Animal nextAnimalToMove) {
		this.nextAnimalToMove = nextAnimalToMove;
	}

	/**
	 * @return the selectedSourceBox
	 */
	public Box getSelectedSourceBox() {
		return selectedSourceBox;
	}

	/**
	 * @param selectedSourceBox the selectedSourceBox to set
	 */
	public void setSelectedSourceBox(Box selectedSourceBox) {
		this.selectedSourceBox = selectedSourceBox;
	}

	/**
	 * @return the selectedTargetBox
	 */
	public Box getSelectedTargetBox() {
		return selectedTargetBox;
	}

	/**
	 * @param selectedTargetBox the selectedTargetBox to set
	 */
	public void setSelectedTargetBox(Box selectedTargetBox) {
		this.selectedTargetBox = selectedTargetBox;
	}

	/**
	 * @return the capturedGoats
	 */
	public List<Captureable> getCapturedGoats() {
		return capturedGoats;
	}

	/**
	 * @param capturedGoats the capturedGoats to set
	 */
	public void setCapturedGoats(List<Captureable> capturedGoats) {
		this.capturedGoats = capturedGoats;
	}

	/**
	 * @return the availableGoats
	 */
	public List<Captureable> getAvailableGoats() {
		return availableGoats;
	}

	/**
	 * @param availableGoats the availableGoats to set
	 */
	public void setAvailableGoats(List<Captureable> availableGoats) {
		this.availableGoats = availableGoats;
	}

	/**
	 * @return the animalBoxMap
	 */
	public Map<Animal, Box> getAnimalBoxMap() {
		return animalBoxMap;
	}

	/**
	 * @param animalBoxMap the animalBoxMap to set
	 */
	public void setAnimalBoxMap(Map<Animal, Box> animalBoxMap) {
		this.animalBoxMap = animalBoxMap;
	}

	/**
	 * 
	 */
	protected void initBoard() {
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
	protected void putGoats() {
		Stack<Animal> players1 = new Stack<Animal>();
		for(int i=0; i < 5; i++){
			players1.push(goats[i]);
			animalBoxMap.put(goats[i], boxes[1][0]);
			availableGoats.add(goats[i]);
		}
		boxes[1][0].setAnimals(players1);
		
		Stack<Animal> players2 = new Stack<Animal>();
		for(int i=5; i < 10; i++){
			players2.push(goats[i]);
			animalBoxMap.put(goats[i], boxes[1][2]);
			availableGoats.add(goats[i]);
		}
		boxes[1][2].setAnimals(players2);
		
		Stack<Animal> players3 = new Stack<Animal>();
		for(int i=10; i < 15; i++){
			players3.push(goats[i]);
			animalBoxMap.put(goats[i], boxes[3][0]);
			availableGoats.add(goats[i]);
		}
		boxes[3][0].setAnimals(players3);
		
		Stack<Animal> players4 = new Stack<Animal>();
		for(int i=15; i < 20; i++){
			players4.push(goats[i]);
			animalBoxMap.put(goats[i], boxes[3][2]);
			availableGoats.add(goats[i]);
		}
		boxes[3][2].setAnimals(players4);
	}

	/**
	 * 
	 */
	protected void putTigers() {
		boxes[2][1].setAnimal(tigers[0]);
		animalBoxMap.put(tigers[0], boxes[2][1]);
		boxes[2][3].setAnimal(tigers[1]);
		animalBoxMap.put(tigers[1], boxes[2][3]);
	}

	protected void addNaighbour(final Box box){
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
		if(box.getX() == 4 && box.getY() == 4){
			box.setTopBox(boxes[box.getX()][box.getY()-1]);
			box.setLeftBox(boxes[box.getX()-1][box.getY()]);
			box.setTopLeftBox(boxes[box.getX()-1][box.getY()-1]);
		} 
	}
	
	

	public AnimalType evalute(){
		// all the goats are captured
		if(availableGoats.size() == 0 && capturedGoats.size() == 20){
			return AnimalType.TIGER;
		}
		Box box1 = animalBoxMap.get(tigers[0]);
		Box box2 = animalBoxMap.get(tigers[1]);
		if((null != box1 && !box1.hasEmptyNeighbour())
				&& (null != box2 && !box2.hasEmptyNeighbour())
				&& !tigers[0].canCaptureAnyGoat(box1)
				&& !tigers[1].canCaptureAnyGoat(box2)){
			return AnimalType.GOAT;
		}
		return AnimalType.NONE;
	}

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	public Box getBoxAt(int i, int j) {
		return boxes[i][j];
	}
	
	public boolean move(Box fromBox, Box toBox) {
		boolean success = false;
		if(null != fromBox && null != toBox){
			if(fromBox.getAnimals() != null && fromBox.getAnimals().size() > 0){
				Animal animal = fromBox.getAnimals().pop();
				toBox.setAnimal(animal);
				success = true;
			} else {
				Animal animal = fromBox.getAnimal();
				toBox.setAnimal(animal);
				fromBox.setAnimal(null);
				success = true;
			}
			System.out.println("Moved from " + fromBox + " To " + toBox);
		}
		return success;
	}
	
	public List<Animal> findAllCapturableAnimals(Box box){
		List<Animal> animals = new ArrayList<Animal>();
		if(null != box.getAnimals() && box.getAnimals().size() > 0){
			return animals;
		}
		Animal animal = box.getAnimal();
		if(null != animal && animal instanceof Tiger){
			
		}
		
		return animals;
	}
	
	
	
	public boolean hasMoreCapturableMoves(Animal animal, Box box){
		
		return false;
	}
	
	public List<Box> getAllCapturableMoves(Animal animal, Box box){
		
		return null;
	}
}




