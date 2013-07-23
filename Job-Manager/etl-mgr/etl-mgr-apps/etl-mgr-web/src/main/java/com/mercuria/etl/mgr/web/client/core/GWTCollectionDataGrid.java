package com.mercuria.etl.mgr.web.client.core;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class GWTCollectionDataGrid<T extends JavaScriptObject> extends ListGrid{

	private GWTCollectionGridModel<T> model;
	
	public GWTCollectionDataGrid() {
		
	}

	public GWTCollectionGridModel<T> getModel() {
		return model;
	}

	public void setModel(GWTCollectionGridModel<T> model) {
		this.model = model;
		Window.alert("Model init...");
		initGrid();
	}

	private void initGrid() {
		List<String> columnNames = model.getColumnNameList();
		ListGridField[] headers = new ListGridField[columnNames.size()];
		for (int i = 0; i < columnNames.size(); i++) {
			headers[i] = model.getListGridField(i);
			Window.alert("Header: " + headers[i].getName());
		}
		Window.alert("Headers: " + headers.length);
		setFields(headers);
		
		setRowData();
	}

	private void setRowData() {
		ListGridRecord[] records = new ListGridRecord[model.getRowCount()] ;
		for(int r=0; r < model.getRowCount(); r++){
			ListGridRecord record = new ListGridRecord();
			for(int c=0; c < model.getColumnCount(); c++){
				record.setAttribute(model.getColumnAttributeName(c), model.getValueAt(r, c));  
				Window.alert("" + model.getValueAt(r, c));
			}
			records[r] = record;
		}
		setData(records);
	}
	
}
