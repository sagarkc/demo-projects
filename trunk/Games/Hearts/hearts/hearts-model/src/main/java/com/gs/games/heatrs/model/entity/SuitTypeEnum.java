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
	CLUBS(4, "CLUBS"),
	INVALID(-999, "INVALID");
	
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
	
	public static SuitTypeEnum getByValue(int value){
		switch (value) {
		case 1:
			return SPADES;
		case 2:
			return HEARTS;
		case 3:
			return DIAMONDS;
		case 4:
			return CLUBS;
		default:
			return INVALID;
		}
	}
	
}
