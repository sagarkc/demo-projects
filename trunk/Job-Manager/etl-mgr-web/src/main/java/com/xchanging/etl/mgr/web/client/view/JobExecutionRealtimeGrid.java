/**
 * 
 */
package com.xchanging.etl.mgr.web.client.view;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.web.client.ds.JobExecutionHistoryDataSource;
import com.xchanging.etl.mgr.web.client.ds.JobMonitorHistoryDataSource;
import com.xchanging.etl.mgr.web.client.ds.LatestJobExecutionDataSource;

/**
 * @author Sabuj
 *
 */
public class JobExecutionRealtimeGrid extends ListGrid {

	
	private List<JobMonitorHistoryVo> jobExecutionData;
	private LatestJobExecutionDataSource jobMonitorHistoryDS = LatestJobExecutionDataSource.getInstance();
	
	/**
	 * 
	 */
	public JobExecutionRealtimeGrid() {
		addColumns();
		setDataSource(jobMonitorHistoryDS);
		setShowAllRecords(true); 
		setAutoFetchData(false);
		setCanExpandRecords(false);
		setShowRecordComponents(true);
		setShowRecordComponentsByCell(true);
		setShowAllColumns(true);
		//setExpansionMode(ExpansionMode.DETAILS);
		
		invalidateCache();
		fetchData();
	}
	
	
	
	/**
	 * 
	 */
	private void addColumns() {
		ListGridField nameField = new ListGridField(JobMonitorHistoryVo.Fields.JOB_NAME, "JOB Name");
		nameField.setWidth(180);
		ListGridField exitCodeField = new ListGridField(JobMonitorHistoryVo.Fields.STATUS, "Status");
		exitCodeField.setAlign(Alignment.CENTER);
		exitCodeField.setWidth(100);
		ListGridField startTimeField = new ListGridField(JobMonitorHistoryVo.Fields.LAST_STARTED_TIME, "Last Start Time");
		startTimeField.setType(ListGridFieldType.DATE);
		startTimeField.setWidth(150);
		startTimeField.setAlign(Alignment.CENTER);
		
		ListGridField endTimeField = new ListGridField(JobMonitorHistoryVo.Fields.LAST_ENDED_TIME,  "Last End Time");
		endTimeField.setWidth(150);
		endTimeField.setAlign(Alignment.CENTER);
		endTimeField.setType(ListGridFieldType.DATE);
		
		ListGridField executeJobField = new ListGridField("executeJob", "Action");
		executeJobField.setAlign(Alignment.RIGHT);
		
		setFields(nameField, exitCodeField, 
				startTimeField, endTimeField, executeJobField);
	}

	/**
	 * @return the jobExecutionData
	 */
	public List<JobMonitorHistoryVo> getJobExecutionData() {
		return jobExecutionData;
	}

	/**
	 * @param jobExecutionData the jobExecutionData to set
	 */
	public void setJobExecutionData(List<JobMonitorHistoryVo> jobExecutionData) {
		this.jobExecutionData = jobExecutionData;
		//populateData();
	}

	
	/* (non-Javadoc)
	 * @see com.smartgwt.client.widgets.grid.ListGrid#createRecordComponent(com.smartgwt.client.widgets.grid.ListGridRecord, java.lang.Integer)
	 */
	@Override
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
		String fieldName = this.getFieldName(colNum); 
		if (fieldName.equals("executeJob")) {  
            IButton button = new IButton();  
            button.setHeight(18);  
            button.setWidth(65);                      
            button.setTitle("Run");  
            button.addClickHandler(new ClickHandler() {  
                public void onClick(ClickEvent event) {  
                	Window.alert(record.getAttribute("jobName") + " execute clicked.");  
                }  
            });  
            return button;  
        } else {  
        	return super.createRecordComponent(record, colNum);
        }  
		
		
		
	}
}