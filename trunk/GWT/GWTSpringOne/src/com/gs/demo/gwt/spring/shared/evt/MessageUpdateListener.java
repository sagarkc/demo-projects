/**
 * File :: com.gs.demo.gwt.spring.shared.evt.MessageUpdateListener
 * Date :: 07-May-2013
 */
package com.gs.demo.gwt.spring.shared.evt;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface MessageUpdateListener extends EventHandler{

	public void messageUpdated(MessageUpdatedEvent messageUpdatedEvent);
	
}
