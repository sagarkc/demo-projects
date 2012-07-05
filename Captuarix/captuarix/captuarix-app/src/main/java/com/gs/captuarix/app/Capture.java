/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.captuarix.app;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Capture extends JFrame {

	public Capture() {
		super();
	}

	public Capture(String str) {
		super("Capture Screen");
	}

	public void createTransparentPanel() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		final Dimension d = kit.getScreenSize();
		Rectangle rect = new Rectangle(d);
		try {
			Robot robot = new Robot();
			final BufferedImage image = robot.createScreenCapture(rect);
			image.flush();
			JPanel panel = new JPanel() {

				public void paintComponent(Graphics g) {
					g.drawImage(image, 0, 0, d.width, d.height, this);
				}
			};
			panel.setOpaque(false);
			panel.prepareImage(image, panel);
			panel.repaint();
			getContentPane().add(panel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Capture capture = new Capture();
		capture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	 capture.createComponents();
		capture.createTransparentPanel();
		Toolkit kit = Toolkit.getDefaultToolkit();
		final Dimension d = kit.getScreenSize();
		capture.setSize(d);
//	 capture.setUndecorated(true);	 // No window controls
		capture.setVisible(true);
	}
}