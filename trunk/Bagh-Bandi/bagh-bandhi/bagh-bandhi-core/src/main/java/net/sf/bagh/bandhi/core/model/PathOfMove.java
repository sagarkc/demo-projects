/**
 * 
 */
package net.sf.bagh.bandhi.core.model;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class PathOfMove {

	private Animal animal;
	private Animal capturedAnimal;
	private Box capturedBox;
	private Box movedFromBox;
	private Box currentBox;
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
	 * @return the capturedAnimal
	 */
	public Animal getCapturedAnimal() {
		return capturedAnimal;
	}
	/**
	 * @param capturedAnimal the capturedAnimal to set
	 */
	public void setCapturedAnimal(Animal capturedAnimal) {
		this.capturedAnimal = capturedAnimal;
	}
	/**
	 * @return the movedFromBox
	 */
	public Box getMovedFromBox() {
		return movedFromBox;
	}
	/**
	 * @param movedFromBox the movedFromBox to set
	 */
	public void setMovedFromBox(Box movedFromBox) {
		this.movedFromBox = movedFromBox;
	}
	/**
	 * @return the currentBox
	 */
	public Box getCurrentBox() {
		return currentBox;
	}
	/**
	 * @param currentBox the currentBox to set
	 */
	public void setCurrentBox(Box currentBox) {
		this.currentBox = currentBox;
	}
	public Box getCapturedBox() {
		return capturedBox;
	}
	public void setCapturedBox(Box capturedBox) {
		this.capturedBox = capturedBox;
	}
	
	
}
