/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.games.sudoku.desktop;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Sabuj das
 */
public interface SudokuUiConstants {
    Color LINK_BG_COLOR = new Color(153, 204, 255);
    Color LINK_FG_COLOR = Color.BLUE;
    
    String LOG_FILE_NAME = "./logs/application.log";
    String SETTINGS_FILE_NAME = "./.settings/sudoku.ini";
    
    String LAF_SYSTEM = UIManager.getSystemLookAndFeelClassName();
    String LAF_METAL = MetalLookAndFeel.class.getCanonicalName();
    String LAF_MOTIF = MotifLookAndFeel.class.getCanonicalName();
    String LAF_NIMBUS = NimbusLookAndFeel.class.getCanonicalName();
    String LAF_WINDOWS_CLASSIC = WindowsClassicLookAndFeel.class.getCanonicalName();
    String LAF_WINDOWS = WindowsLookAndFeel.class.getCanonicalName();
}
