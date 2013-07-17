package com.mercuria.etl.mgr.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowJobMonitorEvent extends GwtEvent<ShowJobMonitorEventListener>{

	public static final Type<ShowJobMonitorEventListener> TYPE
		= new Type<ShowJobMonitorEventListener>();

	
	@Override
	public Type<ShowJobMonitorEventListener> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowJobMonitorEventListener handler) {
		handler.showJobMonitor(this);
	}
	
	
	
}
