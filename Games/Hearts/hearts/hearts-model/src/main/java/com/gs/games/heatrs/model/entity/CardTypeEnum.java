/**
 * 
 */
package com.gs.games.heatrs.model.entity;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum CardTypeEnum {

	ACE("ACE", 1, 13),
	TWO("TWO", 2, 1),
	THREE("THREE", 3, 2),
	FOUR("FOUR", 4, 3),
	FIVE("FIVE", 5, 4),
	SIX("SIX", 6, 5),
	SEVEN("SEVEN", 7, 6),
	EIGHT("EIGHT", 8, 7),
	NINE("NINE", 9, 8),
	TEN("TEN", 10, 9),
	JACK("JACK", 11, 10),
	QUEEN("QUEEN", 12,11),
	KING("KING", 13, 12);
	
	private final String name;
	private final int value;
	private final int power;
	

	/**
	 * @param name
	 * @param value
	 * @param power
	 */
	private CardTypeEnum(String name, int value, int power) {
		this.name = name;
		this.value = value;
		this.power = power;
	}


	public String getName() {
		return name;
	}


	public int getValue() {
		return value;
	}


	public int getPower() {
		return power;
	}

	
	
	
}
