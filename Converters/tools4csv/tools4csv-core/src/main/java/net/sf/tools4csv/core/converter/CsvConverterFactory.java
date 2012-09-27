package net.sf.tools4csv.core.converter;

import java.util.ArrayList;
import java.util.List;

import net.sf.tools4csv.config.model.Configuration;
import net.sf.tools4csv.config.model.Converter;
import net.sf.tools4csv.config.model.ConverterTypeEnum;
import net.sf.tools4csv.core.converter.impl.CsvToCsvConverter;
import net.sf.tools4csv.core.converter.impl.CsvToDatabaseConverter;
import net.sf.tools4csv.core.converter.impl.CsvToPojoConverter;
import net.sf.tools4csv.core.parser.CsvConversionProcessor;
import net.sf.tools4csv.core.parser.impl.CsvParallelConversionProcessor;
import net.sf.tools4csv.core.parser.impl.CsvSerialConversionProcessor;

import org.apache.log4j.Logger;


public class CsvConverterFactory {

	private static Logger logger = Logger.getLogger(CsvConverterFactory.class);
	
	private CsvConverterFactory() {
	}
	
	private static List<CsvConverter> createConverters(Configuration configuration){
		List<CsvConverter> converters = new ArrayList<CsvConverter>();
		if(null != configuration 
				&& null != configuration.getConverterList() 
				&& configuration.getConverterList().size() > 0){
			
			for (Converter converter : configuration.getConverterList()) {
				if(!converter.isActive()){
					logger.info("Converter [ " + converter.getId() + " ] is marked active = " + converter.isActive());
					continue;
				}
				if(ConverterTypeEnum.CSV_TO_CSV.equals(converter.getConverterType())){
					CsvToCsvConverter csvToCsvConverter = new CsvToCsvConverter();
					csvToCsvConverter.setSource(converter.getSource());
					csvToCsvConverter.setTarget(converter.getTarget());
					csvToCsvConverter.setId(converter.getId());
					converters.add(csvToCsvConverter);
				} else if(ConverterTypeEnum.CSV_TO_DB.equals(converter.getConverterType())){
					CsvToDatabaseConverter csvToDbConverter = new CsvToDatabaseConverter();
					csvToDbConverter.setSource(converter.getSource());
					csvToDbConverter.setTarget(converter.getTarget());
					csvToDbConverter.setId(converter.getId());
					converters.add(csvToDbConverter);
				} else if(ConverterTypeEnum.CSV_TO_POJO.equals(converter.getConverterType())){
					CsvToPojoConverter csvToPojoConverter = new CsvToPojoConverter();
					csvToPojoConverter.setSource(converter.getSource());
					csvToPojoConverter.setTarget(converter.getTarget());
					csvToPojoConverter.setId(converter.getId());
					converters.add(csvToPojoConverter);
				}
			}
			
		}
		return converters;
	}
	
	public static CsvConversionProcessor buildConversionProcessor(Configuration configuration){
		if(null != configuration){
			List<CsvConverter> converters = createConverters(configuration);
			if(configuration.isParallelConverters()){
				return new  CsvParallelConversionProcessor(converters);
			}
			return new  CsvSerialConversionProcessor(converters);
		}
		return null;
	}
	
	public static CsvConverter getCsvConverter(String id){
		return null;
	}

	public static CsvConverter getConverter(CsvConversionProcessor conversionProcessor,
			String converterId) {
		for (CsvConverter csvConverter : conversionProcessor.getConverters()) {
			if(csvConverter.getId().equals(converterId)){
				return csvConverter;
			}
		}
		return null;
	}
}
