package net.sf.tools4csv.core.parser;

import java.util.List;

import net.sf.tools4csv.core.converter.CsvConverter;



public interface CsvConversionProcessor {

	void start();
	
	List<CsvConverter> getConverters();
}
