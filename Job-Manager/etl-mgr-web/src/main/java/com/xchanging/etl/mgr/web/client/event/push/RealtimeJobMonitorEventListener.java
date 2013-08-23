/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorEventListener
 * Date:	Aug 21, 2013  1:18:51 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event.push;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobExecutionSummaryVo;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.listener.RemoteEventListener;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public abstract class RealtimeJobMonitorEventListener implements RemoteEventListener, IsSerializable{

	@Override
	public void apply(Event anEvent) {
		if(null == anEvent)
			return;
		if(anEvent instanceof RealtimeJobMonitorDataEvent){
			RealtimeJobMonitorDataEvent event = (RealtimeJobMonitorDataEvent) anEvent;
			if(RealtimeJobMonitorDataEvent.RT_ALL_JOBS.equals(event.getEventType())){
				rtAllJobsDataPushed(event.getCurrentExecutionData());
			} else if(RealtimeJobMonitorDataEvent.RT_LAST_HOUR_JOBS.equals(event.getEventType())){
				rtLastHourDataPushed(event.getCurrentExecutionData());
			} else if(RealtimeJobMonitorDataEvent.RT_LAST_DAY_JOBS.equals(event.getEventType())){
				rtLastDayDataPushed(event.getCurrentExecutionData());
			} else if(RealtimeJobMonitorDataEvent.RT_SELECTED_JOBS.equals(event.getEventType())){
				rtSelectedJobsDataPushed(event.getCurrentExecutionData());
			} else if(RealtimeJobMonitorDataEvent.RT_FILTERED_JOBS.equals(event.getEventType())){
				rtFilteredJobsDataPushed(event.getCurrentExecutionData());
			} 
		}
		else if(anEvent instanceof RealtimeJobMonitorSummaryEvent){
			RealtimeJobMonitorSummaryEvent event = (RealtimeJobMonitorSummaryEvent) anEvent;
			onJobMonitorSummaryPushed(event.getCurrentExecutionSummary());
		}
	}
	

	public abstract void rtAllJobsDataPushed(List<JobExecutionHistoryVo> jobExecutionData) ;
	public abstract void rtLastHourDataPushed(List<JobExecutionHistoryVo> jobExecutionData) ;
	public abstract void rtLastDayDataPushed(List<JobExecutionHistoryVo> jobExecutionData) ;
	public abstract void rtSelectedJobsDataPushed(List<JobExecutionHistoryVo> jobExecutionData) ;
	public abstract void rtFilteredJobsDataPushed(List<JobExecutionHistoryVo> jobExecutionData) ;
	
	public abstract void onJobMonitorSummaryPushed(List<JobExecutionSummaryVo> currentExecutionSummary);
	
}
