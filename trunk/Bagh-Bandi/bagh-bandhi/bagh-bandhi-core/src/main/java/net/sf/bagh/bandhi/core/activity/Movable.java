/**
 * 
 */
package net.sf.bagh.bandhi.core.activity;

import net.sf.bagh.bandhi.core.model.Box;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public interface Movable {

	void moveTo(Box to);
	void moveTo(Box from, Box to);
}
