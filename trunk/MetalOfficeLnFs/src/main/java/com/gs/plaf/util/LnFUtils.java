/** ***************************************************************************
 *		MetalOfficeLnF :: Office Look and Feel from Metal	
 *	
 *	File	: LnFUtils.java
 *	Type	: com.gs.plaf.util.LnFUtils.java
 *	Date	: Jan 3, 2010	9:31:51 AM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.plaf.util;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 *
 */
public class LnFUtils {

	public static ImageIcon loadIcon(String path, String fileName,
			Object invoker) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(
				path + fileName);

		if (url == null) {
			url = LnFUtils.class.getResource(path + fileName);

			if (url == null) {
				System.err.println("TinyLaF: Icon directory could not be resolved.");
				return null;
			}
		}

		return new ImageIcon(url);
	}

}
