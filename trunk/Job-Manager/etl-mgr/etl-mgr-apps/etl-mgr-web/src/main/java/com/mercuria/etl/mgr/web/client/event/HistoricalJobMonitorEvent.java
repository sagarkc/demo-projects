/**
 * File :: com.mercuria.etl.mgr.web.client.event.HistoricalJobMonitorEvent
 * Date :: 24-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.event;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class HistoricalJobMonitorEvent extends GwtEvent<HistoricalJobMonitorEventListener>{

	public static final Type<HistoricalJobMonitorEventListener> TYPE
		= new Type<HistoricalJobMonitorEventListener>();

	private final List<JavaScriptObject> jobMonitorData;

	/**
	 * @param jobMonitorData
	 */
	public HistoricalJobMonitorEvent(List<JavaScriptObject> jobMonitorData) {
		this.jobMonitorData = jobMonitorData;
	}

	@Override
	public Type<HistoricalJobMonitorEventListener> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(HistoricalJobMonitorEventListener handler) {
		handler.showHistoricalJobMonitorData(this);
	}

	public List<JavaScriptObject> getJobMonitorData() {
		return jobMonitorData;
	}
	
}
