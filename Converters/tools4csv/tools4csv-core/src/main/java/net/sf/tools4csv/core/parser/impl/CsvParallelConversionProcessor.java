package net.sf.tools4csv.core.parser.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.tools4csv.core.converter.CsvConverter;
import net.sf.tools4csv.core.parser.CsvConversionProcessor;

import org.apache.log4j.Logger;


public class CsvParallelConversionProcessor implements CsvConversionProcessor {

	private static final Logger logger = Logger.getLogger(CsvParallelConversionProcessor.class);
	private List<CsvConverter> converters;
	
	public CsvParallelConversionProcessor() {
		converters = new ArrayList<CsvConverter>();
	}
	
	public CsvParallelConversionProcessor(List<CsvConverter> converters) {
		if(null != converters)
			this.converters = converters;
	}

	public List<CsvConverter> getConverters() {
		return converters;
	}


	@Override
	public void start() {
		logger.info("Start CSV converters");
		if(null != getConverters() && getConverters().size() > 0){
			for (CsvConverter csvConverter : getConverters()) {
				csvConverter.processConversion();
			}
		} else {
			logger.warn("No Converters found...");
			return;
		}
		logger.info("Completed CSV converters");
	}

}
