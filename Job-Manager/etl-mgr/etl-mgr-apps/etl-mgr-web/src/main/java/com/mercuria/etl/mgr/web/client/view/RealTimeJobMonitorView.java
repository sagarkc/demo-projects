package com.mercuria.etl.mgr.web.client.view;

import com.google.gwt.core.client.JavaScriptObject;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionDataGrid;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RealTimeJobMonitorView extends VLayout {

	private final Button addJobButton = new Button("Add Job");
	private final Button removeJobButton = new Button("Remove Job");
	private final Button refreshButton = new Button("Refresh");
	private final GWTCollectionDataGrid<JavaScriptObject> jobMonitorGrid = new GWTCollectionDataGrid<JavaScriptObject>();
	
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
		
		/*List<JobMonitorData> data = new ArrayList<JobMonitorData>();
		data.add(new JobMonitorData("A", "Running"));
		data.add(new JobMonitorData("B", "Running"));
		data.add(new JobMonitorData("C", "Completed"));
		
		GWTCollectionGridModel<JobMonitorData> model = new JobMonitorDataModel( data);
		
		jobMonitorGrid.setModel(model);*/
		
		jobMonitorGrid.setWidth100();  
		jobMonitorGrid.setHeight100();  
		jobMonitorGrid.setShowAllRecords(true); 
		
        
        addMember(jobMonitorGrid);  
  
        
	}
	
}
