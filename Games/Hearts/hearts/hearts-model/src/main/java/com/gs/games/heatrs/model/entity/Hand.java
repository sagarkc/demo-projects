/**
 * 
 */
package com.gs.games.heatrs.model.entity;

import java.util.Arrays;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Hand {

	private final String name;
	private final Card[] cards;
	
	/**
	 * @param name
	 * @param cards
	 */
	public Hand(String name, Card[] cards) {
		this.name = name;
		this.cards = cards;
	}

	public String getName() {
		return name;
	}

	public Card[] getCards() {
		return cards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Hand)) {
			return false;
		}
		Hand other = (Hand) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hand [name=");
		builder.append(name);
		builder.append(", cards=");
		builder.append(Arrays.toString(cards));
		builder.append("]");
		return builder.toString();
	}
	
	
}
