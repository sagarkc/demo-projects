package com.mercuria.etl.mgr.web.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionDataGrid;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionGridModel;
import com.mercuria.etl.mgr.web.client.core.GWTGridColumnHeader;
import com.mercuria.etl.mgr.web.client.core.UIEventManager;
import com.mercuria.etl.mgr.web.client.event.HistoricalJobMonitorEvent;
import com.mercuria.etl.mgr.web.client.event.HistoricalJobMonitorEventListener;
import com.mercuria.etl.mgr.web.shared.model.JSONListGridDataModel;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class HistoryJobMonitorView extends VLayout implements HistoricalJobMonitorEventListener{

	private static UIEventManager uiEventManager = UIEventManager.getInstance();
	
	private final Button addJobButton = new Button("Add Job");
	private final Button removeJobButton = new Button("Remove Job");
	private final Button refreshButton = new Button("Refresh");
	private final GWTCollectionDataGrid<JavaScriptObject> jobMonitorHistoryGrid 
		= new GWTCollectionDataGrid<JavaScriptObject>();
	private final List<GWTGridColumnHeader> columnHeaders = new ArrayList<GWTGridColumnHeader>();;
	
	public HistoryJobMonitorView() {
		uiEventManager.addListener(HistoricalJobMonitorEvent.TYPE, this);
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
		header.addMember(new Label("Historical view"));
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
		
		jobMonitorHistoryGrid.setWidth100();  
		jobMonitorHistoryGrid.setHeight100();  
		jobMonitorHistoryGrid.setShowAllRecords(true); 
		
		GWTCollectionGridModel<JavaScriptObject> model 
			= new JSONListGridDataModel<JavaScriptObject>(columnHeaders);
		jobMonitorHistoryGrid.setModel(model);
        
        addMember(jobMonitorHistoryGrid);  
  
        
	}

	public GWTCollectionDataGrid<JavaScriptObject> getJobMonitorHistoryGrid() {
		return jobMonitorHistoryGrid;
	}

	@Override
	public void showHistoricalJobMonitorData(HistoricalJobMonitorEvent event) {
		jobMonitorHistoryGrid.reload(event.getJobMonitorData());
	}
	
	
}
