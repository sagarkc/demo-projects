package com.gmail.sabuj.career.javatest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.gmail.sabuj.career.common.model.TaskDetail;
import com.gmail.sabuj.career.common.util.CollectionUtil;
import com.gmail.sabuj.career.common.util.CsvWriter;
import com.gmail.sabuj.career.common.util.StringUtil;
import com.gmail.sabuj.career.javatest.model.NgmReportData;

public class WorkSheetDataParser extends FilteredDataParser {

	private static final Logger logger = Logger.getLogger(WorkSheetDataParser.class);
	
	public WorkSheetDataParser(String sourceFileName, String targetFileName,
			TaskDetail taskDetail) throws IOException {
		super(sourceFileName, targetFileName, taskDetail);
	}

	
	
	@Override
	public boolean parse(HSSFSheet currentSheet) {
		logger.info("Parse for complete worksheet: " + getTaskDetail().getWorksheetName());
		
		List<NgmReportData> ngmReportDataList = populateWorkSheetData(currentSheet);
		
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
		
		
		return true;
	}

	
	private List<NgmReportData> populateWorkSheetData(final HSSFSheet currentSheet){
		List<NgmReportData> dataList = new ArrayList<NgmReportData>();
		Map<Integer, SKU> skuMap = populateSKUColumnMap(currentSheet);
		
		int rowCount = currentSheet.getPhysicalNumberOfRows() -1;
		HSSFRow srcKeyRow = currentSheet.getRow(SRC_KEY_ROW_NUM);
		int cellCount = srcKeyRow.getPhysicalNumberOfCells();
		
		for(int currentRowIndex=DATA_ROW_NUM_FROM; currentRowIndex < rowCount; currentRowIndex++){
			HSSFRow dataRow = currentSheet.getRow(currentRowIndex);
			if(null == dataRow)
				break;
			String reportDateStr = getCellValue(dataRow.getCell(DATE_COLUMN_NUM));
			Date reportDate = null;
			if(StringUtil.hasValidContent(reportDateStr)){
				try {
					reportDate = appSettings.getDateFormat().parse(reportDateStr);
				} catch (ParseException e) {
					logger.error(e.getMessage());
				}
			}
			
			for(int columnIndex = DATE_COLUMN_NUM+1; columnIndex < cellCount-1; columnIndex++){
				NgmReportData reportData = new NgmReportData();
				reportData.setReportDate(reportDate);
				reportData.setReleaseDate(getReleaseDate());
				
				SKU sku = skuMap.get(columnIndex);
				reportData.setSourceKey(sku.sourceKey);
				reportData.setUnit(sku.unitType);
				
				HSSFCell hssfCell = dataRow.getCell(columnIndex);
				
				if(null == hssfCell){
					dataList.add(reportData);
					continue;
				}
				
				String valueStr = getCellValue(dataRow.getCell(columnIndex));
				if(StringUtil.hasValidContent(valueStr))
					reportData.setValue(valueStr);
				dataList.add(reportData);
			}
			
		}
		
		return dataList;
	}
	
	
}
