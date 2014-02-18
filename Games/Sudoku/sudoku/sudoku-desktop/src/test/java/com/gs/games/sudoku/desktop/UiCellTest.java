/**
 * 
 */
package com.gs.games.sudoku.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gs.games.sudoku.desktop.model.UiCell;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class UiCellTest {

	public static void main(String[] args) {

		JPanel panel = new JPanel(){
			/* (non-Javadoc)
			 * @see javax.swing.JComponent#paint(java.awt.Graphics)
			 */
			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				GraphicsUtility.setRendererHints(g2);
				super.paint(g2);
				UiCell cell1 = new UiCell(0, 0);
				cell1.setValue(2);
				cell1.setLocation(new Point(10, 10));
				cell1.setSize(new Dimension(66, 35));
				cell1.setSelected(true);
				cell1.setHovered(false);
				cell1.setValueProvided(true);
				
				cell1.draw(g2);
				
				UiCell cell2 = new UiCell(0, 1);
				cell2.setValue(9);
				cell2.setLocation(new Point(10, 55));
				cell2.setSize(new Dimension(45, 35));
				cell2.setSelected(false);
				cell2.setHovered(false);
				cell2.setValueProvided(false);
				
				cell2.draw(g2);
				
				UiCell cell3 = new UiCell(1, 1);
				cell3.setValue(5);
				cell3.setLocation(new Point(75, 55));
				cell3.setSize(new Dimension(45, 35));
				cell3.setSelected(false);
				cell3.setHovered(true);
				cell3.setValueProvided(false);
				
				cell3.draw(g2);
			}
			
		};
		panel.setSize(200, 200);
		panel.setBackground(SudokuUiConstants.BoardColors.BOARD_BG);

		JFrame frame = new JFrame("test" + " Cell");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
