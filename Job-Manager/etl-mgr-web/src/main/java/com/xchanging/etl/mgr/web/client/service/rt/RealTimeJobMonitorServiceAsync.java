/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.rt.RealTimeJobMonitorServiceAsync
 * Date:	Aug 22, 2013  3:24:58 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service.rt;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface RealTimeJobMonitorServiceAsync {

	void loadRealtimeJobMonitorData(RTJobFilterCriteria filterCriteria, 
			AsyncCallback<List<JobExecutionHistoryVo>> callBack);

	void loadRTLastHourJobMonitorData(
			AsyncCallback<List<JobExecutionHistoryVo>> callback);

	void loadRTLastDayJobMonitorData(
			AsyncCallback<List<JobExecutionHistoryVo>> callback);

	void loadRTAllJobsJobMonitorData(
			AsyncCallback<List<JobExecutionHistoryVo>> callback);
	
}
