/**
 * File :: com.mercuria.etl.mgr.web.client.core.GWTJsonListGridModel
 * Date :: 24-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.core;

import java.util.List;

import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface GWTJsonListGridModel<T> {

public int getColumnCount() ;
	
	public String getColumnName(int columnIndex) ;

	public int getRowCount();

	public Object getValueAt(int rowIndex, int columnIndex) ;
	
	public boolean isCellEditable(int rowIndex, int columnIndex) ;

	public void setValueAt(Object value, int rowIndex, int columnIndex) ;
	
	public List<T> getDataList() ;
	
	public List<String> getColumnNameList() ;

	public String getColumnAttributeName(int columnIndex);

	public int getColumnWidth(int columnIndex);

	/**
	 * @param i
	 * @return
	 */
	public ListGridField getListGridField(int columnIndex);
	
}
