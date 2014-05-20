/**
 * 
 */
package com.gs.games.heatrs.model.entity;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum SuitTypeEnum {

	SPADES(1, "SPADES"),
	HEARTS(2, "HEARTS"),
	DIAMONDS(3, "DIAMONDS"),
	CLUBS(4, "CLUBS");
	
	private final int value;
	private final String name;
	
	/**
	 * @param value
	 * @param name
	 */
	private SuitTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	
}
