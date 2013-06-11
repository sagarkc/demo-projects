/**
 * File :: com.gs.demo.gwt.spring.shared.evt.MessageUpdatedEvent
 * Date :: 07-May-2013
 */
package com.gs.gwtproject.three.shared.event;

import com.google.gwt.event.shared.GwtEvent;
import com.gs.gwtproject.three.shared.listener.MessageUpdateListener;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MessageUpdatedEvent extends GwtEvent<MessageUpdateListener>{

	public static Type<MessageUpdateListener> TYPE = new Type<MessageUpdateListener>();
	private String message;
	
	/**
	 * @param message
	 */
	public MessageUpdatedEvent(String message) {
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public Type<MessageUpdateListener> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MessageUpdateListener handler) {
		handler.messageUpdated(this);
	}

	
	
}
