/**
 * 
 */
package com.gs.games.heatrs.model;

import java.util.HashSet;
import java.util.Set;

import com.gs.games.heatrs.model.entity.Card;
import com.gs.games.heatrs.model.entity.CardTypeEnum;
import com.gs.games.heatrs.model.entity.Suit;
import com.gs.games.heatrs.model.entity.SuitTypeEnum;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class CardFactory {

	private static volatile CardFactory instance;
	private Set<Card> cardCache;
	
	private CardFactory(){
		cardCache = new HashSet<Card>();
	}

	public static CardFactory getInstance() {
		if(null == instance)
			synchronized (CardFactory.class) {
				if(null == instance)
					instance = new CardFactory();
			}
		
		return instance;
	}
	
	
	
	public synchronized Card buildCard(CardTypeEnum cardType, SuitTypeEnum suitType){
		
		return new Card(cardType, suitType);
	}

	/**
	 * @param card
	 * @param suit
	 * @return
	 */
	public synchronized Card buildCard(int cardValue, int suitValue) {
		return new Card(CardTypeEnum.getByValue(cardValue), SuitTypeEnum.getByValue(suitValue));
	}

	/**
	 * @param j
	 * @param suit
	 * @return
	 */
	public synchronized Card buildCard(int cardValue, Suit suit) {
		CardTypeEnum ct = CardTypeEnum.getByValue(cardValue);
		return new Card(ct, suit.getSuitType());
	}
	
}
