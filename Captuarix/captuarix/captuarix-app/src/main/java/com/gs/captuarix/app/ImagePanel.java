/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.captuarix.app;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ImagePanel  extends javax.swing.JPanel {

	private Image image;

	/**
	 * Creates new form ImagePanel from Image file name
	 */
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	/**
	 * Creates new form ImagePanel from Image
	 */
	public ImagePanel(Image image) {
		if (null == image) {
			throw new IllegalArgumentException("Image must be provided");
		}
		this.image = image;
		initComponents();
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        //setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(7, 7, 7, 7, new java.awt.Color(204, 204, 255)), null));
        //setLayout(new java.awt.BorderLayout());
    }
}
