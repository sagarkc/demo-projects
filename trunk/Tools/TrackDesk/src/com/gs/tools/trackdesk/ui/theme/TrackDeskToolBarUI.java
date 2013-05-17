/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.trackdesk.ui.theme.TrackDeskToolBarUI
 * Date     : May 17, 2013
 */

package com.gs.tools.trackdesk.ui.theme;

import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarUI;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class TrackDeskToolBarUI extends BasicToolBarUI{

    public static ComponentUI createUI(JComponent c) {
	    return new TrackDeskToolBarUI((JToolBar) c);
	  }

    public TrackDeskToolBarUI(JToolBar jToolBar) {
        
    }
    
}
