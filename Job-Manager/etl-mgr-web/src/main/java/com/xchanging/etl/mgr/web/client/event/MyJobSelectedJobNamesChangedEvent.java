/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.MyJobSelectedJobNamesChangedEvent
 * Date:	Aug 8, 2013  8:02:29 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class MyJobSelectedJobNamesChangedEvent extends GwtEvent<MyJobSelectedJobNamesChangedEventListener>{

	public static final Type<MyJobSelectedJobNamesChangedEventListener> TYPE
		= new Type<MyJobSelectedJobNamesChangedEventListener>();
	
	private final String[] selectedJobNames;
	
	
	/**
	 * @param selectedJobNames
	 */
	public MyJobSelectedJobNamesChangedEvent(String[] selectedJobNames) {
		this.selectedJobNames = selectedJobNames;
	}

	/**
	 * @return the selectedJobNames
	 */
	public String[] getSelectedJobNames() {
		return selectedJobNames;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MyJobSelectedJobNamesChangedEventListener> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(MyJobSelectedJobNamesChangedEventListener handler) {
		handler.selectedJobNamesChanged(this);
	}

	
}
