/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import net.sf.bagh.bandhi.core.activity.Captureable;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class Goat extends Animal implements Captureable{

	/**
	 * 
	 */
	public Goat() {
		this("", 0);
	}

	public Goat(String name, int number) {
		super(name, number);
		setSymbol('G');
		setCanCapture(false);
	}

	public void moveTo(Box from, Box to) {
		// TODO Auto-generated method stub

	}

	public void moveTo(Box to) {
		// TODO Auto-generated method stub

	}

	
	public boolean canBeCaptured(Tiger tiger) {
		
		return false;
	}

}
