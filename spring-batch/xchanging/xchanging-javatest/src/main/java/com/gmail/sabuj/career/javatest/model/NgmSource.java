package com.gmail.sabuj.career.javatest.model;

import java.util.ArrayList;
import java.util.List;

public class NgmSource {

	private String sourceKey;
	private String unit;
	private List<NgmImportData> importDataSet;

	public NgmSource() {
		importDataSet = new ArrayList<NgmImportData>();
	}

	public String getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<NgmImportData> getImportDataSet() {
		return importDataSet;
	}

	public void setImportDataSet(List<NgmImportData> importDataSet) {
		this.importDataSet = importDataSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sourceKey == null) ? 0 : sourceKey.hashCode());
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
		NgmSource other = (NgmSource) obj;
		if (sourceKey == null) {
			if (other.sourceKey != null)
				return false;
		} else if (!sourceKey.equals(other.sourceKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NgmSource [sourceKey=" + sourceKey + ", unit=" + unit + "]";
	}
	
}
