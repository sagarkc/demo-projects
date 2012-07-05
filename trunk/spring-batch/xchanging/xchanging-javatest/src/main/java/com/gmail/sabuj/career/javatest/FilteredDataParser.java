package com.gmail.sabuj.career.javatest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.gmail.sabuj.career.common.model.TaskDetail;
import com.gmail.sabuj.career.common.util.StringUtil;
import com.gmail.sabuj.career.javatest.model.NgmReportData;

public abstract class FilteredDataParser extends AbstractParser {

	private static final Logger logger = Logger.getLogger(FullDataParser.class);
	private final TaskDetail taskDetail;
	
	protected class SKU{
		protected String sourceKey;
		protected String unitType;
	}
	
	public FilteredDataParser(String sourceFileName, String targetFileName, TaskDetail taskDetail) throws IOException {
		super(sourceFileName, targetFileName);
		this.taskDetail = taskDetail; 
	}
	
	public TaskDetail getTaskDetail() {
		return taskDetail;
	}

	@Override
	public boolean parse() {
		if(null == taskDetail)
			return false;
		final HSSFSheet hssfSheet = getWorkbook().getSheet(taskDetail.getWorksheetName());
		if(null == hssfSheet)
			return false;
		logger.info("Parse worksheet: " + taskDetail.getWorksheetName());
		return parse(hssfSheet);
	}

	public abstract boolean parse(final HSSFSheet currentSheet) ;

	protected List<NgmReportData> populateDataForSourceKey(final HSSFSheet currentSheet, int srcKeyColIndex) {
		List<NgmReportData> ngmReportDataList = new ArrayList<NgmReportData>();
		
		String unitName = readUnitName(currentSheet, srcKeyColIndex);
		
		int lastRowIndex = (currentSheet.getPhysicalNumberOfRows() > 0) 
				? currentSheet.getPhysicalNumberOfRows()
				: -1;
		for(int i = DATA_ROW_NUM_FROM; i < lastRowIndex-1; i++){
			HSSFRow dataRow = currentSheet.getRow(i);
			if(null != dataRow){
				NgmReportData ngmReportData = new NgmReportData();
				ngmReportData.setReleaseDate(getReleaseDate());
				ngmReportData.setSourceKey(getTaskDetail().getSourceKey());
				ngmReportData.setUnit(unitName);
				
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
	
	protected Map<Integer, SKU> populateSKUColumnMap(final  HSSFSheet currentSheet){
		HSSFRow srckeyRow = currentSheet.getRow(SRC_KEY_ROW_NUM);
		HSSFRow unitRow = currentSheet.getRow(UNIT_ROW_NUM);
		Map<Integer, SKU> dataMap = new HashMap<Integer, SKU>();
		String unit = null;
		String srcKey = null;
		if(null != srckeyRow && null != unitRow){
			int colCount = srckeyRow.getPhysicalNumberOfCells();
			for(int i=SRC_KEY_COL_NUM_FROM; i < colCount-1; i++){
				SKU sku = new SKU();
				HSSFCell srckeyCell = srckeyRow.getCell(i);
				if(null != srckeyCell){
					srcKey = getCellValue(srckeyCell);
					if(StringUtil.hasValidContent(srcKey)){
						sku.sourceKey = srcKey;
					}
				}
				HSSFCell unitCell = unitRow.getCell(i);
				if (null != unitCell) {
					String unitTxt = getCellValue(unitCell);
					if (StringUtil.hasValidContent(unitTxt)) {
						Matcher unitMatcher = getUnitNamePattern().matcher(unitTxt);
						if (unitMatcher.matches() && unitMatcher.groupCount() >= 3) {
							unit = unitMatcher.group(UNIT_GROUP_NUM);
							if(StringUtil.hasValidContent(unit)){
								sku.unitType = unit;
							}
						}
					}
				}
				dataMap.put(i, sku);
			}
		}
		return dataMap;
	}
}
