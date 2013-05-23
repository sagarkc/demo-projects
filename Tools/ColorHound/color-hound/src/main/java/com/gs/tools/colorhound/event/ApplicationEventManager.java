/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.colorhound.event.ApplicationEventManager
 * Date     : May 23, 2013
 */

package com.gs.tools.colorhound.event;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ApplicationEventManager {

    private static ApplicationEventManager instance;
    private Map<Class, BaseEventListener> listeners 
            = new HashMap<Class,BaseEventListener>(0);
    
    private ApplicationEventManager() {
    }

    public static ApplicationEventManager getInstance() {
        if(null != instance)
            return instance;
        synchronized(ApplicationEventManager.class){
            if(null == instance){
                instance = new ApplicationEventManager();
            }
        }
        return instance;
    }

    public synchronized void registerListener(final Class eventType, 
            final BaseEventListener listener){
        listeners.put(eventType, listener);
    }
    
    public synchronized void unregisterListener(final Class eventType){
        listeners.remove(eventType);
    }
    
    public synchronized void fireEvent(ApplicationEvent event) {
		if(null == event)
			return;
		BaseEventListener listener = listeners.get(event.getClass());
        if(null != listener){
            if(event.getClass() == ColorGrabEvent.class){
                fireColorGrabEvent((ColorGrabEvent)event, 
                        (ColorGrabListener)listener);
            }
        }
	}
    
    public synchronized void fireColorGrabEvent(ColorGrabEvent event, ColorGrabListener listener){
        listener.colorGrabbed(event);
    }
 }
