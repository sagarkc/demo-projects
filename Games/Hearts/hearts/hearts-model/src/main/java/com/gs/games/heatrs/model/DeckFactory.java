/**
 * 
 */
package com.gs.games.heatrs.model;

import com.gs.games.heatrs.model.entity.Card;
import com.gs.games.heatrs.model.entity.Deck;
import com.gs.games.heatrs.model.entity.Suit;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class DeckFactory {
	
	private static volatile DeckFactory instance;
	private static CardFactory cardFactory = CardFactory.getInstance();
	private static SuitFactory suitFactory = SuitFactory.getInstance();
	
	private DeckFactory(){
		
	}

	public static DeckFactory getInstance() {
		if(null == instance)
			synchronized (DeckFactory.class) {
				if(null == instance)
					instance = new DeckFactory();
			}
		
		return instance;
	}
	
	public synchronized Deck buildDeck(){
		Card[] cards = new Card[52];
		Suit[] suits = new Suit[4];
		for (int i = 0, k=0; i < 4; i++ ) {
			Suit suit = suitFactory.buildSuit(i+1);
			for (int j = 0; j < 13; j++) {
				Card c = cardFactory.buildCard(j+1, suit);
				suit.attachCard(c);
				cards[k] = c;
				k++;
			}
			suits[i] = suit;
		}
		
		
		Deck deck = new Deck(cards, suits);
		
		return deck;
	}
}
