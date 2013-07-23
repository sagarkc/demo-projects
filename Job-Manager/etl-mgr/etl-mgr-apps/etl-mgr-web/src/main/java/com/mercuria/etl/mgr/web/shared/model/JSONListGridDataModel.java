/**
 * File :: com.mercuria.etl.mgr.web.shared.model.JSONListGridDataModel
 * Date :: 23-Jul-2013
 */
package com.mercuria.etl.mgr.web.shared.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionGridModel;
import com.mercuria.etl.mgr.web.client.core.GWTGridColumnHeader;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class JSONListGridDataModel<T extends JavaScriptObject> implements GWTCollectionGridModel<T> {

	private final List<T> jsonDataList;
	
	private final int columnCount;
	private Object[][] dataTable;
	private int rowCount;
	private final String[] columnNames;
	private final String[] columnAttributeNames;
	private final int[] columnWidths;
	private final List<GWTGridColumnHeader> columnHeaders;
	private final List<ListGridField> listGridFields;
	
	public JSONListGridDataModel(List<T> jsonDataObjects, List<GWTGridColumnHeader> columnHeaders) {
		this.jsonDataList = jsonDataObjects;
		this.columnHeaders = columnHeaders;
		this.columnCount = columnHeaders.size();
		this.columnNames = new String[columnCount];
		this.columnAttributeNames = new String[columnCount];
		this.rowCount = jsonDataObjects.size();
		columnWidths = new int[columnCount];
		listGridFields = new ArrayList<ListGridField>();
		//dataTable = new Object[dataList.size()][columnCount];
		
		for (int i = 0; i < columnCount; i++) {
			GWTGridColumnHeader header = columnHeaders.get(i);
			columnNames[i] = header.getTitle();
			columnAttributeNames[i] = header.getAttribute();
			listGridFields.add(header.createListGridField());
		}
		
		populateModel();
	}
	
	public void populateModel(){
		for (int i = 0; i < jsonDataList.size(); i++) {
			JSONObject jsonObject = new JSONObject(jsonDataList.get(i));
			for (int j = 0; j < columnAttributeNames.length; j++) {
				dataTable[i][j] = jsonObject.get(columnAttributeNames[j]).isString().toString();
			}
		}
		
	}

	@Override
	public ListGridField getListGridField(int columnIndex) {
		return listGridFields.get(columnIndex);
	}
	
	@Override
	public List<T> getDataList() {
		return new ArrayList<T>();
	}
	
	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return dataTable[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		dataTable[rowIndex][columnIndex] = value;
	}

	
	@Override
	public List<String> getColumnNameList() {
		return Arrays.asList(columnNames);
	}

	@Override
	public String getColumnAttributeName(int columnIndex) {
		return columnAttributeNames[columnIndex];
	}

	@Override
	public int getColumnWidth(int columnIndex) {
		return columnWidths[columnIndex];
	}
	
	
	
}
