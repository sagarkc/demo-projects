/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEvent
 * Date:	Aug 21, 2013  1:00:52 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event.push;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;

import de.novanic.eventservice.client.event.Event;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
public class RealtimeJobMonitorDataEvent implements Event, IsSerializable {

	public static final String RT_ALL_JOBS = "RT_ALL_JOBS";
	public static final String RT_LAST_HOUR_JOBS = "RT_LAST_HOUR_JOBS";
	public static final String RT_LAST_DAY_JOBS = "RT_LAST_DAY_JOBS";
	public static final String RT_SELECTED_JOBS = "RT_SELECTED_JOBS";
	public static final String RT_FILTERED_JOBS = "RT_FILTERED_JOBS";

	/**
	 * 
	 */
	private static final long serialVersionUID = 291903203456109437L;

	private List<JobExecutionHistoryVo> currentExecutionData 
		= new ArrayList<JobExecutionHistoryVo>();
	private String eventType;

	/**
	 * 
	 */
	@Deprecated
	public RealtimeJobMonitorDataEvent() {

	}

	/**
	 * @param currentExecutionData
	 */
	public RealtimeJobMonitorDataEvent(String type,
			List<JobExecutionHistoryVo> currentExecutionData) {
		this.eventType = type;
		this.currentExecutionData = currentExecutionData;
	}

	/**
	 * @return the currentExecutionData
	 */
	public List<JobExecutionHistoryVo> getCurrentExecutionData() {
		return currentExecutionData;
	}

	/**
	 * @param currentExecutionData
	 *            the currentExecutionData to set
	 */
	public void setCurrentExecutionData(
			List<JobExecutionHistoryVo> currentExecutionData) {
		this.currentExecutionData = currentExecutionData;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
