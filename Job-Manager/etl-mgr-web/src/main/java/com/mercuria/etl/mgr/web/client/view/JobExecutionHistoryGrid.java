/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.client.view.JobExecutionHistoryGrid
 * Date:	Aug 5, 2013  8:13:49 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.client.view;

import java.sql.Date;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.mercuria.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.mercuria.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.mercuria.etl.mgr.web.client.ds.JobExecutionHistoryDataSource;
import com.mercuria.etl.mgr.web.client.ds.JobMonitorHistoryDataSource;
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
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobExecutionHistoryGrid extends ListGrid {

	
	private List<JobMonitorHistoryVo> jobExecutionData;
	private JobMonitorHistoryDataSource jobMonitorHistoryDS = JobMonitorHistoryDataSource.getInstance();
	
	/**
	 * 
	 */
	public JobExecutionHistoryGrid() {
		addColumns();
		setDataSource(jobMonitorHistoryDS);
		setShowAllRecords(true); 
		setAutoFetchData(false);
		setDrawAheadRatio(4);
		setCanExpandRecords(true);
		setShowRecordComponents(true);
		setShowRecordComponentsByCell(true);
		setShowAllColumns(true);
		setExpansionMode(ExpansionMode.DETAILS);
		
		invalidateCache();
		fetchData();
	}
	
	public DataSource getRelatedDataSource(ListGridRecord record) {  
        return JobExecutionHistoryDataSource.getInstance();  
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
		startTimeField.setType(ListGridFieldType.DATETIME);
		startTimeField.setWidth(150);
		startTimeField.setAlign(Alignment.CENTER);
		
		ListGridField endTimeField = new ListGridField(JobMonitorHistoryVo.Fields.LAST_ENDED_TIME,  "Last End Time");
		endTimeField.setWidth(150);
		endTimeField.setAlign(Alignment.CENTER);
		endTimeField.setType(ListGridFieldType.DATETIME);
		
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
	 * @see com.smartgwt.client.widgets.grid.ListGrid#getExpansionComponent(com.smartgwt.client.widgets.grid.ListGridRecord)
	 */
	@Override
	protected Canvas getExpansionComponent(ListGridRecord record) {
		ListGrid executionDetailsGrid = new ListGrid();
		executionDetailsGrid.setWidth100();
		executionDetailsGrid.setHeight(150);
		
		ListGridField idField = new ListGridField(JobExecutionHistoryVo.Fields.JOB_EXECUTION_ID, "JOB Execution ID");
		idField.setCanEdit(false);
		idField.setWidth(50);
		idField.setType(ListGridFieldType.INTEGER);
		
		ListGridField nameField = new ListGridField(JobExecutionHistoryVo.Fields.JOB_NAME, "JOB Name");
		nameField.setWidth(180);
		nameField.setHidden(true);
		
		ListGridField exitCodeField = new ListGridField(JobExecutionHistoryVo.Fields.EXIT_CODE, "Exit Code");
		exitCodeField.setWidth(100);
		ListGridField startTimeField = new ListGridField(JobExecutionHistoryVo.Fields.START_TIME, "Job Start Time");
		startTimeField.setWidth(150);
		startTimeField.setAlign(Alignment.CENTER);
		startTimeField.setType(ListGridFieldType.DATETIME);
		
		ListGridField endTimeField = new ListGridField(JobExecutionHistoryVo.Fields.END_TIME,  "Job End Time");
		endTimeField.setWidth(150);
		endTimeField.setAlign(Alignment.CENTER);
		endTimeField.setType(ListGridFieldType.DATETIME);
		
		ListGridField exitMessageField = new ListGridField(JobExecutionHistoryVo.Fields.EXIT_MESSAGE, "Exit Message");
		executionDetailsGrid.setFields( idField, nameField, exitCodeField, startTimeField, endTimeField, exitMessageField);
		executionDetailsGrid.setDataSource(getRelatedDataSource(record));
		executionDetailsGrid.fetchData(
				new Criterion(JobExecutionHistoryVo.Fields.JOB_NAME, OperatorId.EQUALS, 
						record.getAttributeAsString(JobMonitorHistoryVo.Fields.JOB_NAME)));
		Canvas canvas = new Canvas();
		canvas.setWidth100();
		canvas.setHeight(180);
		canvas.addChild(executionDetailsGrid);
		
		return canvas;//super.getExpansionComponent(record);
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