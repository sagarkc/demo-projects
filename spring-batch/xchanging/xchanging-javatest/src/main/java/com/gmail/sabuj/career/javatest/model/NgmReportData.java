package com.gmail.sabuj.career.javatest.model;

import java.util.Date;

import com.gmail.sabuj.career.common.util.CsvColumnHeader;

public class NgmReportData {

	
	private Date reportDate;
	private Date releaseDate;
	private String sourceKey;
	private String unit;
	private String value;
	
	public NgmReportData() {
		
	}

	@CsvColumnHeader(index=0, title="Date", useDateFormat=true)
	public Date getReportDate() {
		return reportDate;
	}

	@CsvColumnHeader(index=1, title="Release_Date", useDateFormat=true)
	public Date getReleaseDate() {
		return releaseDate;
	}

	@CsvColumnHeader(index=2, title="Source_Key")
	public String getSourceKey() {
		return sourceKey;
	}

	@CsvColumnHeader(index=3, title="Units")
	public String getUnit() {
		return unit;
	}

	@CsvColumnHeader(index=4, title="Value", useNumberFormat=true)
	public String getValue() {
		return value;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
