/**
 * 
 */
package com.gs.games.heatrs.model.entity;

import com.gs.games.heatrs.model.Deck;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class HeartsDeck extends Deck {

	public static final int HAND_COUNT = 4;
	public static final int HAND_CARD_COUNT = 13;
	
	/**
	 * @param cards
	 * @param suits
	 */
	public HeartsDeck(Card[] cards, Suit[] suits) {
		super(cards, suits);
	}

	@Override
	public Hand[] distribute() {
		final Card[] cards = getCards();
		int i=0;
		final Hand[] hands = new Hand[HAND_COUNT];
		for (int j = 0; j < cards.length; j+=HAND_CARD_COUNT) {
			String name = "HAND_" + (i+1);
			Card[] handCards = new Card[HAND_CARD_COUNT];
			System.arraycopy(cards, j, handCards, 0, HAND_CARD_COUNT);
			
			Hand hand = new Hand(name, handCards);
			hands[i] = hand;
			i++;
		}
		return hands;
	}

}
