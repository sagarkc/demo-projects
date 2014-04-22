/**
 * 
 */
package com.gs.games.mysticsquare.app.model;

import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.undo.UndoableEditSupport;

import com.gs.games.mysticsquare.app.SizeFactorEnum;
import com.gs.games.mysticsquare.app.board.BoxSizeEnum;
import com.gs.games.mysticsquare.app.board.Drawable;
import com.gs.games.mysticsquare.core.IntegerProvider;
import com.gs.games.mysticsquare.core.model.Board;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UIBoard extends Board implements Drawable, Serializable {

	public static int X = 20;
	public static int Y = 20;
	private UndoableEditSupport undoMoveSupport;
	
	public static SizeFactorEnum sizeFactorEnum;
	
	public UIBoard() {
		super(new IntegerProvider(15));
		
		if(null == sizeFactorEnum)
			sizeFactorEnum = SizeFactorEnum.NORMAL;
		undoMoveSupport = new UndoableEditSupport();
	}
	
	public UndoableEditSupport getUndoMoveSupport() {
		return undoMoveSupport;
	}
	
	protected void initBoard() {
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				UiBox box = new UiBox(i, j);
				box.getPosition().x = X + (i * BoxSizeEnum.getValue(sizeFactorEnum).getWidth());
				box.getPosition().y = Y + (j * BoxSizeEnum.getValue(sizeFactorEnum).getHeight());
				getBoxes()[i][j] = box;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.gs.games.mysticsquare.app.board.Drawable#draw(java.awt.Graphics)
	 */
	public void draw(Graphics graphics) {
		
	}
	
	
}
