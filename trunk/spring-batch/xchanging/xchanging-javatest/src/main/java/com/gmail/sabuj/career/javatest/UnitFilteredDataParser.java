package com.gmail.sabuj.career.javatest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.gmail.sabuj.career.common.model.TaskDetail;
import com.gmail.sabuj.career.common.util.CollectionUtil;
import com.gmail.sabuj.career.common.util.CsvWriter;
import com.gmail.sabuj.career.common.util.StringUtil;
import com.gmail.sabuj.career.javatest.model.NgmReportData;
import com.gmail.sabuj.career.javatest.model.NgmSource;

public class UnitFilteredDataParser extends FilteredDataParser {

	private static final Logger logger = Logger.getLogger(UnitFilteredDataParser.class);
	
	public UnitFilteredDataParser(String sourceFileName, String targetFileName,
			TaskDetail taskDetail) throws IOException {
		super(sourceFileName, targetFileName, taskDetail);
	}

	@Override
	public boolean parse(final HSSFSheet currentSheet) {
		logger.info("Parse for Unit: " + getTaskDetail().getUnitType());
		Map<String, Integer> sourceKeyColMap = populateSourceKeyIndices(currentSheet);
		if(sourceKeyColMap.size() <= 0){
			logger.info("Unit data not found.");
			return false;
		}
		
		List<String> sourceKeyList = new ArrayList<String>(sourceKeyColMap.keySet());
		
		List<NgmReportData> ngmReportDataList = new ArrayList<NgmReportData>();
		
		for (String sk : sourceKeyList) {
			ngmReportDataList.addAll(populateDataForUnit(currentSheet, sk ,sourceKeyColMap.get(sk)));
		}
		
		if(!CollectionUtil.hasElements(ngmReportDataList)){
			logger.info("No Data found for writing in csv");
			return true;
		}
		
		CsvWriter<NgmReportData> csvWriter = new CsvWriter<NgmReportData>();
		try {
			csvWriter.write(getTargetFileName()+"_"+getTaskDetail().getTaskName()+".csv", ngmReportDataList);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		
		return false;
	}

	private Collection<? extends NgmReportData> populateDataForUnit(
			final HSSFSheet currentSheet, final String sourceKey, final int srcKeyColIndex) {
		List<NgmReportData> ngmReportDataList = new ArrayList<NgmReportData>();
		
		int lastRowIndex = (currentSheet.getPhysicalNumberOfRows() > 0) 
				? currentSheet.getPhysicalNumberOfRows()
				: -1;
		for(int i = DATA_ROW_NUM_FROM; i < lastRowIndex-1; i++){
			HSSFRow dataRow = currentSheet.getRow(i);
			if(null != dataRow){
				NgmReportData ngmReportData = new NgmReportData();
				ngmReportData.setReleaseDate(getReleaseDate());
				ngmReportData.setSourceKey(sourceKey);
				ngmReportData.setUnit(getTaskDetail().getUnitType());
				
				String dateStr = getCellValue(dataRow.getCell(DATE_COLUMN_NUM));
				if(StringUtil.hasValidContent(dateStr))
					try {
						ngmReportData.setReportDate(appSettings.getDateFormat().parse(dateStr));
					} catch (ParseException e) {
						logger.error(e.getMessage());
					}
				String valueStr = getCellValue(dataRow.getCell(srcKeyColIndex));
				if(StringUtil.hasValidContent(valueStr))
					ngmReportData.setValue(valueStr);
				ngmReportDataList.add(ngmReportData);
			}
		}
		
		return ngmReportDataList;
	}

	private Map<String, Integer> populateSourceKeyIndices(final HSSFSheet currentSheet) {
		Map<String, Integer> sourceKeyColMap = new LinkedHashMap<String, Integer>();
		
		HSSFRow srckeyRow = currentSheet.getRow(SRC_KEY_ROW_NUM);
		HSSFRow unitRow = currentSheet.getRow(UNIT_ROW_NUM);
		
		String srcKey = null;
		String currUnit = null;
		if(null != srckeyRow && null != unitRow ){
			int colCount = srckeyRow.getPhysicalNumberOfCells();
			for(int i=SRC_KEY_COL_NUM_FROM; i < colCount; i++){
				HSSFCell srckeyCell = srckeyRow.getCell(i);
				if(null != srckeyCell){
					srcKey = getCellValue(srckeyCell);
				}
				HSSFCell unitCell = unitRow.getCell(i);
				if(null != unitCell){
					String unitTxt = getCellValue(unitCell);
					if(StringUtil.hasValidContent(unitTxt)){
						Matcher unitMatcher = getUnitNamePattern().matcher(unitTxt);
						if(unitMatcher.matches() && unitMatcher.groupCount() >= 3){
							currUnit = unitMatcher.group(UNIT_GROUP_NUM);
							if(getTaskDetail().getUnitType().equals(currUnit)){
								sourceKeyColMap.put(srcKey, i);
							}
						}
					}
				}
			}
		}
		return sourceKeyColMap;
	}
	
}
