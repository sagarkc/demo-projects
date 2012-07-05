package com.gmail.sabuj.career.javatest.model;

import java.util.Date;

public class NgmImportData {

	private Date date;
	private double quantity;
	
	public NgmImportData() {
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "NgmImportData [date=" + date + ", quantity=" + quantity + "]";
	}
	
}
