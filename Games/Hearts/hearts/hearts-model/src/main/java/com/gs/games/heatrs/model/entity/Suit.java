/**
 * 
 */
package com.gs.games.heatrs.model.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Suit implements Serializable{

	private static final long serialVersionUID = 565165432165L;
	
	private final SuitTypeEnum suitType;
	private transient String displayText;
	private Set<Card> cards = new TreeSet<Card>(Card.DEFAULT_COMPARATOR);
	
	/**
	 * @param suitType
	 */
	public Suit(SuitTypeEnum suitType) {
		this.suitType = suitType;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public SuitTypeEnum getSuitType() {
		return suitType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((suitType == null) ? 0 : suitType.hashCode());
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
		if (!(obj instanceof Suit)) {
			return false;
		}
		Suit other = (Suit) obj;
		if (suitType != other.suitType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Suit [suitType=");
		builder.append(suitType);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @param c
	 */
	public void attachCard(Card c) {
		cards.add(c);
	}
	
	
}
