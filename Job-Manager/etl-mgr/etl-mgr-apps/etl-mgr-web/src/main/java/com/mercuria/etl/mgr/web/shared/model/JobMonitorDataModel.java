/**
 * File :: com.mercuria.etl.mgr.web.shared.model.JobMonitorDataModel
 * Date :: 23-Jul-2013
 */
package com.mercuria.etl.mgr.web.shared.model;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionGridModel;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class JobMonitorDataModel<T extends IsSerializable> implements GWTCollectionGridModel<T> {

	private final List<T> dataList;
	private final int columnCount;
	private Object[][] data;
	private final String[] columnNames;
	private final String[] columnAttributeNames;
	private final int[] columnWidths;
	
	
	public JobMonitorDataModel(List<T> dataList) {
		this.dataList = dataList;
		
		this.columnCount = 2;
		data = new Object[dataList.size()][columnCount];
		
		this.columnNames = new String[columnCount];
		this.columnAttributeNames = new String[columnCount];
		
		columnNames[0] = "Job Name";
		columnNames[1] = "Job Status";
		
		columnAttributeNames[0] = "jobName";
		columnAttributeNames[1] = "status";
		
		columnWidths = new int[columnCount];
		columnWidths[0] = 150;
		columnWidths[1] = 80;
		populateModel();
	}
	
	private void populateModel(){
		
	}
	
	@Override
	public void reload(List<T> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ListGridField getListGridField(int columnIndex) {
		return new ListGridField(getColumnName(columnIndex));
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
		return dataList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = value;
	}

	@Override
	public List<T> getDataList() {
		return dataList;
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
