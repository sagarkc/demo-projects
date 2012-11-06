/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class Tiger extends Player {

	/**
	 * 
	 */
	public Tiger() {
		this("Tiger", 0);
	}

	public Tiger(String name, int number) {
		super(name, number);
		setSymbol('T');
		setCanCapture(true);
	}

	public void moveTo(Box from, Box to) {
		// TODO Auto-generated method stub

	}

	public void moveTo(Box to) {
		// TODO Auto-generated method stub

	}

}
