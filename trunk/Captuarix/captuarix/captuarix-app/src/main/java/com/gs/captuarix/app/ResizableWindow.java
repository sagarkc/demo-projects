/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.captuarix.app;



import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ResizableWindow extends JWindow {

	public ResizableWindow() {

		/*
		 * Register the resize listener.
		 */
		ResizeListener resizeListener = new ResizeListener();
		addMouseListener(resizeListener);
		addMouseMotionListener(resizeListener);

		setMinimumSize(new Dimension(50, 50));


		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		int w = 3;//UIManager.getInt("InternalFrame.borderWidth");
		//w = Math.max(w, 3) - 2;
		border = BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(w, w, w, w));

		rootPane.setBorder(border);
		rootPane.setDoubleBuffered(true);
		
		
	}

	protected int getDefaultCursor(MouseEvent p_Evt) {
		return Cursor.MOVE_CURSOR;
	}

	private class ResizeListener extends MouseAdapter
			implements MouseMotionListener {

		private static final short RESIZE_E = 1;
		private static final short RESIZE_W = 2;
		private static final short RESIZE_N = 4;
		private static final short RESIZE_S = 8;
		private static final short MOVE = 0x10;
		int resizing = 0;
		private Rectangle tempBounds = new Rectangle();
		private Point mousePointer;

		public void mousePressed(MouseEvent evt) {
			resizing = getResizeDirection(evt);
		}

		public void mouseReleased(MouseEvent evt) {
			resizing = 0;
		}

		private short getResizeDirection(MouseEvent evt) {
			short direction = 0;
			int width = getWidth();
			int height = getHeight();
			Insets insets = getRootPane().getInsets();
			int mouseX = evt.getX();
			int mouseY = evt.getY();
			if (mouseX < insets.left) {
				direction |= RESIZE_W;
			} else if (mouseX > width - insets.right) {
				direction |= RESIZE_E;
			}
			if (mouseY < insets.top) {
				direction |= RESIZE_N;
			} else if (mouseY > height - insets.bottom) {
				direction |= RESIZE_S;
			}
			return direction;
		}

		public void mouseMoved(MouseEvent evt) {
			int cursorType = getCursorType(evt);
			setCursor(Cursor.getPredefinedCursor(cursorType));
			mousePointer = evt.getPoint();
		}

		private int getCursorType(MouseEvent evt) {
			short direction = getResizeDirection(evt);
			switch (direction) {
				case RESIZE_S:
					return Cursor.S_RESIZE_CURSOR;
				case RESIZE_E:
					return Cursor.E_RESIZE_CURSOR;
				case RESIZE_N:
					return Cursor.N_RESIZE_CURSOR;
				case RESIZE_W:
					return Cursor.W_RESIZE_CURSOR;
				case RESIZE_S | RESIZE_E:
					return Cursor.SE_RESIZE_CURSOR;
				case RESIZE_N | RESIZE_W:
					return Cursor.NW_RESIZE_CURSOR;
				case RESIZE_N | RESIZE_E:
					return Cursor.NE_RESIZE_CURSOR;
				case RESIZE_S | RESIZE_W:
					return Cursor.SW_RESIZE_CURSOR;
				default:
					int result = getDefaultCursor(evt);
					if (result == Cursor.MOVE_CURSOR) {
						resizing = MOVE;
					}
					return result;
			}
		}

		public void mouseDragged(final MouseEvent evt) {
			Point mousePoint = evt.getPoint();
			if (resizing == 0 && MOVE != 0) {
				int x = getX() + mousePoint.x - mousePointer.x;
				int y = getY() + mousePoint.y - mousePointer.y;
				setLocation(x, y);
				repaint();
				return;
			}
			Rectangle bounds = getBounds(tempBounds);
			
			SwingUtilities.convertPointToScreen(mousePoint, ResizableWindow.this);
			if ((resizing & RESIZE_E) != 0) {
				bounds.width = evt.getX();
			} else if ((resizing & RESIZE_W) != 0) {
				bounds.width += bounds.x - mousePoint.x;
				bounds.x = mousePoint.x;
			}
			if ((resizing & RESIZE_S) != 0) {
				bounds.height = evt.getY();
			} else if ((resizing & RESIZE_N) != 0) {
				bounds.height += bounds.y - mousePoint.y;
				bounds.y = mousePoint.y;
			}

			if (bounds.getSize().height >= getMinimumSize().height
					&& bounds.getSize().width >= getMinimumSize().width) {
				setBounds(bounds);
				validate();
				repaint();
			}

			Graphics graphics = getGraphics();
			paint(graphics);
		}
	} 

}
