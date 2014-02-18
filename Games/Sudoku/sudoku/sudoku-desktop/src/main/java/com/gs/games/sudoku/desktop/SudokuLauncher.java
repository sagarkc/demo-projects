/**
 *
 */
package com.gs.games.sudoku.desktop;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.gs.games.sudoku.desktop.ui.SudokuGameFrame;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class SudokuLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
        	UIManager.setLookAndFeel(SudokuUiConstants.LAF_SYSTEM);
        } catch (Exception ex) {
        	try {
				UIManager.setLookAndFeel(SudokuUiConstants.LAF_METAL);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SudokuGameFrame().setVisible(true);
            }
        });
    }
}
