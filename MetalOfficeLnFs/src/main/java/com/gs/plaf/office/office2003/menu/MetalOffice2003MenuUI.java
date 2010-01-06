/** ***************************************************************************
 *		MetalOfficeLnF :: Office Look and Feel from Metal	
 *	
 *	File	: MetalOffice2003MenuUI.java
 *	Type	: com.gs.plaf.office.office2003.menu.MetalOffice2003MenuUI.java
 *	Date	: Jan 3, 2010	9:15:40 AM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.plaf.office.office2003.menu;

import java.awt.KeyEventPostProcessor;
import java.awt.Window;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.ComboPopup;

/**
 *
 */
public class MetalOffice2003MenuUI extends MetalOffice2003MenuItemUI{

	public static final AltProcessor altProcessor = new AltProcessor();

	
	
	
	
	static class AltProcessor implements KeyEventPostProcessor {
		static boolean altKeyPressed = false;
		static boolean menuCanceledOnPress = false;
		static JRootPane root = null;
		static Window winAncestor = null;

		void altPressed(KeyEvent ev) {
			MenuSelectionManager msm = MenuSelectionManager.defaultManager();
			MenuElement[] path = msm.getSelectedPath();

			if ((path.length > 0) && (!(path[0] instanceof ComboPopup))) {
				msm.clearSelectedPath();
				menuCanceledOnPress = true;
				ev.consume();
			} else if (path.length > 0) {
				menuCanceledOnPress = false;
				ev.consume();
			} else {
				menuCanceledOnPress = false;
				JMenuBar mbar = (root != null) ? root.getJMenuBar() : null;

				if ((mbar == null) && (winAncestor instanceof JFrame)) {
					mbar = ((JFrame) winAncestor).getJMenuBar();
				}

				JMenu menu = (mbar != null) ? mbar.getMenu(0) : null;

				if (menu != null)
					ev.consume();
			}
		}

		void altReleased(KeyEvent ev) {
			if (menuCanceledOnPress) {
				return;
			}

			MenuSelectionManager msm = MenuSelectionManager.defaultManager();

			if (msm.getSelectedPath().length != 0)
				return;
			JMenuBar mbar = (root != null) ? root.getJMenuBar() : null;

			if ((mbar == null) && (winAncestor instanceof JFrame)) {
				mbar = ((JFrame) winAncestor).getJMenuBar();
			}

			JMenu menu = (mbar != null) ? mbar.getMenu(0) : null;

			if (menu != null) {
				MenuElement[] path = new MenuElement[2];
				path[0] = mbar;
				path[1] = menu;
				msm.setSelectedPath(path);
			}
		}

		public boolean postProcessKeyEvent(KeyEvent ev) {
			if (ev.getKeyCode() == 18) {
				root = SwingUtilities.getRootPane(ev.getComponent());
				winAncestor = SwingUtilities.getWindowAncestor(root);

				if (ev.getID() == 401) {
					if (!(altKeyPressed)) {
						altPressed(ev);
					}

					altKeyPressed = true;
					return true;
				}
				if (ev.getID() == 402) {
					if (altKeyPressed) {
						altReleased(ev);
					}

					altKeyPressed = false;
				}
			} else {
				altKeyPressed = false;
			}

			return false;
		}
	}
}
