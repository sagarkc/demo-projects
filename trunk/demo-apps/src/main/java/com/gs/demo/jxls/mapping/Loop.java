package com.gs.demo.jxls.mapping;

import java.util.ArrayList;
import java.util.List;

public class Loop {

	/*private String ;
	 * startRow="START_ROW_NUMBER" endRow="END_ROW_NUMBER" items="ITEMS_MAP_KEY"
			var="currentObject" varType="CURRENT_CLASS_NAME"
	 */
	private String startRow;
	private String endRow;
	private String itemsMapKey;
	private String className;
	private LoopBreakCondition loopBreakCondition;
	private List<Section> sectionList;
	
	public Loop() {
		sectionList = new ArrayList<Section>();
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
	 * @return the itemsMapKey
	 */
	public String getItemsMapKey() {
		return itemsMapKey;
	}

	/**
	 * @param itemsMapKey the itemsMapKey to set
	 */
	public void setItemsMapKey(String itemsMapKey) {
		this.itemsMapKey = itemsMapKey;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the loopBreakCondition
	 */
	public LoopBreakCondition getLoopBreakCondition() {
		return loopBreakCondition;
	}

	/**
	 * @param loopBreakCondition the loopBreakCondition to set
	 */
	public void setLoopBreakCondition(LoopBreakCondition loopBreakCondition) {
		this.loopBreakCondition = loopBreakCondition;
	}

	/**
	 * @return the sectionList
	 */
	public List<Section> getSectionList() {
		return sectionList;
	}

	/**
	 * @param sectionList the sectionList to set
	 */
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
	
	
	
}
