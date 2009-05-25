package com.conversion.csv;

public interface CSVObject {

	
	char DEFAULT_SEPERATOR = ',';
	boolean IS_QUOTE_DELEMITED = false;
	
	public String toCsv(char delem);
	
}
