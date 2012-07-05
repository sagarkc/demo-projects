/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.captuarix.core.io;

import com.gs.captuarix.core.ScreenRecorder;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.media.ConfigureCompleteEvent;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.DataSink;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.PrefetchCompleteEvent;
import javax.media.Processor;
import javax.media.RealizeCompleteEvent;
import javax.media.ResourceUnavailableEvent;
import javax.media.datasink.DataSinkErrorEvent;
import javax.media.datasink.DataSinkEvent;
import javax.media.datasink.DataSinkListener;
import javax.media.datasink.EndOfStreamEvent;
import javax.media.protocol.DataSource;


/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ScreenDataWriter implements PropertyChangeListener {

	public static void writeImage(ImageChunk chunk){
		if(null == chunk){
			return;
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:\\test.img", true));
			oos.writeObject(chunk);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != oos){
				try {
					oos.close();
				} catch (IOException ex) {
					
				}
			}
		}
	}
	
	public static MediaLocator createMediaLocator(String url) {

		MediaLocator ml;

		if (url.indexOf(":") > 0 && (ml = new MediaLocator(url)) != null)
			return ml;

		if (url.startsWith(File.separator)) {
			if ((ml = new MediaLocator("file:" + url)) != null)
				return ml;
		} else {
			String file = "file:" + System.getProperty("user.dir")
					+ File.separator + url;
			if ((ml = new MediaLocator(file)) != null)
				return ml;
		}

		return null;
	}

	private Dimension size;
	
	private MediaLocator mediaLocator;

	public ScreenDataWriter(Dimension size) {
		MediaLocator oml;
		if ((oml = createMediaLocator("d:\\mmmm.avi")) == null) {
			return;
		}
		mediaLocator = oml;
	}
	
	

	public void propertyChange(PropertyChangeEvent evt) {
		if(ScreenRecorder.START_RECORD == evt.getPropertyName()){
			size = (Dimension) evt.getNewValue();
			System.out.println("Size sent: " + size);
		}
		if(ScreenRecorder.IMAGE_ADDED == evt.getPropertyName()){
			BufferedImage image = (BufferedImage) evt.getNewValue();
			System.out.println("Image added: ");
			
			try {
				writeImage(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	private synchronized void writeImage(BufferedImage image) {
		File file = new File("d:\\temp-ing\\" + System.currentTimeMillis() + ".jpeg");
		try {
			ImageIO.write(image, "jpeg", file);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
		
}
