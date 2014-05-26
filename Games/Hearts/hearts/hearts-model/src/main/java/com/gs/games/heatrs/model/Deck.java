/**
 * 
 */
package com.gs.games.heatrs.model;

import java.util.Random;

import com.gs.games.heatrs.model.entity.Card;
import com.gs.games.heatrs.model.entity.Hand;
import com.gs.games.heatrs.model.entity.Suit;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public abstract class Deck {

	private final Card[] cards;
	private final Suit[] suits;
	
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

	public synchronized void shuffle(){
		int m = (cards.length-1);
		int n = 0;
		Random random = new Random();
		for (int i = 0; i < cards.length ; i++) {
			int p = 0;
			if(i % 2 == 0){
				p = n;
				n++;
			} else {
				p = m;
				m--;
			}
			
			if(p < 0)
				p = 0;
			if(p >= cards.length)
				p = cards.length-1;
			int q = random.nextInt(cards.length-1);
			swapCards(p, q);
		}
		
		
	}
	
	private void swapCards(final int i, final int j){
		final Card temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}
	
	public abstract Hand[] distribute();
	
}
