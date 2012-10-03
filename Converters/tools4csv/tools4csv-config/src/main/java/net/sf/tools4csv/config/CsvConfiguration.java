package net.sf.tools4csv.config;

import java.io.File;
import java.io.IOException;

import net.sf.tools4csv.config.model.Configuration;


public class CsvConfiguration {

	public static final String DEFAULT_CFG_FILE = "csv.cfg.xml";
	private Configuration configuration;
	
	public static CsvConfiguration configure() throws Exception{
		return configure(DEFAULT_CFG_FILE);
	}
	
	public static CsvConfiguration configure(String fileName) throws Exception{
		File cfgFile = new File(fileName);
		CsvConfiguration csvConfiguration = new CsvConfiguration();
		try {
			Configuration configuration = CsvConfigTransformer.loadConfiguration(cfgFile);
			if(null == configuration){
				throw new Exception("Cannot load configuration....");
			}
			csvConfiguration.configuration = configuration;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return csvConfiguration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}
	
	
}
