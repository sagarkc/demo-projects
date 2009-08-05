package com.gs.demo.jxls.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Section implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481506304315246653L;
	
	private String startRow;
	private String endRow;
	
	private List<SectionMapping> mappingList;
	
	public Section() {
		mappingList = new ArrayList<SectionMapping>();
	}

	/**
	 * @return the startRow
	 */
	public String getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the endRow
	 */
	public String getEndRow() {
		return endRow;
	}

	/**
	 * @param endRow the endRow to set
	 */
	public void setEndRow(String endRow) {
		this.endRow = endRow;
	}

	/**
	 * @return the mappingList
	 */
	public List<SectionMapping> getMappingList() {
		return mappingList;
	}

	/**
	 * @param mappingList the mappingList to set
	 */
	public void setMappingList(List<SectionMapping> mappingList) {
		this.mappingList = mappingList;
	}
	
	
}
