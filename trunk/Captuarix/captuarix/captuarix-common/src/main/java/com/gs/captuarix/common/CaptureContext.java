/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.captuarix.common;

import com.gs.captuarix.model.Screen;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public final class CaptureContext {

	private static CaptureContext context;
	private CaptureContext(){}

	public static CaptureContext getContext() {
		synchronized(CaptureContext.class){
			if(null == context)
				context = new CaptureContext();
		}
		return context;
	}
	
	
	public Screen currentScreen = Screen.defaultScreen;
	
	
}
