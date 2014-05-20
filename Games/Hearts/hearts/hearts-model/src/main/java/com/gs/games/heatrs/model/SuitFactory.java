/**
 * 
 */
package com.gs.games.heatrs.model;

import com.gs.games.heatrs.commons.exceptions.IllegalSuitException;
import com.gs.games.heatrs.model.entity.Suit;
import com.gs.games.heatrs.model.entity.SuitTypeEnum;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class SuitFactory {

	private static volatile SuitFactory instance;
	
	private SuitFactory(){
		
	}

	public static SuitFactory getInstance() {
		if(null == instance)
			synchronized (SuitFactory.class) {
				if(null == instance)
					instance = new SuitFactory();
			}
		
		return instance;
	}

	/**
	 * @param i
	 * @return
	 */
	public Suit buildSuit(int suitValue) {
		SuitTypeEnum type = SuitTypeEnum.getByValue(suitValue);
		if(SuitTypeEnum.INVALID.equals(type))
			throw new IllegalSuitException();
		
		return new Suit(type);
	} 
	
}
