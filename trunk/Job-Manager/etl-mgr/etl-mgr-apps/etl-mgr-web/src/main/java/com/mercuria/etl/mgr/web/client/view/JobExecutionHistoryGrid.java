/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.client.view.JobExecutionHistoryGrid
 * Date:	Aug 1, 2013  5:23:12 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.client.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.mercuria.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
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

	/**
	 * 
	 */
	public JobExecutionHistoryGrid() {
		this(new ArrayList<JobMonitorHistoryVo>());
	}
	
	/**
	 * @param jobExecutionData
	 */
	public JobExecutionHistoryGrid(List<JobMonitorHistoryVo> jobExecutionData) {
		this.jobExecutionData = jobExecutionData;
		setShowAllRecords(true); 
		setAutoFetchData(false);
		setDrawAheadRatio(4);
		setCanExpandRecords(true);
		
		addColumns();
		populateData();
	}

	/**
	 * 
	 */
	private void addColumns() {
		ListGridField nameField = new ListGridField("jobName", "JOB Name");
		nameField.setWidth(180);
		ListGridField exitCodeField = new ListGridField("status", "Status");
		exitCodeField.setWidth(100);
		ListGridField startTimeField = new ListGridField("lastStartedTime", "Last Start Time");
		startTimeField.setWidth(150);
		startTimeField.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				String data = record.getAttribute("startTime");
				if(data != null && !"".equals(data)){
					Long milis = Long.parseLong(data);
					Date date = new Date(milis);
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
					return fmt.format(date);
				}
				return data;
			}
			
		});
		startTimeField.setAlign(Alignment.LEFT);
		startTimeField.setType(ListGridFieldType.DATETIME);
		
		ListGridField endTimeField = new ListGridField("lastEndedTime",  "Last End Time");
		endTimeField.setWidth(150);
		endTimeField.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				String data = record.getAttribute("endTime");
				if(data != null && !"".equals(data)){
					Long milis = Long.parseLong(data);
					Date date = new Date(milis);
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
					return fmt.format(date);
				}
				return data;
			}
			
		});
		endTimeField.setAlign(Alignment.LEFT);
		endTimeField.setType(ListGridFieldType.DATETIME);
		
		ListGridField executeJobField = new ListGridField("executeJob", "Action");
		exitCodeField.setWidth(80);
		
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
		populateData();
	}

	/**
	 * 
	 */
	private void populateData() {
		if(null == jobExecutionData || jobExecutionData.size() <= 0)
			return;
		for (JobMonitorHistoryVo monitorVo : jobExecutionData) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("jobName", monitorVo.getJobName());
			record.setAttribute("status", monitorVo.getJobName());
			record.setAttribute("lastStartedTime", monitorVo.getJobName());
			record.setAttribute("lastEndedTime", monitorVo.getJobName());
			
			record.setAttribute("executionDetails", monitorVo.getExecutionDetails());
			
			addData(record);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.smartgwt.client.widgets.grid.ListGrid#getExpansionComponent(com.smartgwt.client.widgets.grid.ListGridRecord)
	 */
	@Override
	protected Canvas getExpansionComponent(ListGridRecord record) {
		ListGrid executionDetailsGrid = new ListGrid();
		executionDetailsGrid.setWidth100();
		executionDetailsGrid.setHeight(150);
		
		ListGridField idField = new ListGridField("jobExecutionId", "JOB Execution ID");
		idField.setCanEdit(false);
		idField.setHidden(true);
		idField.setType(ListGridFieldType.INTEGER);
		
		ListGridField nameField = new ListGridField("jobName", "JOB Name");
		nameField.setWidth(180);
		ListGridField exitCodeField = new ListGridField("exitCode", "Exit Code");
		exitCodeField.setWidth(100);
		ListGridField startTimeField = new ListGridField("startTime", "Job Start Time");
		startTimeField.setWidth(150);
		startTimeField.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				String data = record.getAttribute("startTime");
				if(data != null && !"".equals(data)){
					Long milis = Long.parseLong(data);
					Date date = new Date(milis);
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
					return fmt.format(date);
				}
				return data;
			}
			
		});
		startTimeField.setAlign(Alignment.LEFT);
		startTimeField.setType(ListGridFieldType.DATETIME);
		
		ListGridField endTimeField = new ListGridField("endTime",  "Job End Time");
		endTimeField.setWidth(150);
		endTimeField.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				String data = record.getAttribute("endTime");
				if(data != null && !"".equals(data)){
					Long milis = Long.parseLong(data);
					Date date = new Date(milis);
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
					return fmt.format(date);
				}
				return data;
			}
			
		});
		endTimeField.setAlign(Alignment.LEFT);
		endTimeField.setType(ListGridFieldType.DATETIME);
		
		ListGridField exitMessageField = new ListGridField("exitMessage", "Exit Message");
		executionDetailsGrid.setFields( idField, nameField, exitCodeField, startTimeField, endTimeField, exitMessageField);
		
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
                    SC.say(record.getAttribute("jobName") + " execute clicked.");  
                }  
            });  
            return button;  
        } else {  
        	return super.createRecordComponent(record, colNum);
        }  
		
		
		
	}
}
