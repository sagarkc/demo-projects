package com.mercuria.etl.mgr.web.client.view;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class HistoryJobMonitorView extends VLayout {

	private final Button addJobButton = new Button("Add Job");
	private final Button removeJobButton = new Button("Remove Job");
	private final Button refreshButton = new Button("Refresh");
	private final ListGrid countryGrid = new ListGrid();  
	
	public HistoryJobMonitorView() {
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
		
		
        countryGrid.setWidth100();  
        countryGrid.setHeight100();  
        countryGrid.setShowAllRecords(true); 
		
        ListGridField countryCodeField = new ListGridField("countryCode", "Flag", 40);  
        countryCodeField.setAlign(Alignment.CENTER);  
        countryCodeField.setType(ListGridFieldType.IMAGE);  
  
        ListGridField nameField = new ListGridField("countryName", "Country");  
        ListGridField capitalField = new ListGridField("capital", "Capital");  
        ListGridField continentField = new ListGridField("continent", "Continent");  
        countryGrid.setFields(countryCodeField, nameField, capitalField, continentField);  
        countryGrid.setCanResizeFields(true);  
        
        addMember(countryGrid);  
  
        
	}
	
}
