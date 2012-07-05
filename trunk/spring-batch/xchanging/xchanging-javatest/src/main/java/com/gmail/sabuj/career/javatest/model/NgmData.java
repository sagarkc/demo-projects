package com.gmail.sabuj.career.javatest.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class NgmData {

	private String dataSheetName;
	private Date releasedate;
	private Map<String, NgmSource> sourceDataMap = new LinkedHashMap<String, NgmSource>();

	public NgmData() {
		// TODO Auto-generated constructor stub
	}
	
	

	public NgmData(String dataSheetName, Date releasedate) {
		this.dataSheetName = dataSheetName;
		this.releasedate = releasedate;
	}



	public String getDataSheetName() {
		return dataSheetName;
	}

	public void setDataSheetName(String dataSheetName) {
		this.dataSheetName = dataSheetName;
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public Map<String, NgmSource> getSourceDataMap() {
		return sourceDataMap;
	}

	public void setSourceDataMap(Map<String, NgmSource> sourceDataMap) {
		this.sourceDataMap = sourceDataMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataSheetName == null) ? 0 : dataSheetName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NgmData other = (NgmData) obj;
		if (dataSheetName == null) {
			if (other.dataSheetName != null)
				return false;
		} else if (!dataSheetName.equals(other.dataSheetName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NgmData [dataSheetName=" + dataSheetName + ", releasedate="
				+ releasedate + "]";
	}
	
	
}
