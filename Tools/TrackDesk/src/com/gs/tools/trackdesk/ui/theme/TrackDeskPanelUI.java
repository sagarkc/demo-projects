/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.trackdesk.ui.theme;

import com.gs.tools.trackdesk.ui.UiConstants;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 *
 * @author Sabuj
 */
public class TrackDeskPanelUI extends BasicPanelUI{

    public TrackDeskPanelUI(JPanel panel) {
        panel.setBackground(UiConstants.PanelColors.BACKGROUND);
    }
    
    
}
