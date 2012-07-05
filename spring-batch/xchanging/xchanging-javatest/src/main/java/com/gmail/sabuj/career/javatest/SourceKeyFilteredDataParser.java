package com.gmail.sabuj.career.javatest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.gmail.sabuj.career.common.model.TaskDetail;
import com.gmail.sabuj.career.common.util.CollectionUtil;
import com.gmail.sabuj.career.common.util.CsvWriter;
import com.gmail.sabuj.career.common.util.StringUtil;
import com.gmail.sabuj.career.javatest.model.NgmReportData;

public class SourceKeyFilteredDataParser extends FilteredDataParser {

	private static final Logger logger = Logger.getLogger(SourceKeyFilteredDataParser.class);
	
	public SourceKeyFilteredDataParser(String sourceFileName,
			String targetFileName, TaskDetail taskDetail) throws IOException {
		super(sourceFileName, targetFileName, taskDetail);
	}

	@Override
	public boolean parse(final HSSFSheet currentSheet) {
		logger.info("Parse for source key: " + getTaskDetail().getSourceKey());
		int columnIndex = getSourceKeyColumnIndex(currentSheet);
		if(columnIndex == -1){
			logger.info("Source key not found.");
			return false;
		}
		if(logger.isDebugEnabled()){
			logger.debug("Source key column index = " + columnIndex);
		}
		
		List<NgmReportData> ngmReportDataList = populateDataForSourceKey(currentSheet, columnIndex);
		
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
	
	
	private int getSourceKeyColumnIndex(final HSSFSheet currentSheet){
		HSSFRow srckeyRow = currentSheet.getRow(SRC_KEY_ROW_NUM);
		String srcKey = null;
		if(null != srckeyRow){
			int colCount = srckeyRow.getPhysicalNumberOfCells();
			for(int i=SRC_KEY_COL_NUM_FROM; i < colCount; i++){
				HSSFCell srckeyCell = srckeyRow.getCell(i);
				if(null != srckeyCell){
					srcKey = getCellValue(srckeyCell);
					if(StringUtil.hasValidContent(srcKey) && srcKey.equals(getTaskDetail().getSourceKey())){
						return i;
					}
				}
			}
		}
		
		return -1;
	}

}
