/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xchanging.etl.mgr.desk;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Sabuj
 */
public interface EtlManagerConstants {
    String LAF_SYSTEM = UIManager.getSystemLookAndFeelClassName();
    String LAF_METAL = MetalLookAndFeel.class.getCanonicalName();
    String LAF_MOTIF = MotifLookAndFeel.class.getCanonicalName();
    String LAF_NIMBUS = NimbusLookAndFeel.class.getCanonicalName();
    String LAF_WINDOWS_CLASSIC = WindowsClassicLookAndFeel.class.getCanonicalName();
    String LAF_WINDOWS = WindowsLookAndFeel.class.getCanonicalName();
    
    Color LINK_BG_COLOR = new Color(153, 204, 255);
    Color LINK_FG_COLOR = Color.BLUE;
}
