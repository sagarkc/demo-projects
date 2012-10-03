package net.sf.tools4csv.core;

import java.util.List;

import net.sf.tools4csv.config.CsvConfiguration;
import net.sf.tools4csv.config.model.Configuration;
import net.sf.tools4csv.core.converter.CsvConverter;
import net.sf.tools4csv.core.converter.CsvConverterFactory;
import net.sf.tools4csv.core.parser.CsvConversionProcessor;


public class CsvConversionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Configuration configuration = CsvConfiguration.configure("D:/CVS_ROOT/TEMP/in/test-csv.xml")
				.getConfiguration();
			
			CsvConversionProcessor conversionProcessor = CsvConverterFactory.buildConversionProcessor(configuration);
			
			if(null != conversionProcessor) {
				conversionProcessor.start();
				
				String converterId = "employee-converter";
				CsvConverter converter = CsvConverterFactory.getConverter(conversionProcessor, converterId);
				if(null != converter){
					List<Object> dataList = (List<Object>) converter.getOutputData();
					if(null != dataList){
						System.out.println(dataList.size());
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
