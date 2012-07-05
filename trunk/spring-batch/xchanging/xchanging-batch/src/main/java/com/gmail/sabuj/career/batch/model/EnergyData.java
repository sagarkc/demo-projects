package com.gmail.sabuj.career.batch.model;

import java.io.Serializable;

public class EnergyData implements Serializable {

	/**
	 * serialVersionUID = -4967698450552529000L;
	 */
	private static final long serialVersionUID = -4967698450552529000L;

	private String msnCode;
	private String date;
	private String value;
	private String columnOrder;
	private String description;
	private String unit;

	public EnergyData() {
		// TODO Auto-generated constructor stub
	}

	public String getMsnCode() {
		return msnCode;
	}

	public void setMsnCode(String msnCode) {
		this.msnCode = msnCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(String columnOrder) {
		this.columnOrder = columnOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
