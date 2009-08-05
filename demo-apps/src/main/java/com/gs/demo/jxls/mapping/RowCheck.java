package com.gs.demo.jxls.mapping;

import java.util.ArrayList;
import java.util.List;

public class RowCheck {

	private String offset;
	
	private List<CellCheck> cellCheckList;
	
	 public RowCheck() {
		cellCheckList = new ArrayList<CellCheck>();
	}

	/**
	 * @return the offset
	 */
	public String getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(String offset) {
		this.offset = offset;
	}

	/**
	 * @return the cellCheckList
	 */
	public List<CellCheck> getCellCheckList() {
		return cellCheckList;
	}

	/**
	 * @param cellCheckList the cellCheckList to set
	 */
	public void setCellCheckList(List<CellCheck> cellCheckList) {
		this.cellCheckList = cellCheckList;
	}
	 
	 
}
