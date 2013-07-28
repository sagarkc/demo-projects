package com.mercuria.etl.mgr.web.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionDataGrid;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionGridModel;
import com.mercuria.etl.mgr.web.client.core.GWTGridColumnHeader;
import com.mercuria.etl.mgr.web.shared.model.JobMonitorData;
import com.mercuria.etl.mgr.web.shared.model.JobMonitorDataModel;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RealTimeJobMonitorView extends VLayout {

	private final Button addJobButton = new Button("Add Job");
	private final Button removeJobButton = new Button("Remove Job");
	private final Button refreshButton = new Button("Refresh");
	private final GWTCollectionDataGrid<JobMonitorData> jobMonitorGrid = new GWTCollectionDataGrid<JobMonitorData>();
	private final List<GWTGridColumnHeader> columnHeaders = new ArrayList<GWTGridColumnHeader>();
	
	public RealTimeJobMonitorView() {
		setStyleName("job-monitor-realTime");
		setWidth100();
		setHeight100();
		setLayoutMargin(2);
		initComponents();
	}

	private void initComponents() {
		HLayout header = new HLayout();
		header.setWidth100();
		header.setHeight(25);
		header.addMember(new Label("Real Time view"));
		header.addMember(addJobButton);
		header.addMember(removeJobButton);
		header.addMember(refreshButton);
		addMember(header);
		
		columnHeaders.add(new GWTGridColumnHeader("JOB Name", "jobName", ListGridFieldType.TEXT));
		columnHeaders.add(new GWTGridColumnHeader("Status", "status"));
		columnHeaders.add(new GWTGridColumnHeader("Start Time", "startTime", ListGridFieldType.DATETIME));
		columnHeaders.add(new GWTGridColumnHeader("End Time", "endTime"));
		columnHeaders.add(new GWTGridColumnHeader("Exit Code", "exitCode"));
		columnHeaders.add(new GWTGridColumnHeader("Exit Message", "exitMessage"));
		
		jobMonitorGrid.setWidth100();  
		jobMonitorGrid.setHeight100();  
		jobMonitorGrid.setShowAllRecords(true); 
		
		//GWTCollectionGridModel<JobMonitorData> model = new JobMonitorCollectionDataModel(columnHeaders);
		
		jobMonitorGrid.setWidth100();  
		jobMonitorGrid.setHeight100();  
		jobMonitorGrid.setShowAllRecords(true); 
		
        
        addMember(jobMonitorGrid);  
  
        
	}
	
}
