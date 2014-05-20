/**
 * 
 */
package com.gs.games.heatrs.model.entity;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class Deck {

	private final Card[] cards;
	private final Suit[] suits;
	
	private int hands;

	/**
	 * @param cards
	 * @param suits
	 */
	public Deck(Card[] cards, Suit[] suits) {
		this.cards = cards;
		this.suits = suits;
	}

	public Card[] getCards() {
		return cards;
	}

	public Suit[] getSuits() {
		return suits;
	}

}
