package com.gs.demo.jxls.mapping;

import java.io.Serializable;

public class SectionMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3117341593420105307L;
	
	private String row;
	private String col;
	private String cell;
	private String nullAllowed;
	private String mappingFieldName;
	/**
	 * @return the row
	 */
	public String getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(String row) {
		this.row = row;
	}
	/**
	 * @return the col
	 */
	public String getCol() {
		return col;
	}
	/**
	 * @param col the col to set
	 */
	public void setCol(String col) {
		this.col = col;
	}
	/**
	 * @return the nullAllowed
	 */
	public String getNullAllowed() {
		return nullAllowed;
	}
	/**
	 * @param nullAllowed the nullAllowed to set
	 */
	public void setNullAllowed(String nullAllowed) {
		this.nullAllowed = nullAllowed;
	}
	/**
	 * @return the mappingFieldName
	 */
	public String getMappingFieldName() {
		return mappingFieldName;
	}
	/**
	 * @param mappingFieldName the mappingFieldName to set
	 */
	public void setMappingFieldName(String mappingFieldName) {
		this.mappingFieldName = mappingFieldName;
	}
	/**
	 * @return the cell
	 */
	public String getCell() {
		return cell;
	}
	/**
	 * @param cell the cell to set
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}
	
	
}
