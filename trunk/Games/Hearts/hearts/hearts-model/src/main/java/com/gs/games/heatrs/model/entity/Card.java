/**
 * 
 */
package com.gs.games.heatrs.model.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 565165432165L;
	
	public static final Comparator<Card> DEFAULT_COMPARATOR
		= new Comparator<Card>() {

			public int compare(Card c1, Card c2) {
				Integer cv1 = Integer.valueOf(c1.cardType.getValue());
				Integer cv2 = Integer.valueOf(c2.cardType.getValue());
				return cv1.compareTo(cv2);
			}
		
		};
	
	
	
	private final CardTypeEnum cardType;
	private final SuitTypeEnum suitType;
	private transient String displayText;
	
	
	/**
	 * @param cardType
	 * @param suitType
	 */
	public Card(CardTypeEnum cardType, SuitTypeEnum suitType) {
		this.cardType = cardType;
		this.suitType = suitType;
		this.displayText = cardType.getName() + " of " + suitType.getName();
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public CardTypeEnum getCardType() {
		return cardType;
	}

	public SuitTypeEnum getSuitType() {
		return suitType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardType == null) ? 0 : cardType.hashCode());
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
		if (!(obj instanceof Card)) {
			return false;
		}
		Card other = (Card) obj;
		if (cardType != other.cardType) {
			return false;
		}
		if (suitType != other.suitType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card [cardType=");
		builder.append(cardType);
		builder.append(", suitType=");
		builder.append(suitType);
		builder.append(", displayText=");
		builder.append(displayText);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
