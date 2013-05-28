package com.gs.tools.colorhound;

import com.gs.tools.colorhound.ui.ColorHoundBaseFrame;
import java.awt.EventQueue;
import java.util.Locale;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Hello world!
 *
 */
public class ColorHound 
{
    public static void main( String[] args )
    {
        try{
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (Exception ex){}
        //Locale.setDefault(new Locale("es"));
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorHoundBaseFrame().setVisible(true);
            }
        });
    }
}
