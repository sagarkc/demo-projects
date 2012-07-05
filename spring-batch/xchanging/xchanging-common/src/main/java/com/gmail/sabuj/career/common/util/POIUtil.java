package com.gmail.sabuj.career.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * POI common utility methods
 * 
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 *
 */
public class POIUtil {

	private static final Logger logger = Logger.getLogger(POIUtil.class);

	/**
	 * Convert CSV to Excel (single column)
	 * @param inputCSVFileName
	 * @param targetFileName
	 * @param bookName
	 * @return
	 */
	public static boolean convert(String inputCSVFileName, String targetFileName,
			String bookName) {
		File targetFile = new File(targetFileName);
		logger.info("Start convertion ");
		BufferedReader reader = null;
		FileOutputStream fileOut = null;
		try {
			reader = new BufferedReader(new FileReader(inputCSVFileName));

			HSSFWorkbook wb = new HSSFWorkbook(); // or new XSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(bookName);
			String line = null;
			int rowCount = 0;
			while ((line = reader.readLine()) != null) {

				HSSFRow row = sheet.createRow(rowCount);
				HSSFCell cell = row.createCell( 0);
				cell.setCellValue(line.trim());
				rowCount++;
			}
			// Write the output to a file
			fileOut = new FileOutputStream(targetFile);
			wb.write(fileOut);
			logger.info("Conversion Completed.");
		} catch (IOException ioex) {
			logger.error("Conversion Failed .\n\t" + ioex.getMessage(), ioex);
		} finally {
			IOUtil.close(reader);
			IOUtil.close(fileOut);
		}
		logger.info("End convertion ");
		return true;
	}

	/**
	 * Create excel (Single column) to CSV
	 * @param sourceFileName
	 * @param targetFileName
	 * @throws IOException
	 */
	public static void convert2Csv(String sourceFileName,
			String targetFileName) throws IOException {
		logger.info("Start convertion ");
		File sourceFile = new File(sourceFileName);
		if(!sourceFile.exists()){
			throw new FileNotFoundException(sourceFileName);
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(targetFileName)));
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		try{
			HSSFSheet sheet = workbook.getSheetAt(0);
			if(null != sheet){
				int lastRow = sheet.getLastRowNum()-1;
				while(lastRow >= 0){
					HSSFRow row = sheet.getRow(lastRow);
					if(null != row){
						HSSFCell cell = row.getCell(0);
						HSSFRichTextString rStr = cell.getRichStringCellValue();
						if(null != rStr){
							bw.write(rStr.toString()+System.getProperty("line.separator"));
						}
					}
					lastRow --;
				}
			}
		} finally{
			IOUtil.close(bw);
			IOUtil.close(inputStream);
		}
		
		logger.info("End convertion ");
	}

}
