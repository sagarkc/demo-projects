package com.mercuria.etl.mgr.web.client.core;


import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public class UIEventManager implements HasHandlers{

	private static UIEventManager manager;
	private HandlerManager handlerManager;
	
	private UIEventManager() {
		handlerManager = new HandlerManager(this);
	}
	
	/**
	 * Thread-safe instance creator
	 * @return
	 */
	public static UIEventManager getInstance() {
		if(null != manager)
			return manager;
		synchronized (UIEventManager.class) {
			if(null == manager)
				manager = new UIEventManager();
		}
		return manager;
	}



    @Override
	public void fireEvent(GwtEvent<?> event) {
		handlerManager.fireEvent(event);
	}

	public <T extends EventHandler> HandlerRegistration addListener(
            GwtEvent.Type<T> eventType,
            T listener) {
        
        return handlerManager.addHandler(eventType, listener);
    }
}