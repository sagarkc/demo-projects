package net.sf.tools4csv.config.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {

	private boolean parallelConverters;
	private boolean exitOnError;
	private Map<String, Converter> converters;
	private List<Converter> converterList;
	
	public Configuration() {
		this.converters = new HashMap<String, Converter>();
		this.converterList = new ArrayList<Converter>();
	}
	
	public void addConverter(Converter converter){
		if(null == converter)
			return;
		converters.put(converter.getId(), converter);
		converterList.add(converter);
	}

	public boolean isParallelConverters() {
		return parallelConverters;
	}

	public void setParallelConverters(boolean parallelConverters) {
		this.parallelConverters = parallelConverters;
	}

	public Map<String, Converter> getConverters() {
		return converters;
	}

	public List<Converter> getConverterList() {
		return converterList;
	}

	public boolean isExitOnError() {
		return exitOnError;
	}

	public void setExitOnError(boolean exitOnError) {
		this.exitOnError = exitOnError;
	}
	
	
}

