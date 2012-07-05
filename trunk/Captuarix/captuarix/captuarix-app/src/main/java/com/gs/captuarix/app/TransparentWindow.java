/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.captuarix.app;

import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author 50120C1509
 */
public class TransparentWindow extends ResizableWindow {

	

	public TransparentWindow() {

		setSize(new Dimension(200, 200));
		setLocation(100, 100);


		setAlwaysOnTop(true);

		if (AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.TRANSLUCENT)) {
			AWTUtilities.setWindowOpacity(this, 0.25F);
		}

		getContentPane().setLayout(new BorderLayout());

		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.GREEN);
		jPanel.setBorder(new LineBorder(Color.BLACK, 2));

		getContentPane().add(jPanel, BorderLayout.CENTER);

		
	}

	public static void main(String[] args) {
		TransparentWindow tw = new TransparentWindow();
		tw.setVisible(true);

		/*SwingUtilities.invokeLater(new Runnable() {
		
		@Override
		public void run() {
		createAndShowGUI();
		}
		});*/
	}


	
}
