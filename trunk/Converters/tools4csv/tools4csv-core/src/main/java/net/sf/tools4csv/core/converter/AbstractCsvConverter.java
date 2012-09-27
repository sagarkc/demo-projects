package net.sf.tools4csv.core.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.tools4csv.config.model.Column;
import net.sf.tools4csv.config.model.Source;
import net.sf.tools4csv.config.model.Target;
import net.sf.tools4csv.core.IllegalConverterException;
import net.sf.tools4csv.core.util.FormatManager;
import net.sf.tools4csv.core.util.ReflectionUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public abstract class AbstractCsvConverter implements CsvConverter {

	private static final Logger logger = Logger.getLogger(AbstractCsvConverter.class);
	
	private String id;
	private Source source;
	private Target target;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public Object getOutputData() {
		return null;
	}

	public void processConversion() {
		if(null == getSource() || null == getTarget()){
			throw new IllegalConverterException("Source and/or Target not found");
		}
		if(validateSource() && validateTarget()){
			BufferedReader bufferedReader = null;
			try{
				bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(getSource().getFileName()))
						);
				String line = null;
				List<String> lines = new ArrayList<String>();
				int sourceBatchSize = (getSource().isHasHeader() && getSource().getSelect().getBatchSize() > 0) 
						? getSource().getSelect().getBatchSize() + 1
						: 0;
				int lineCount = 0;
				if(getSource().isHasHeader())
					bufferedReader.readLine();
				if(getTarget().isHasHeader()){
					writeTargetHeader(processHeader());
				}
				while((line = bufferedReader.readLine()) != null){
					lineCount ++;
					if(!"".equals(line.trim())){
						if(sourceBatchSize > 0){
							lines.add(line);
							if((lineCount % (sourceBatchSize-1)) == 0){
								List<Map<String, String>> targetColumnMaps = getCollumnValues(lines);
								writeToTarget(targetColumnMaps);
								lines = new ArrayList<String>();
								continue;
							} 
							
						} else {
							Map<String, String> targetColumns = getCollumnValues(line);
							if(targetColumns.isEmpty()){
								continue;
							}
							writeToTarget(targetColumns);
						}
					}
				}
				// remaining lines if processed in batch
				if(sourceBatchSize > 0 && null != lines && lines.size() > 0){
					List<Map<String, String>> targetColumnMaps = getCollumnValues(lines);
					writeToTarget(targetColumnMaps);
					lines = new ArrayList<String>();
				}
			} catch (FileNotFoundException e) {
				throw new IllegalConverterException("Conversion Not Supported. " + e.getMessage() , e);
			} catch (IOException e) {
				throw new IllegalConverterException("Conversion Not Supported. " + e.getMessage() , e);
			} finally {
				if(null != bufferedReader){
					try {
						bufferedReader.close();
					} catch (IOException e) {
						// ignore
					}
				}
				try {
					closeWriter();
				} catch (IOException e) {
					// ignore
				}
			}
		} else {
			logger.info("No valid source and/or target found... process did not start.");
		}
		
	}

	protected abstract void writeTargetHeader(String targetHeader) throws IOException ;

	protected String processHeader() {
		StringBuffer targetBuffer = new StringBuffer();
		for(int i=0 ; i < getTarget().getWrite().getColumns().size() ; i++){
			Column column = getTarget().getWrite().getColumns().get(i);
			if(null != column){
				targetBuffer.append(column.getHeader());
			}
			if(i < getTarget().getWrite().getColumns().size()-1){
				targetBuffer.append(getTarget().getSeparator());
			}
		}
		return targetBuffer.toString();
	}

	protected abstract void closeWriter() throws IOException ;

	protected abstract void writeToTarget(Map<String, String> targetColumns) throws IOException ;

	protected abstract void writeToTarget(List<Map<String, String>> targetColumnMaps) throws IOException ;

	public abstract boolean validateTarget() ;

	public boolean validateSource(){
		File file = new File(source.getFileName());
		if(!file.exists()){
			return false;
		}
		if(null == source.getSelect()){
			return false;
		}
		if(null == source.getSelect().getColumns() || source.getSelect().getColumns().size() <= 0){
			return false;
		}
		
		return true;
	}

	protected Map<String, String> getCollumnValues(String line) {
		Map<String, String> targetColumns = new HashMap<String, String>();
		String[] tokens = line.split(getSource().getSeparator());
		if(null != tokens && tokens.length > 0){
			if(getSource().getSelect().getColumns().size() > 0 && tokens.length >= getSource().getSelect().getColumns().size()){
				for (Column c : getSource().getSelect().getColumns()) {
					targetColumns.put(c.getName(), tokens[c.getIndex()]);
				}
			} else {
				for (int i = 0; i < tokens.length; i++) {
					targetColumns.put(""+i, tokens[i]);
				}
			}
		}
		return targetColumns;
	}
	
	protected List<Map<String, String>> getCollumnValues(List<String> lines) {
		List<Map<String, String>> targetColumns = new ArrayList<Map<String,String>>();
		for (String line : lines) {
			targetColumns.add(getCollumnValues(line));
		}
		return targetColumns;
	}
	
	protected String getFormattedString(Column targetColumn, String sourceValue){
		if(null == sourceValue)
			return targetColumn.getDefaultValue();
		
		if(targetColumn.isFormat()){
			String sourceFormat = getSource().getSelect().getColumnByName(targetColumn.getName()).getPattern();
			try {
				Format format = FormatManager.getInstance().getDataFormat(targetColumn.getType(), targetColumn.getPattern());
				if(null == format){
					logger.error("Unsupported format pattern: ", new IllegalArgumentException("Unsupported format pattern: " + targetColumn.getPattern()));
					return sourceValue.toString();
				}
				
				String targetValue = format.format(ReflectionUtil.getObject(targetColumn.getType(), sourceValue, sourceFormat));
				if(targetColumn.isUseQuote())
					return getCsvString(targetValue, true); 
				return getCsvString(targetValue) ;
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
				return sourceValue.toString();
			}
		}
		if(targetColumn.isUseQuote()){
			return getCsvString(sourceValue, true);
		}
		return getCsvString(sourceValue.toString());
	}
	
	public static String getCsvString(String text){
		if(StringUtils.isEmpty(text)){
			return "";
		}
		if(text.contains(",")
				|| text.contains("\n")
				|| text.contains("\n\r")
				|| text.startsWith("e")
				|| text.startsWith("E")){
			return getCsvString(text, true);
		}
		return text;
	}
	
	public static String getCsvString(String text, boolean userQuote){
		text = text.replaceAll("\"", "\"\"");
		return "\"" + text + "\"";
	}
	
}
