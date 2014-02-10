/**
 * 
 */
package com.gs.games.sudoku.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum DifficultyLevel {

	BEGINNER(	1, "Beginner"),
	EASY(		2, "Easy"),
	MEDIUM(		3, "Medium"),
	HARD(		4, "Hard"),
	EXPERT(		5, "Expert");
	
	private final int key;
	private final String value;
	/**
	 * @param key
	 * @param value
	 */
	private DifficultyLevel(int key, String value) {
		this.key = key;
		this.value = value;
	}
	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	
}
