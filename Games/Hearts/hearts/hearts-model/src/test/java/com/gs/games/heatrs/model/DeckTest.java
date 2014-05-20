/**
 * 
 */
package com.gs.games.heatrs.model;

import com.gs.games.heatrs.model.entity.Card;
import com.gs.games.heatrs.model.entity.Deck;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class DeckTest {

	public static void main(String[] args) {
		Deck deck = DeckFactory.getInstance().buildDeck();
		for (Card c : deck.getCards()) {
			System.out.println(c);
		}
	}
	
}
