/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.captuarix.core;

import com.gs.captuarix.core.io.JpegImagesToMovie;
import com.gs.captuarix.core.io.ScreenDataWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.MediaLocator;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ScreenRecorder implements Runnable{

	public static boolean record = false;
	public static int captureInterval = 33; // mili secSTAER_RECORD
	public static final String IMAGE_ADDED = "IMAGE_ADDED";
	public static final String START_RECORD = "START_RECORD";
	
	private final Point location;
	private final Dimension size;
	private Robot robot;
	
	private ScreenDataWriter screenDataWriter;

	private PropertyChangeSupport propertyChangeSupport;
	
	public ScreenRecorder(Point location, Dimension size) throws AWTException {
		this.location = location;
		if(null == location)
			throw new IllegalArgumentException("Location must be defiled.");
		this.size = size;
		if(null == size)
			throw new IllegalArgumentException("Dimension must be defiled.");
		this.robot = new Robot();
		
		propertyChangeSupport  = new PropertyChangeSupport(this);
		
		this.screenDataWriter = new ScreenDataWriter(size);
		addPropertyChangeListener(screenDataWriter);
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}
	
	
    public final void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }
	
	public final void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(listener);
    }
	
	public final void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        getPropertyChangeSupport().firePropertyChange(propertyName, oldValue, newValue);
    }
	
	public void run() {
		int count = 0;
		try {
			firePropertyChange(START_RECORD, null, size);
			while (count == 0 || record) {
				BufferedImage img = robot.createScreenCapture(
						new Rectangle(location, size)
					);	
				firePropertyChange(IMAGE_ADDED, null, img);
				if (count == 0) {
					record = true;
					count = 1;
				}
				Thread.sleep(captureInterval);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public static void main(String[] args) {
		try {
//			ScreenRecorder recorder = new ScreenRecorder(
//					new Point(100, 100), new Dimension(300, 250));
//			Thread t = new Thread(recorder);
//			t.start();
			
			
			System.out
				.println("#### Easy Capture making video, please wait!!! ####");
			JpegImagesToMovie imageToMovie = new JpegImagesToMovie();
			Vector<String> imgLst = new Vector<String>();
			File f = new File("d:\\temp-ing\\");
			File[] fileLst = f.listFiles();
			for (int i = 0; i < fileLst.length; i++) {
				imgLst.add(fileLst[i].getAbsolutePath());
			}
			// Generate the output media locators.
			MediaLocator oml = ScreenDataWriter.createMediaLocator("d:\\xy001.avi");
			
			imageToMovie.doIt(280,280, (1000 / captureInterval),
					imgLst, oml);
		} catch (Exception ex) {
			Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		
	}
	
}
