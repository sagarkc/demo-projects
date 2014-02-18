/**
 * 
 */
package com.gs.games.sudoku.desktop.model;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import com.gs.games.sudoku.core.model.Cell;
import com.gs.games.sudoku.desktop.GraphicsUtility;
import com.gs.games.sudoku.desktop.SudokuUiConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class UiCell extends Cell implements MouseListener{

	private String name;
	private Dimension size;
	private Point location;
	private Graphics2D graphics2d;

	private boolean selected;
	private boolean hovered;
	
	
	/**
	 * @param x
	 * @param y
	 */
	public UiCell(int x, int y) {
		super(x, y);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the size
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	
	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the hovered
	 */
	public boolean isHovered() {
		return hovered;
	}

	/**
	 * @param hovered the hovered to set
	 */
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}


	public void draw(Graphics2D g){
		if(null == g)
			throw new RuntimeException("Cannot get Graphics");
		graphics2d = g;
		GraphicsUtility.setRendererHints(g);
		g.setBackground(SudokuUiConstants.BoardColors.BOARD_BG);
		
		Rectangle2D fillRect = new Rectangle2D.Double(
				location.getX() , 
				location.getY() , 
				size.getWidth() , 
				size.getHeight());
		Rectangle2D outerBorderRect = new Rectangle2D.Double(
				location.getX() , 
				location.getY() , 
				size.getWidth() , 
				size.getHeight());
		Rectangle2D innerBorderRect = new Rectangle2D.Double(
				location.getX() + 1, 
				location.getY() + 1, 
				size.getWidth() - 2, 
				size.getHeight()- 2);
		
		if(isSelected() && !isHovered()){
			GradientPaint gp = new GradientPaint(
					0, 0,
					SudokuUiConstants.BoardColors.CELL_FOCUS_TOP, 
					0, size.height,
					SudokuUiConstants.BoardColors.CELL_FOCUS_BOTTOM, true);
			
			g.setPaint(gp);
			g.fill(fillRect);
			
			g.setColor(SudokuUiConstants.BoardColors.CELL_FOCUS_OUTER_BORDER);
			g.draw(outerBorderRect);
			
			g.setStroke(new BasicStroke(0.5F));
			g.setColor(SudokuUiConstants.BoardColors.CELL_FOCUS_INNER_BORDER);
			g.draw(innerBorderRect);
		} 
		if(isHovered() && !isSelected()){
			GradientPaint gp = new GradientPaint(
					0, 0,
					SudokuUiConstants.BoardColors.CELL_HOVER_TOP, 
					0, size.height,
					SudokuUiConstants.BoardColors.CELL_HOVER_BOTTOM, true);
			
			g.setPaint(gp);
			g.fill(fillRect);
			
			
			g.setColor(SudokuUiConstants.BoardColors.CELL_HOVER_OUTER_BORDER);
			g.draw(outerBorderRect);
			
			g.setColor(SudokuUiConstants.BoardColors.CELL_HOVER_INNER_BORDER);
			g.draw(innerBorderRect);
		} 
		if(!isHovered() && !isSelected()){
			
			g.setColor(SudokuUiConstants.BoardColors.BOARD_BG);
			g.fill(fillRect);
		}
		
		if(isValueProvided()){
			g.setFont(SudokuUiConstants.Fonts.getFixedInputTextFont());
			g.setColor(SudokuUiConstants.BoardColors.FIXED_INPUT);
		} else {
			g.setFont(SudokuUiConstants.Fonts.getUserInputTextFont());
			g.setColor(SudokuUiConstants.BoardColors.USER_INOUT);
		}
		int textWidth = GraphicsUtility.getTextWidth(g, "" + getValue());
		int textHeight = GraphicsUtility.getTextHeight(g, "" + getValue());
		
		int px = ((int)getSize().getWidth() / 2 - textWidth/2);
		int py = ((int)getSize().getHeight() /2 + textHeight);
		g.drawString(""+getValue(), location.x + px, location.y + py);
		
		
	}
	
	public void repaint(Graphics2D g){
		graphics2d = g;
		draw(g);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		setSelected(true);
		setHovered(false);
		repaint(graphics2d);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		setSelected(false);
		setHovered(true);
		repaint(graphics2d);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		setSelected(false);
		setHovered(false);
		repaint(graphics2d);
	}
	
	
}
