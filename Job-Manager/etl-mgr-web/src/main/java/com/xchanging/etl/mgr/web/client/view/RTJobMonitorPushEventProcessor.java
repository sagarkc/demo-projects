/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.view.RTJobMonitorPushEventProcessor
 * Date:	Aug 23, 2013  7:18:41 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.view;

import java.util.List;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobExecutionSummaryVo;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorEventListener;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class RTJobMonitorPushEventProcessor extends
		RealtimeJobMonitorEventListener {

	private final ListGrid listGrid;
	
	/**
	 * @param listGrid
	 */
	public RTJobMonitorPushEventProcessor(ListGrid listGrid) {
		this.listGrid = listGrid;
	}

	
	@Override
	public void rtAllJobsDataPushed(List<JobExecutionHistoryVo> jobExecutionData) {
		updateGridData(jobExecutionData);
	}

	
	@Override
	public void rtLastHourDataPushed(
			List<JobExecutionHistoryVo> jobExecutionData) {
		updateGridData(jobExecutionData);
	}

	
	@Override
	public void rtLastDayDataPushed(List<JobExecutionHistoryVo> jobExecutionData) {
		updateGridData(jobExecutionData);
	}

	
	@Override
	public void rtSelectedJobsDataPushed(
			List<JobExecutionHistoryVo> jobExecutionData) {
		updateGridData(jobExecutionData);
	}

	
	@Override
	public void rtFilteredJobsDataPushed(
			List<JobExecutionHistoryVo> jobExecutionData) {
		updateGridData(jobExecutionData);
	}

	
	@Override
	public void onJobMonitorSummaryPushed(
			List<JobExecutionSummaryVo> currentExecutionSummary) {
		// TODO Auto-generated method stub

	}

	private void updateGridData(final List<JobExecutionHistoryVo> jobExecutionData){
		if(null == jobExecutionData){
			return;
		}
		ListGridRecord records[] = new ListGridRecord[jobExecutionData.size()];
		for (int i = 0; i < jobExecutionData.size(); i++) {
			JobExecutionHistoryVo monitorVo = jobExecutionData.get(i);
			ListGridRecord record = new ListGridRecord();
			record.setAttribute(JobExecutionHistoryVo.Fields.JOB_NAME, monitorVo.getJobName());
			record.setAttribute(JobExecutionHistoryVo.Fields.JOB_EXECUTION_ID, monitorVo.getJobExecutionId());
			record.setAttribute(JobExecutionHistoryVo.Fields.EXIT_CODE, monitorVo.getExitCode());
			record.setAttribute(JobExecutionHistoryVo.Fields.STATUS_CODE, monitorVo.getStatusCode());
			record.setAttribute(JobExecutionHistoryVo.Fields.START_TIME, monitorVo.getStartTime());
			record.setAttribute(JobExecutionHistoryVo.Fields.END_TIME, monitorVo.getEndTime());
			record.setAttribute(JobExecutionHistoryVo.Fields.EXIT_MESSAGE, monitorVo.getExitMessage());
			records[i] = record;
		}
		listGrid.setData(records);
	}
}
