package com.gs.tools.colorhound;

import com.gs.tools.colorhound.ui.ColorHoundBaseFrame;
import java.awt.EventQueue;

/**
 * Hello world!
 *
 */
public class ColorHound 
{
    public static void main( String[] args )
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorHoundBaseFrame().setVisible(true);
            }
        });
    }
}
