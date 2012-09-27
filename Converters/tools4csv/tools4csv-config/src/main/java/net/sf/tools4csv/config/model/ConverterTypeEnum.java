package net.sf.tools4csv.config.model;

public enum ConverterTypeEnum {

	INVALID("INVALID", "Invalid"),
	CSV_TO_CSV("csv-to-csv", "Convert from csv to csv"),
	CSV_TO_DB("csv-to-db", "Convert from file to database"),
	CSV_TO_XLS("csv-to-xls", "Convert from file to xls"),
	CSV_TO_POJO("csv-to-pojo", "csv-to-pojo");
	
	private final String type;
	private final String description;
	
	private ConverterTypeEnum(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}
	
	public static ConverterTypeEnum getConverterType(String key){
		if(CSV_TO_CSV.getType().equals(key))
			return CSV_TO_CSV;
		if(CSV_TO_DB.getType().equals(key))
			return CSV_TO_DB;
		if(CSV_TO_XLS.getType().equals(key))
			return CSV_TO_XLS;
		if(CSV_TO_POJO.getType().equals(key))
			return CSV_TO_POJO;
		return INVALID;
	}
	
	
}
