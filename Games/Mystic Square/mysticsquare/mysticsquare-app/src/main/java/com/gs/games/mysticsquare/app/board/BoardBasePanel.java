/**
 * 
 */
package com.gs.games.mysticsquare.app.board;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.sf.bagh.bandhi.core.GameEngine;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class BoardBasePanel extends JPanel implements MouseMotionListener, MouseListener{

	/**
	 * serialVersionUID = -3821322838306025737L;
	 */
	private static final long serialVersionUID = -3821322838306025737L;
	
	private static final int LEFT_MARGIN = 20;
	private static final int RIGHT_MARGIN = 20;
	
	private static final int BOARD_WIDTH = 820;
	private static final int BOARD_HEIGHT = 690;
	
	//private static final int GRID_HEIGHT = 650;
	//private static final int GRID_WIDTH = 650;
	
	private static final int INFO_BOX_WIDTH = 100;
	private static final int INFO_BOX_HEIGHT = 650;
	private static final int INFO_BOX_X = 690;
	private static final int INFO_BOX_Y = 20;
	private static final int TEXT_HEIGHT = 20;
	
	private final Border boardBorder = BorderFactory.createCompoundBorder(BorderFactory
			.createLineBorder(new Color(0, 102, 102), 2), BorderFactory
			.createCompoundBorder(BorderFactory.createMatteBorder(10, 10,
					10, 10, new ImageIcon(getClass().getResource("/images/wood-plank-small.jpg"))), BorderFactory
					.createLineBorder(new Color(0, 153, 51))));
	
	
}
