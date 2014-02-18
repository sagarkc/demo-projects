/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.games.sudoku.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

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
    
    public interface BoardValues{
    	int MIN_CELL_WIDTH = 25;
    	int MIN_CELL_HEIGHT = 25;
    	
    	int LINE_CELL_GAP = 3;
    	
    	int MIN_WIDTH = 255;
    	int MIN_HEIGHT = 255;
    	
    	int CORNER_RADIUS = 3;
    }
    
    public interface BoardColors{
    	Color BOARD_BG = Color.decode("0xF1F5FB");
    	
    	Color CELL_SEPARATOR = Color.decode("0x4C5C6E");
    	Color BLOCK_SEPARATOR = Color.decode("0x4C5C6E");
    	
    	Color FIXED_INPUT = Color.decode("0x195495");
    	Color USER_INOUT = Color.decode("0x003B15");
    	Color INVALID_INPUT = Color.decode("0xAC3806");
    	
    	Color CELL_FOCUS_TOP = Color.decode("0xDCEBFC");
    	Color CELL_FOCUS_BOTTOM = Color.decode("0xC1DBFC");
    	Color CELL_FOCUS_OUTER_BORDER = Color.decode("0x7DA2CE");
    	Color CELL_FOCUS_INNER_BORDER = Color.decode("0xDBEAFD");
    	
    	Color CELL_HOVER_TOP = Color.decode("0xFAFCFD");
    	Color CELL_HOVER_BOTTOM = Color.decode("0xEBF3FD");
    	Color CELL_HOVER_OUTER_BORDER = Color.decode("0xB8D6FB");
    	Color CELL_HOVER_INNER_BORDER = Color.decode("0xF2F7FE");
    	
    	Color MATCH_CELL_OUTER_BORDER = Color.decode("0xF6D450");
    	Color MATCH_CELL_INNER_BORDER = Color.decode("0xFEF7DA");
    	Color MATCH_CELL_TOP = Color.decode("0xFEE486");
    	Color MATCH_CELL_BOTTOM = Color.decode("0xFEF1BD");
    }
    
    public static final class Fonts{
    	private static final Font FIXED_INPUT_TEXT_FONT;
    	private static final Font USER_INPUT_TEXT_FONT;
    	
    	static{
    		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    		try {
				font = Font.createFont(Font.TRUETYPE_FONT, 
						Fonts.class.getResourceAsStream("/fonts/VeraMoBd.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		FIXED_INPUT_TEXT_FONT = font.deriveFont(Font.ITALIC + Font.BOLD, 13.5F);
    		USER_INPUT_TEXT_FONT = font.deriveFont(Font.BOLD, 13.5F);
    	}
    	
    	public static Font getFixedInputTextFont(){
    		return FIXED_INPUT_TEXT_FONT;
    	}
    	
    	public static Font getUserInputTextFont(){
    		return USER_INPUT_TEXT_FONT;
    	}
    }
}
