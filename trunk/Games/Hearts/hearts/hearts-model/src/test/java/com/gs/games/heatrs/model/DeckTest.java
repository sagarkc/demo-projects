/**
 * 
 */
package com.gs.games.heatrs.model;

import com.gs.games.heatrs.model.entity.Card;
import com.gs.games.heatrs.model.entity.Hand;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class DeckTest {

	public static void main(String[] args) {
		Deck deck = DeckFactory.getInstance().buildHeartsDeck();
		deck.shuffle();
		
		for (Card c : deck.getCards()) {
			System.out.println(c);
		}
		
		Hand[] hands = deck.distribute();
		for (Hand hand : hands) {
			System.out.println(hand);
		}
	}
	
}
