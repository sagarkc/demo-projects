package com.gs.demo.jxls.mapping;

import java.util.ArrayList;
import java.util.List;

public class LoopBreakCondition {

	private List<RowCheck> rowCheckList;
	
	public LoopBreakCondition() {
		rowCheckList = new ArrayList<RowCheck>();
	}

	/**
	 * @return the rowCheckList
	 */
	public List<RowCheck> getRowCheckList() {
		return rowCheckList;
	}

	/**
	 * @param rowCheckList the rowCheckList to set
	 */
	public void setRowCheckList(List<RowCheck> rowCheckList) {
		this.rowCheckList = rowCheckList;
	}
	
	
}
