/**
 * File :: com.mercuria.etl.mgr.web.client.event.HistoricalJobMonitorEvent
 * Date :: 24-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.mercuria.etl.mgr.model.vo.JobMonitorHistoryVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class HistoricalJobMonitorEvent extends GwtEvent<HistoricalJobMonitorEventListener>{

	public static final Type<HistoricalJobMonitorEventListener> TYPE
		= new Type<HistoricalJobMonitorEventListener>();

	private final List<JobMonitorHistoryVo> jobMonitorData;

	/**
	 * @param jobMonitorData
	 */
	public HistoricalJobMonitorEvent(List<JobMonitorHistoryVo> jobMonitorData) {
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

	public List<JobMonitorHistoryVo> getJobMonitorData() {
		return jobMonitorData;
	}
	
}
