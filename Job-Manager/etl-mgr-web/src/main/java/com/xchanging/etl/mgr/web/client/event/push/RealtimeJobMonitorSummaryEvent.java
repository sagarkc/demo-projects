/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorSummaryEvent
 * Date:	Aug 22, 2013  5:40:28 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event.push;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.xchanging.etl.mgr.model.vo.JobExecutionSummaryVo;

import de.novanic.eventservice.client.event.Event;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class RealtimeJobMonitorSummaryEvent implements Event, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 16546579684654684L;
	
	private List<JobExecutionSummaryVo> currentExecutionSummary = new ArrayList<JobExecutionSummaryVo>();

	/**
	 * 
	 */
	@Deprecated
	public RealtimeJobMonitorSummaryEvent() {
		
	}
	
	/**
	 * @param currentExecutionSummary
	 */
	public RealtimeJobMonitorSummaryEvent(List<JobExecutionSummaryVo> currentExecutionSummary) {
		this.currentExecutionSummary = currentExecutionSummary;
	}

	/**
	 * @return the currentExecutionSummary
	 */
	public List<JobExecutionSummaryVo> getCurrentExecutionSummary() {
		return currentExecutionSummary;
	}

	/**
	 * @param currentExecutionSummary the currentExecutionSummary to set
	 */
	public void setCurrentExecutionSummary(
			List<JobExecutionSummaryVo> currentExecutionSummary) {
		this.currentExecutionSummary = currentExecutionSummary;
	}

	
	
}
