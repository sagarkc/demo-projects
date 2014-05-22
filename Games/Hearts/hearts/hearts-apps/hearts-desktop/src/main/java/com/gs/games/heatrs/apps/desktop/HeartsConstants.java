/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.games.heatrs.apps.desktop;

import com.gs.games.heatrs.commons.ResourceBundleManager;
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
    
}
