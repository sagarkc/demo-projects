package net.sf.tools4csv.core.converter.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import net.sf.tools4csv.config.model.Column;
import net.sf.tools4csv.core.converter.AbstractCsvConverter;

import org.apache.log4j.Logger;


public class CsvToCsvConverter extends AbstractCsvConverter {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CsvToCsvConverter.class);

	private BufferedWriter bufferedWriter;
	
	@Override
	protected void writeTargetHeader(String targetHeader) throws IOException {
		if(null != targetHeader && !"".equals(targetHeader.trim())){
			writeToTarget(targetHeader.trim() + System.getProperty("line.separator"));
		}
	}

	@Override
	protected void writeToTarget(Map<String, String> targetColumns) throws IOException {
		StringBuffer targetBuffer = new StringBuffer();
		for(int i=0 ; i < getTarget().getWrite().getColumns().size() ; i++){
			Column column = getTarget().getWrite().getColumns().get(i);
			if(null != column){
				targetBuffer.append(getFormattedString(column, targetColumns.get(column.getName())));
			}
			if(i < getTarget().getWrite().getColumns().size()-1){
				targetBuffer.append(getTarget().getSeparator());
			}
		}
		targetBuffer.append(System.getProperty("line.separator"));
		writeToTarget(targetBuffer.toString());
	}

	@Override
	protected void writeToTarget(List<Map<String, String>> targetColumnMaps) throws IOException {
		for (int i = 0; i < targetColumnMaps.size(); i++) {
			writeToTarget(targetColumnMaps.get(i));
		}
	}

	private void writeToTarget(String text) throws IOException {
		if(null == bufferedWriter){
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getTarget().getFileName(), true)));
		}
		bufferedWriter.write(text);
	}
	
	
	@Override
	protected void closeWriter() throws IOException {
		if(null != bufferedWriter){
			bufferedWriter.close();
		}
	}

	public boolean validateTarget() {
		if (null == getTarget().getWrite()) {
			return false;
		}
		if (null == getTarget().getWrite().getColumns()
				|| getTarget().getWrite().getColumns().size() <= 0) {
			return false;
		}
		return true;
	}
}
