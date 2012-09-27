package net.sf.tools4csv.core.converter.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.tools4csv.config.model.Property;
import net.sf.tools4csv.core.IllegalConverterException;
import net.sf.tools4csv.core.converter.AbstractCsvConverter;
import net.sf.tools4csv.core.util.ReflectionUtil;

import org.apache.log4j.Logger;


public class CsvToPojoConverter extends AbstractCsvConverter{
	private static Logger logger = Logger.getLogger(CsvToPojoConverter.class);
	
	private List<Object> data = new ArrayList<Object>();

	@Override
	public Object getOutputData() {
		return data;
	}

	@Override
	protected void writeTargetHeader(String targetHeader) throws IOException {
		// NO need to implement
	}

	@Override
	protected void closeWriter() throws IOException {
		// NO need to implement
	}

	@Override
	protected void writeToTarget(Map<String, String> targetColumns)
			throws IOException {
		Object dataObject = ReflectionUtil.createObject(getTarget().getCollect().getTargetClassName());
		if(null == dataObject)
			return;
		
		for(int i=0 ; i < getTarget().getCollect().getProperties().size() ; i++){
			Property property = getTarget().getCollect().getProperties().get(i);
			if(null != property){
				String sourceFormat = getSource().getSelect().getColumnByName(property.getMapTo()).getPattern();
				try {
					ReflectionUtil.updateObject(dataObject, property.getName(), 
							ReflectionUtil.getObject(property.getType(), targetColumns.get(property.getMapTo()), sourceFormat));
				} catch (ParseException e) {
					throw new IllegalConverterException(e.getMessage(),e);
				}
			}
		}
		
		data.add(dataObject);
	}

	@Override
	protected void writeToTarget(List<Map<String, String>> targetColumnMaps)
			throws IOException {
		for (int i = 0; i < targetColumnMaps.size(); i++) {
			writeToTarget(targetColumnMaps.get(i));
		}
	}

	@Override
	public boolean validateTarget() {
		return true;
	}

	
}
