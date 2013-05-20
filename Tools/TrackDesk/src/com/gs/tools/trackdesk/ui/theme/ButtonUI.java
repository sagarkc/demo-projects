/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.trackdesk.ui.theme;

import com.gs.tools.trackdesk.ui.UiConstants;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Sabuj
 */
public class ButtonUI extends BasicButtonUI{
    
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();
    
    public static ComponentUI createUI(JComponent c) {
        return new ButtonUI((JButton) c);
    }

    public ButtonUI(JButton jButton) {
        //jButton.setFont(UiConstants.Fonts.TAHOMA_PLAIN.deriveFont(Font.PLAIN,  10.7F));
    }

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void installListeners(AbstractButton b) {
        super.installListeners(b); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
