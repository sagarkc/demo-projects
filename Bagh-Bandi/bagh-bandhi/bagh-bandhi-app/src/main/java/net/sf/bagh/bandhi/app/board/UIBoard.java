/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Graphics;
import java.util.Stack;

import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Board;
import net.sf.bagh.bandhi.core.model.Goat;
import net.sf.bagh.bandhi.core.model.Tiger;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UIBoard extends Board implements Drawable {

	public static int X = 20;
	public static int Y = 20;
	public static int WIDTH = 670;
	public static int HEIGHT = 670;
	
	
	
	public UIBoard(Tiger[] tigers, Goat[] goats) {
		super(tigers, goats);
		initBoard();
	}


	protected void initBoard() {
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				UiBox box = new UiBox(i, j);
				box.getPosition().x = X + (i * UiBox.WIDTH);
				box.getPosition().y = Y + (j * UiBox.HEIGHT);
				getBoxes()[i][j] = box;
			}
		}
		// add neighbours 
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				addNaighbour(getBoxes()[i][j]);
			}
		}
		
		putTigers();
		putGoats();
	}
	
	
	protected void putGoats() {
		UIGoat[] goats = (UIGoat[]) getGoats();
		Stack<Animal> players1 = new Stack<Animal>();
		UiBox box10 = (UiBox) getBoxes()[1][0];
		for(int i=0; i < 5; i++){
			goats[i].setX(box10.getPosition().x + (UiBox.WIDTH / 2) - (UITiger.WIDTH / 2));
			goats[i].setY(box10.getPosition().y + (UiBox.HEIGHT / 2) - (UITiger.HEIGHT / 2));
			players1.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box10);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[1][0].setAnimals(players1);
		
		UiBox box12 = (UiBox) getBoxes()[1][2];
		Stack<Animal> players2 = new Stack<Animal>();
		for(int i=5; i < 10; i++){
			goats[i].setX(box12.getPosition().x + (UiBox.WIDTH / 2) - (UITiger.WIDTH / 2));
			goats[i].setY(box12.getPosition().y + (UiBox.HEIGHT / 2) - (UITiger.HEIGHT / 2));
			players2.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box12);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[1][2].setAnimals(players2);
		
		UiBox box30 = (UiBox) getBoxes()[3][0];
		Stack<Animal> players3 = new Stack<Animal>();
		for(int i=10; i < 15; i++){
			goats[i].setX(box30.getPosition().x + (UiBox.WIDTH / 2) - (UITiger.WIDTH / 2));
			goats[i].setY(box30.getPosition().y + (UiBox.HEIGHT / 2) - (UITiger.HEIGHT / 2));
			players3.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box30);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[3][0].setAnimals(players3);
		
		UiBox box32 = (UiBox) getBoxes()[3][2];
		Stack<Animal> players4 = new Stack<Animal>();
		for(int i=15; i < 20; i++){
			goats[i].setX(box32.getPosition().x + (UiBox.WIDTH / 2) - (UITiger.WIDTH / 2));
			goats[i].setY(box32.getPosition().y + (UiBox.HEIGHT / 2) - (UITiger.HEIGHT / 2));
			players4.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box32);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[3][2].setAnimals(players4);
	}
	
	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.core.model.Board#putTigers()
	 */
	@Override
	protected void putTigers() {
		UiBox box1 = (UiBox) getBoxes()[2][1];
		UITiger tiger1 = (UITiger) getTigers()[0];
		tiger1.setX(box1.getPosition().x + (UiBox.WIDTH / 2) - (UITiger.WIDTH / 2));
		tiger1.setY(box1.getPosition().y + (UiBox.HEIGHT / 2) - (UITiger.HEIGHT / 2));
		box1.setAnimal(tiger1);
		getAnimalBoxMap().put(getTigers()[0], getBoxes()[2][1]);
		
		UiBox box2 = (UiBox) getBoxes()[2][3];
		UITiger tiger2 = (UITiger) getTigers()[1];
		tiger2.setX(box2.getPosition().x + (UiBox.WIDTH / 2) - (UITiger.WIDTH / 2));
		tiger2.setY(box2.getPosition().y + (UiBox.HEIGHT / 2) - (UITiger.HEIGHT / 2));
		box2.setAnimal(tiger2);
		getAnimalBoxMap().put(getTigers()[1], getBoxes()[2][3]);
	}
	
	
	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.app.board.Drawable#draw(java.awt.Graphics)
	 */
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param x - position on the screen
	 * @param y - position on the screen
	 * @return
	 */
	public UiBox getSelectedBox(int x, int y) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				final UiBox box = (UiBox) getBoxAt(i, j);
				if((x >= box.getPosition().x && x <= box.getPosition().x + UiBox.WIDTH)
						&& (y >= box.getPosition().y && y <= box.getPosition().y + UiBox.HEIGHT)){
					return box;
				}
			}
		}
		return null;
	}


	/**
	 * @param previousSelectedBox
	 * @param box
	 * @return
	 */
	public boolean canBeMoved(UiBox fromBox, UiBox toBox) {
		if(fromBox.isEmptyNeighbour(toBox)){
			return true;
		}
		if(AnimalType.TIGER == fromBox.getAnimalType()){
			UiBox uiBox = (UiBox) fromBox.getCommonNeighbourOnPath(toBox);
			if(null != uiBox && AnimalType.GOAT == uiBox.getAnimalType()){
				return true;
			}
		}
		
		return false;
	}


	/**
	 * @param previousSelectedBox
	 * @param box
	 */
	public boolean move(UiBox fromBox, UiBox toBox) {
		boolean success = false;
		if(null != fromBox && null != toBox){
			UiBox commonOnPath = (UiBox) fromBox.getCommonNeighbourOnPath(toBox);
			if(AnimalType.TIGER == fromBox.getAnimalType()){
				Animal animal = fromBox.getAnimal();
				toBox.setAnimal(animal);
				fromBox.setAnimal(null);
				if(null != animal && animal instanceof UITiger){
					((UITiger)animal).setX(toBox.getPosition().x + (UiBox.WIDTH / 2) - (UITiger.WIDTH / 2));
					((UITiger)animal).setY(toBox.getPosition().y + (UiBox.HEIGHT / 2) - (UITiger.HEIGHT / 2));
				}
				if(null != commonOnPath && AnimalType.GOAT == commonOnPath.getAnimalType()){
					if(commonOnPath.getAnimals() != null && commonOnPath.getAnimals().size() > 0){
						Goat goat = (Goat) commonOnPath.getAnimals().pop();
						getCapturedGoats().add(goat);
					} else {
						Goat goat = (Goat) commonOnPath.getAnimal();
						commonOnPath.setAnimal(null);
						getCapturedGoats().add(goat);
					}
				}
				success = true;
			} else if(AnimalType.GOAT == fromBox.getAnimalType()){
				if(fromBox.getAnimals() != null && fromBox.getAnimals().size() > 0){
					Animal animal = fromBox.getAnimals().pop();
					toBox.setAnimal(animal);
					if(null != animal && animal instanceof UIGoat){
						((UIGoat)animal).setX(toBox.getPosition().x + (UiBox.WIDTH / 2) - (UIGoat.WIDTH / 2));
						((UIGoat)animal).setY(toBox.getPosition().y + (UiBox.HEIGHT / 2) - (UIGoat.HEIGHT / 2));
					}
					success = true;
				} else {
					Animal animal = fromBox.getAnimal();
					toBox.setAnimal(animal);
					fromBox.setAnimal(null);
					if(null != animal && animal instanceof UIGoat){
						((UIGoat)animal).setX(toBox.getPosition().x + (UiBox.WIDTH / 2) - (UIGoat.WIDTH / 2));
						((UIGoat)animal).setY(toBox.getPosition().y + (UiBox.HEIGHT / 2) - (UIGoat.HEIGHT / 2));
					}
					success = true;
				}
			}
			
			if(success)
				System.out.println("Moved from " + fromBox + " To " + toBox);
		}
		return success;
	}

}
