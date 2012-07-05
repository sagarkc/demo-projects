package com.gmail.sabuj.career.javatest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gmail.sabuj.career.common.AppConstants;
import com.gmail.sabuj.career.common.AppSettings;
import com.gmail.sabuj.career.common.util.CollectionUtil;
import com.gmail.sabuj.career.common.util.IOUtil;
import com.gmail.sabuj.career.common.util.StringUtil;
import com.gmail.sabuj.career.javatest.model.NgmData;
import com.gmail.sabuj.career.javatest.model.NgmSource;

public abstract class AbstractParser {

	protected static AppSettings appSettings = AppSettings.getInstance();

	private static final Logger logger = Logger.getLogger(AbstractParser.class);

	protected static final int SRC_KEY_ROW_NUM = 1;
	protected static final int SRC_KEY_COL_NUM_FROM = 1;
	protected static final int UNIT_ROW_NUM = 2;
	protected static final int UNIT_COL_NUM_FROM = 1;
	protected static final int DATA_ROW_NUM_FROM = 3;
	protected static final String UNIT_NAME_PATTERN = "(.*)\\((.*)\\)(.*)";
	protected static final int UNIT_GROUP_NUM = 2;
	protected static final int DATE_COLUMN_NUM = 0;

	private final String sourceFileName;
	private final String targetFileName;

	private HSSFWorkbook workbook;
	private BufferedInputStream inputStream = null;
	private Date releaseDate;
	private Pattern unitNamePattern;
	private Map<String, NgmData> ngmDataMap = new HashMap<String, NgmData>();

	private FilterParams releaseDateFilterParams = new FilterParams();

	public AbstractParser(String sourceFileName, String targetFileName)
			throws IOException {
		this.sourceFileName = sourceFileName;
		this.targetFileName = targetFileName;
		init();
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public Pattern getUnitNamePattern() {
		return unitNamePattern;
	}

	public Map<String, NgmData> getNgmDataMap() {
		return ngmDataMap;
	}

	private void init() throws IOException {
		File srcFile = new File(getSourceFileName());
		if (!srcFile.exists()) {
			throw new FileNotFoundException(getSourceFileName());
		}
		inputStream = new BufferedInputStream(new FileInputStream(srcFile));
		workbook = new HSSFWorkbook(inputStream);
		String params = appSettings
				.getProperty(AppConstants.PROP_NAME_RELEASE_DATE_SOURCE);
		if (StringUtil.hasValidContent(params)) {
			String[] values = params.split(",");
			if (CollectionUtil.hasElements(values)) {
				for (String val : values) {
					String[] filter = val.split(":");
					if (filter.length == 2) {
						if (AppConstants.FILTER_PROP_WORKBOOK_NAME
								.equals(filter[0])) {
							releaseDateFilterParams.workSheetName = filter[1];
						} else if (AppConstants.FILTER_PROP_ROW_NUM
								.equals(filter[0])) {
							int row = -1;
							try {
								row = Integer.parseInt(filter[1]);
							} catch (Exception e) {

							}
							releaseDateFilterParams.rowNumber = row;
						} else if (AppConstants.FILTER_PROP_COL_NUM
								.equals(filter[0])) {
							int col = -1;
							try {
								col = Integer.parseInt(filter[1]);
							} catch (Exception e) {

							}
							releaseDateFilterParams.columnName = col;
						}
					}
				}
			}
			releaseDate = readReleaseDate();
		} else {
			releaseDateFilterParams = null;
		}
		unitNamePattern = Pattern.compile(UNIT_NAME_PATTERN);
	}

	private Date readReleaseDate() {
		if (null == releaseDateFilterParams) {
			return null;
		}

		HSSFSheet sheet = workbook
				.getSheet(releaseDateFilterParams.workSheetName);
		if (null != sheet) {
			HSSFRow row = sheet.getRow(releaseDateFilterParams.rowNumber);
			if (null != row) {
				String releaseDate = getCellValue(row
						.getCell(releaseDateFilterParams.columnName));
				if (!StringUtil.hasValidContent(releaseDate)) {
					return null;
				}
				try {
					return appSettings.getDateFormat().parse(releaseDate);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}

		return null;
	}

	protected String getCellValue(HSSFCell hssfCell) {
		String cellValue = "";
		if (null == hssfCell) {
			return cellValue;
		}
		
		switch(hssfCell.getCellType()){
			case HSSFCell.CELL_TYPE_BLANK:
				return "";
				
			case HSSFCell.CELL_TYPE_STRING:
				return hssfCell.getStringCellValue();
			case HSSFCell.CELL_TYPE_NUMERIC:
				Double d = hssfCell.getNumericCellValue();
				if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
					Date date = HSSFDateUtil.getJavaDate(d);
					return appSettings.getDateFormat().format(date);
				}
				return d.toString();
			
		}
		return cellValue;
	}
	

	protected String readUnitName(final HSSFSheet currentSheet, int columnIndex) {
		HSSFRow unitRow = currentSheet.getRow(UNIT_ROW_NUM);
		String unit = null;
		if (null != unitRow) {
			HSSFCell unitCell = unitRow.getCell(columnIndex);
			if (null != unitCell) {
				String unitTxt = getCellValue(unitCell);
				if (StringUtil.hasValidContent(unitTxt)) {
					Matcher unitMatcher = unitNamePattern.matcher(unitTxt);
					if (unitMatcher.matches() && unitMatcher.groupCount() >= 3) {
						unit = unitMatcher.group(UNIT_GROUP_NUM);
					}
				}
			}

		}
		return unit;
	}

	protected void destroy() {
		workbook = null;
		IOUtil.close(inputStream);
	}

	protected void finalize() throws Throwable {
		super.finalize();
	}

	public abstract boolean parse();

}
