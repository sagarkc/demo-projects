/**
 * File :: com.gs.demo.gwt.spring.shared.evt.MessageUpdateListener
 * Date :: 07-May-2013
 */
package com.gs.gwtproject.three.shared.listener;

import com.google.gwt.event.shared.EventHandler;
import com.gs.gwtproject.three.shared.event.MessageUpdatedEvent;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface MessageUpdateListener extends EventHandler{

	public void messageUpdated(MessageUpdatedEvent messageUpdatedEvent);
	
}
