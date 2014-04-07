/**
 * 
 */
package com.gs.games.mysticsquare.core;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface ValueProvider<T> {

	T[] getValues();
	
	void shuffle();
	
	void sort();
	
	T getValueAt(int index);
}
