package com.gwt.sample.mvc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
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
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtMvcSample implements EntryPoint {
	@Override
	public void onModuleLoad() {
		Window.enableScrolling(false);
	    Window.setMargin("0px");
	    Cookies.setCookie("skin_name_2_4", "EnterpriseBlue");
		
		VLayout vLayout = new VLayout();
		vLayout.setWidth100();
		vLayout.setHeight100();
		
		vLayout.addMember(new Label("List JSON REST example - Using Spring MVC"));
		
		ListGrid jobMonitorGrid = new ListGrid();
		jobMonitorGrid.setWidth100();
		jobMonitorGrid.setHeight100();
		
		EmployeeRestDataSource dataSource = EmployeeRestDataSource.getInstance();
		
		/*
		
		
		dataSource.setDataFormat(DSDataFormat.JSON);
		
		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);  
        fetch.setDataProtocol(DSProtocol.POSTMESSAGE); 
        dataSource.setOperationBindings(fetch);
        
        dataSource.setFetchDataURL(GWT.getHostPageBaseURL() + "emp/rest/getAll.rpc");*/
        
        jobMonitorGrid.setDataSource(dataSource);
		jobMonitorGrid.setWidth100();  
		jobMonitorGrid.setHeight100();  
		jobMonitorGrid.setShowAllRecords(true); 
		jobMonitorGrid.setAutoFetchData(true);
		
		
		vLayout.addMember(jobMonitorGrid);
		
		RootPanel.get().add(vLayout);
		
		
	}
}

