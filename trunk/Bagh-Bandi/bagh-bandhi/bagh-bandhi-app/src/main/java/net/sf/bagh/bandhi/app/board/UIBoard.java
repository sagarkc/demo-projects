/**
 * 
 */
package net.sf.bagh.bandhi.app.board;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Stack;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEditSupport;

import net.sf.bagh.bandhi.app.AnimalSizeEnum;
import net.sf.bagh.bandhi.app.BoxSizeEnum;
import net.sf.bagh.bandhi.app.GoatImageEnum;
import net.sf.bagh.bandhi.app.SizeFactorEnum;
import net.sf.bagh.bandhi.app.TigerImageEnum;
import net.sf.bagh.bandhi.app.event.RedoMoveEvent;
import net.sf.bagh.bandhi.app.event.RedoMoveEventManager;
import net.sf.bagh.bandhi.app.event.UndoMoveEvent;
import net.sf.bagh.bandhi.app.event.UndoMoveEventManager;
import net.sf.bagh.bandhi.app.event.UndoableMove;
import net.sf.bagh.bandhi.app.undo.UndoMoveEdit;
import net.sf.bagh.bandhi.core.GameEngine;
import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Board;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.Goat;
import net.sf.bagh.bandhi.core.model.PathOfMove;
import net.sf.bagh.bandhi.core.model.Tiger;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;
import net.sf.bagh.bandhi.core.util.PushStack;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UIBoard extends Board implements Drawable, Serializable {

	private static final GameEngine gameEngine = GameEngine.getEngine();
	
	public static int X = 20;
	public static int Y = 20;
	private UndoableEditSupport undoMoveSupport;
	
	public static SizeFactorEnum sizeFactorEnum;
	
	public UIBoard(Tiger[] tigers, Goat[] goats) {
		super(tigers, goats);
		if(null == sizeFactorEnum)
			sizeFactorEnum = SizeFactorEnum.NORMAL;
		undoMoveSupport = new UndoableEditSupport();
		initBoard();
	}

	public UndoableEditSupport getUndoMoveSupport() {
		return undoMoveSupport;
	}



	protected void initBoard() {
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				UiBox box = new UiBox(i, j);
				box.getPosition().x = X + (i * BoxSizeEnum.getValue(sizeFactorEnum).getWidth());
				box.getPosition().y = Y + (j * BoxSizeEnum.getValue(sizeFactorEnum).getHeight());
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
			updateGoatPosition(goats, box10, i);
			players1.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box10);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[1][0].setAnimals(players1);
		
		UiBox box12 = (UiBox) getBoxes()[1][2];
		Stack<Animal> players2 = new Stack<Animal>();
		for(int i=5; i < 10; i++){
			updateGoatPosition(goats, box12, i);
			players2.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box12);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[1][2].setAnimals(players2);
		
		UiBox box30 = (UiBox) getBoxes()[3][0];
		Stack<Animal> players3 = new Stack<Animal>();
		for(int i=10; i < 15; i++){
			updateGoatPosition(goats, box30, i);
			players3.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box30);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[3][0].setAnimals(players3);
		
		UiBox box32 = (UiBox) getBoxes()[3][2];
		Stack<Animal> players4 = new Stack<Animal>();
		for(int i=15; i < 20; i++){
			updateGoatPosition(goats, box32, i);
			players4.push(goats[i]);
			getAnimalBoxMap().put(goats[i], box32);
			getAvailableGoats().add(goats[i]);
		}
		getBoxes()[3][2].setAnimals(players4);
	}

	public void updateBoxDetails(){
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				UiBox box = (UiBox) getBoxAt(i, j);
				box.getPosition().x = X + (i * BoxSizeEnum.getValue(sizeFactorEnum).getWidth());
				box.getPosition().y = Y + (j * BoxSizeEnum.getValue(sizeFactorEnum).getHeight());
				
				getBoxes()[i][j] = box;
				if(AnimalType.NONE != box.getAnimalType()){
					if(null != box.getAnimals() && box.getAnimals().size() > 0){
						for (Animal animal : box.getAnimals()) {
							if(null != animal && animal instanceof UIGoat){
								((UIGoat)animal).setX(box.getPosition().x 
										+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
										- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
								((UIGoat)animal).setY(box.getPosition().y 
										+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
										- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));
								((UIGoat)animal).setBgImage(GoatImageEnum.getValue(sizeFactorEnum).getImage());
							}
						}
					} 
					if(null != box.getAnimal()){
						Animal animal = box.getAnimal();
						if(null != animal && animal instanceof UIGoat){
							((UIGoat)animal).setX(box.getPosition().x 
									+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
									- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
							((UIGoat)animal).setY(box.getPosition().y 
									+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
									- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));
							((UIGoat)animal).setBgImage(GoatImageEnum.getValue(sizeFactorEnum).getImage());
						}
						else if(null != animal && animal instanceof UITiger){
							((UITiger)animal).setX(box.getPosition().x 
									+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
									- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
							((UITiger)animal).setY(box.getPosition().y 
									+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
									- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));
							((UITiger)animal).setBgImage(TigerImageEnum.getValue(sizeFactorEnum).getImage());
						}
					}
				}
			}
		}
	}


	/**
	 * @param goats
	 * @param box10
	 * @param i
	 */
	public void updateGoatPosition(UIGoat[] goats, UiBox box10, int i) {
		goats[i].setX(box10.getPosition().x 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
		goats[i].setY(box10.getPosition().y 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));;
	}
	
	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.core.model.Board#putTigers()
	 */
	@Override
	protected void putTigers() {
		UiBox box1 = (UiBox) getBoxes()[2][1];
		UITiger tiger1 = (UITiger) getTigers()[0];
		tiger1.setX(box1.getPosition().x 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
		tiger1.setY(box1.getPosition().y 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));
		box1.setAnimal(tiger1);
		getAnimalBoxMap().put(getTigers()[0], getBoxes()[2][1]);
		
		UiBox box2 = (UiBox) getBoxes()[2][3];
		UITiger tiger2 = (UITiger) getTigers()[1];
		tiger2.setX(box2.getPosition().x 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
		tiger2.setY(box2.getPosition().y 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));
		box2.setAnimal(tiger2);
		getAnimalBoxMap().put(getTigers()[1], getBoxes()[2][3]);
	}
	
	
	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.app.board.Drawable#draw(java.awt.Graphics)
	 */
	public void draw(Graphics graphics) {
		
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
				if((x >= box.getPosition().x 
						&& x <= box.getPosition().x + BoxSizeEnum.getValue(sizeFactorEnum).getWidth())
						&& (y >= box.getPosition().y 
								&& y <= box.getPosition().y + BoxSizeEnum.getValue(sizeFactorEnum).getHeight())){
					return box;
				}
			}
		}
		return null;
	}


	/**
	 * For the first move of any animal, if the move is to the next empty neighbor, then that move is possible.
	 * For a tiger, at the first time, if it can capture a goat, that move is possible.
	 * <br/>
	 * For second time, only the Tiger can move if and only if the Tiger:
	 * <ol>
	 * 	<li>In the last move, the tiger captured a goat </li>
	 * 	<li>The tiger is not moving back or not moving any other empty box
	 * 		for the second time</li>
	 * <li>The tiger can capture one more goat</li>
	 * </ol>
	 * 
	 * @param previousSelectedBox
	 * @param box
	 * @return
	 */
	public boolean canBeMoved(UiBox fromBox, UiBox toBox) {
		if(AnimalType.GOAT == fromBox.getAnimalType()){
			if(gameEngine.isFirstMove(fromBox.getAnimal()) && fromBox.isEmptyNeighbor(toBox))
				return true;
			return false;
		}
		if(AnimalType.TIGER == fromBox.getAnimalType()){
			if(gameEngine.isFirstMove(fromBox.getAnimal()) ){
				if(fromBox.isEmptyNeighbor(toBox))
					return true;
				UiBox uiBox = (UiBox) fromBox.getCommonNeighbourOnPath(toBox);
				if(null != uiBox && AnimalType.GOAT == uiBox.getAnimalType()){
					return true;
				}
			}
			else {
				PathOfMove lastMove = gameEngine.getLastMove(fromBox.getAnimal());
				if(null != lastMove && null != lastMove.getCapturedAnimal()){
					// if jumping back - NOT POSSIBLE
					if(toBox.equals(lastMove.getMovedFromBox())){
						return false;
					}
					// if going to empty neighbor - NOT POSSIBLE
					if(fromBox.isEmptyNeighbor(toBox)){
						return false;
					}
					UiBox uiBox = (UiBox) fromBox.getCommonNeighbourOnPath(toBox);
					if(null != uiBox && AnimalType.GOAT == uiBox.getAnimalType()){
						return true;
					}
				}
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
		PathOfMove pathOfMove = new PathOfMove();
		if(null != fromBox && null != toBox){
			UiBox commonOnPath = (UiBox) fromBox.getCommonNeighbourOnPath(toBox);
			if(AnimalType.TIGER == fromBox.getAnimalType()){
				Animal animal = fromBox.getAnimal();
				pathOfMove.setAnimal(animal);
				toBox.setAnimal(animal);
				fromBox.setAnimal(null);
				if(null != animal && animal instanceof UITiger){
					updateTigerPosition(toBox, animal);
				}
				if(null != commonOnPath && AnimalType.GOAT == commonOnPath.getAnimalType()){
					if(commonOnPath.getAnimals() != null && commonOnPath.getAnimals().size() > 0){
						Goat goat = (Goat) commonOnPath.getAnimals().pop();
						getCapturedGoats().add(goat);
						pathOfMove.setCapturedAnimal(goat);
					} else {
						Goat goat = (Goat) commonOnPath.getAnimal();
						commonOnPath.setAnimal(null);
						getCapturedGoats().add(goat);
						pathOfMove.setCapturedAnimal(goat);
					}
				}
				success = true;
			} else if(AnimalType.GOAT == fromBox.getAnimalType()){
				if(fromBox.getAnimals() != null && fromBox.getAnimals().size() > 0){
					Animal animal = fromBox.getAnimals().pop();
					toBox.setAnimal(animal);
					pathOfMove.setAnimal(animal);
					pathOfMove.setCapturedAnimal(null);
					if(null != animal && animal instanceof UIGoat){
						updateGoatPosition(toBox, animal);
					}
					success = true;
				} else {
					Animal animal = fromBox.getAnimal();
					toBox.setAnimal(animal);
					pathOfMove.setAnimal(animal);
					pathOfMove.setCapturedAnimal(null);
					fromBox.setAnimal(null);
					if(null != animal && animal instanceof UIGoat){
						updateGoatPosition(toBox, animal);
					}
					success = true;
				}
			}
			
			if(success){
				pathOfMove.setMovedFromBox(fromBox);
				pathOfMove.setCurrentBox(toBox);
				gameEngine.getMovePaths().push(pathOfMove);
				UndoMoveEdit undoMoveEdit = new UndoMoveEdit(this, pathOfMove);
				undoMoveSupport.postEdit(undoMoveEdit);
				System.out.println("Moved from " + fromBox + " To " + toBox);
			}
		}
		return success;
	}


	/**
	 * @param box
	 * @param animal
	 */
	public void updateGoatPosition(UiBox box, Animal animal) {
		((UIGoat)animal).setX(box.getPosition().x 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
		((UIGoat)animal).setY(box.getPosition().y 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));
	}


	/**
	 * @param box
	 * @param animal
	 */
	public void updateTigerPosition(UiBox box, Animal animal) {
		((UITiger)animal).setX(box.getPosition().x 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getWidth() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getWidth() / 2));
		((UITiger)animal).setY(box.getPosition().y 
				+ (BoxSizeEnum.getValue(sizeFactorEnum).getHeight() / 2) 
				- (AnimalSizeEnum.getValue(sizeFactorEnum).getHeight() / 2));
	}


	/**
	 * @param lastMove
	 * @return
	 */
	public boolean undoMove(PathOfMove lastMove) {
		resetMove((UiBox)lastMove.getCurrentBox(), (UiBox)lastMove.getMovedFromBox(), lastMove.getCapturedAnimal());
		UndoMoveEvent event = new UndoMoveEvent(this, null);
		UndoMoveEventManager.getInstance().fireUndoMoveEvent(event);
		return true;
	}


	/**
	 * @param lastMove
	 * @return
	 */
	public boolean redoMove(PathOfMove lastMove) {
		resetMove((UiBox)lastMove.getMovedFromBox(), (UiBox)lastMove.getCurrentBox(), lastMove.getCapturedAnimal());
		RedoMoveEvent event = new RedoMoveEvent(this, null);
		RedoMoveEventManager.getInstance().fireRedoMoveEvent(event);
		return true;
	}

	/**
	 * @param movedFromBox
	 * @param currentBox
	 * @param capturedAnimal
	 */
	public void resetMove(UiBox fromBox, UiBox toBox,
			Animal capturedAnimal) {
		Animal animal = fromBox.getAnimal();
		toBox.setAnimal(animal);
		
	}

	
	

}
