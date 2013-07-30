package com.mercuria.etl.mgr.web.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.mercuria.etl.mgr.web.WebConstants;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionDataGrid;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionGridModel;
import com.mercuria.etl.mgr.web.client.core.GWTGridColumnHeader;
import com.mercuria.etl.mgr.web.client.service.JobMonitorService;
import com.mercuria.etl.mgr.web.shared.model.JobMonitorData;
import com.mercuria.etl.mgr.web.shared.model.JobMonitorDataModel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RealTimeJobMonitorView extends VLayout {

	private final Button addJobButton = new Button("Add Job");
	private final Button removeJobButton = new Button("Remove Job");
	private final Button refreshButton = new Button("Refresh");
	private final ListGrid jobMonitorGrid = new ListGrid();
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
		
		
		jobMonitorGrid.setWidth100();  
		jobMonitorGrid.setHeight100();  
		jobMonitorGrid.setShowAllRecords(true); 
		
		XJSONDataSource dataSource = new XJSONDataSource();
		dataSource.setDataURL(GWT.getHostPageBaseURL() + JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT);
		
		DataSourceField field = new DataSourceField("jobName", FieldType.TEXT, "Job Name");
		dataSource.addField(field);
		field = new DataSourceField("exitCode", FieldType.TEXT, "Exit Code");
		dataSource.addField(field);
		field = new DataSourceField("startTime", FieldType.DATETIME, "Job Start Time");
		dataSource.addField(field);
		field = new DataSourceField("endTime", FieldType.DATETIME, "Job End Time");
		dataSource.addField(field);
		
		dataSource.setDataFormat(DSDataFormat.JSON);
		
		jobMonitorGrid.setDataSource(dataSource);
		jobMonitorGrid.setWidth100();  
		jobMonitorGrid.setHeight100();  
		jobMonitorGrid.setShowAllRecords(true); 
		jobMonitorGrid.setAutoFetchData(true);
        
        addMember(jobMonitorGrid);  
  
        
	}
	
}
