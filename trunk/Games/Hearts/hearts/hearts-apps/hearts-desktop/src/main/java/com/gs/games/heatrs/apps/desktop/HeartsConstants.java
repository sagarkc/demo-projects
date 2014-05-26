/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.games.heatrs.apps.desktop;

import com.gs.games.heatrs.commons.ResourceBundleManager;

import java.awt.Color;
import java.util.ResourceBundle;

/**
 *
 * @author Sabuj
 */
public interface HeartsConstants {
    
    ResourceBundle bundle = ResourceBundleManager.getBundleManager().getResourceBundle();
    
    public interface WindowConstants{
        String HEARTS_WIN_TITLE = bundle.getString("hearts.win.title");
        String BRIDG_WIN_TITLE = bundle.getString("bridge.win.title");
    }
    
    public interface Colors{
    	Color CARD_BG = Color.decode("0x103B68");
    	Color CARD_BG_BORDER = Color.decode("0x689FC7");
    	
    	Color CARD_FG = Color.decode("0xEBDAA1");
    	Color CARD_FG_BORDER = Color.decode("0xAEA06F");
    	
    	Color CARD_SPADES = Color.decode("0x000000");
    	Color CARD_HEARTS = Color.decode("0xFF0000");
    	Color CARD_DIAMONDS = Color.decode("0x0000FF");
    	Color CARD_CLUBS = Color.decode("0x009900");
    }
}
