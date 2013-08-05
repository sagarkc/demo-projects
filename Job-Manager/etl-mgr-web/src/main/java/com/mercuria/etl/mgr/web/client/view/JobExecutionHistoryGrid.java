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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
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

	private JobExecutionHistoryDataSource dataSource;
	
	/**
	 * 
	 */
	public JobExecutionHistoryGrid() {
		addColumns();
		//this(new ArrayList<JobMonitorHistoryVo>());
		dataSource = new JobExecutionHistoryDataSource();
		setDataSource(dataSource);
		setShowAllRecords(true); 
		setAutoFetchData(false);
		setDrawAheadRatio(4);
		setCanExpandRecords(true);
		
		invalidateCache();
		fetchData();
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
		//populateData();
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
		//populateData();
	}

	/**
	 * 
	 */
	private void populateData() {
		
		if(null == jobExecutionData || jobExecutionData.size() <= 0)
			return;
		Window.alert("JobExecutionHistoryGrid.populateData()");
		for (int i = 0; i < jobExecutionData.size(); i++) {
			JobMonitorHistoryVo monitorVo = jobExecutionData.get(i);
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("jobName", monitorVo.getJobName());
			record.setAttribute("status", monitorVo.getStatus());
			record.setAttribute("lastStartedTime", monitorVo.getLastStartedTime());
			record.setAttribute("lastEndedTime", monitorVo.getLastEndedTime());
			
			record.setAttribute("executionDetails", monitorVo.getExecutionDetails());
			Window.alert("JobExecutionHistoryGrid.populateData():: Record: " + record);
			addData(record);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.smartgwt.client.widgets.grid.ListGrid#getExpansionComponent(com.smartgwt.client.widgets.grid.ListGridRecord)
	 */
	@Override
	protected Canvas getExpansionComponent(ListGridRecord record) {
		Window.alert("JobExecutionHistoryGrid.getExpansionComponent()");
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
		
		List<JobExecutionHistoryVo> execHistoryVos = (List<JobExecutionHistoryVo>)record.getAttributeAsObject("executionDetails");
		for (int i = 0; i < execHistoryVos.size(); i++) {
			JobExecutionHistoryVo vo = execHistoryVos.get(i);
			ListGridRecord subRecord = new ListGridRecord();
			subRecord.setAttribute("jobExecutionId", vo.getJobExecutionId());
			subRecord.setAttribute("jobName", vo.getJobName());
			subRecord.setAttribute("exitCode", vo.getExitCode());
			subRecord.setAttribute("startTime", vo.getStartTime());
			subRecord.setAttribute("endTime", vo.getEndTime());
			subRecord.setAttribute("exitMessage", vo.getExitMessage());
			executionDetailsGrid.addData(subRecord);
		}
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
		Window.alert("JobExecutionHistoryGrid.createRecordComponent():: Create button");
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