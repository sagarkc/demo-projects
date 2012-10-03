package net.sf.tools4csv.core.converter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.tools4csv.config.CsvConfiguration;
import net.sf.tools4csv.config.model.Configuration;
import net.sf.tools4csv.config.model.Converter;
import net.sf.tools4csv.config.model.ConverterTypeEnum;
import net.sf.tools4csv.core.IllegalConfigurationException;
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
	
	/**
	 * <pre>An example (in an XML based bean factory definition) of a bean definition which uses this class to call a static factory method:

 &lt;bean id="myObject" class="org.springframework.beans.factory.config.MethodInvokingFactoryBean"&gt;
   &lt;property name="staticMethod"&gt;&lt;value&gt;com.whatever.MyClassFactory.getInstance&lt;/value&gt;&lt;/property&gt;
 &lt;/bean&gt;
An example of calling a static method then an instance method to get at a Java system property. Somewhat verbose, but it works.

 &lt;bean id="sysProps" class="org.springframework.beans.factory.config.MethodInvokingFactoryBean"&gt;
   &lt;property name="targetClass"&gt;&lt;value&gt;java.lang.System&lt;/value&gt;&lt;/property&gt;
   &lt;property name="targetMethod"&gt;&lt;value&gt;getProperties&lt;/value&gt;&lt;/property&gt;
 &lt;/bean&gt;</pre>
	 * @param fileName
	 * @return
	 */
	public static CsvConversionProcessor buildConversionProcessor(String fileName){
		File file = new File(fileName);
		if(!file.exists()){
			throw new IllegalConfigurationException("File not exists : " + fileName);
		}
		
		Configuration configuration = null;
		try {
			configuration = CsvConfiguration.configure(fileName).getConfiguration();
		} catch (Exception e) {
			throw new IllegalConfigurationException(e);
		}
		
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
