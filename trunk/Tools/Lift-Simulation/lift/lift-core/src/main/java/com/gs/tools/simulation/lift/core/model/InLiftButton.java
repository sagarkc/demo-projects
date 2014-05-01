/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class InLiftButton extends Button{

	private Lift lift;

	/**
	 * @param name
	 */
	public InLiftButton(String name) {
		super(name);
	}

	public Lift getLift() {
		return lift;
	}

	public void setLift(Lift lift) {
		this.lift = lift;
	}
	
	/* (non-Javadoc)
	 * @see com.gs.tools.simulation.lift.core.model.Button#doAction()
	 */
	public void doAction() {
		
	}
	
}
