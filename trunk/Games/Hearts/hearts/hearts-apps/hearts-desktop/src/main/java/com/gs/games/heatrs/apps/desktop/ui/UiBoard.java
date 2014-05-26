/**
 * 
 */
package com.gs.games.heatrs.apps.desktop.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.gs.games.heatrs.model.entity.Hand;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class UiBoard extends JPanel {

	private Map<String, Hand> handNameMap = new HashMap<String, Hand>();
	private Hand[] hands;
	
	
	
	/**
	 * @param hands
	 */
	public UiBoard(Hand[] hands) {
		this.hands = hands;
	}

	public Map<String, Hand> getHandNameMap() {
		return handNameMap;
	}

	public Hand[] getHands() {
		return hands;
	}
	
	
	
	
}
