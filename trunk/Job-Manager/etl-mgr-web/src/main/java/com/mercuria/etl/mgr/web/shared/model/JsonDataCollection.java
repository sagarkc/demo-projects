package com.mercuria.etl.mgr.web.shared.model;

import java.util.ArrayList;
import java.util.List;

public class JsonDataCollection<T> {

	public static final String RECORD_FIELD = "rows";
	public static final String TOTAL_FIELD = "total";
	public static final String PAGE_FIELD = "page";
	
	private List<T> records;
	private long totalRecords;
	private int pageNumber;
	
	public JsonDataCollection() {
		records = new ArrayList<T>();
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
	
	
}
