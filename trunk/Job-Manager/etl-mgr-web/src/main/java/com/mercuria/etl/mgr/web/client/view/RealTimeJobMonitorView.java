package com.mercuria.etl.mgr.web.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.mercuria.etl.mgr.web.WebConstants;
import com.mercuria.etl.mgr.web.client.core.GWTGridColumnHeader;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
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
		
		RealtimeJobHistoryDS dataSource = new RealtimeJobHistoryDS();
		
		DataSourceField field = new DataSourceField("jobName", FieldType.TEXT, "Job Name");
		dataSource.addField(field);
		field = new DataSourceField("exitCode", FieldType.TEXT, "Exit Code");
		dataSource.addField(field);
		field = new DataSourceField("startTime", FieldType.DATETIME, "Job Start Time");
		dataSource.addField(field);
		field = new DataSourceField("endTime", FieldType.DATETIME, "Job End Time");
		dataSource.addField(field);
		
		dataSource.setDataFormat(DSDataFormat.JSON);
		
		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);  
        fetch.setDataProtocol(DSProtocol.POSTMESSAGE); 
        dataSource.setOperationBindings(fetch);
        
        dataSource.setFetchDataURL(GWT.getHostPageBaseURL() + WebConstants.FETCH_HISTORICAL_JOB_MONITOR_DATA
        		+ WebConstants.RPC_EXT);
		
		jobMonitorGrid.setDataSource(dataSource);
		jobMonitorGrid.setWidth100();  
		jobMonitorGrid.setHeight100();  
		jobMonitorGrid.setShowAllRecords(true); 
		jobMonitorGrid.setAutoFetchData(true);
        
        addMember(jobMonitorGrid);  
  
        
	}
	
}

class RealtimeJobHistoryDS extends RestDataSource {
	@Override
	protected Object transformRequest(DSRequest dsRequest) {
		dsRequest.setContentType("application/json");
		JavaScriptObject jso = dsRequest.getData();
		String jsonText = JSON.encode(jso);
		return jsonText;
	}

	@Override
	protected void transformResponse(DSResponse response, DSRequest request,
			Object data) {
		super.transformResponse(response, request, data);
	}
}
