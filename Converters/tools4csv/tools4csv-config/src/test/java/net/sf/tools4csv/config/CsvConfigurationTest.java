package net.sf.tools4csv.config;

import net.sf.tools4csv.config.CsvConfiguration;
import net.sf.tools4csv.config.model.Configuration;

public class CsvConfigurationTest {

	public static void main(String[] args) {
		try {
			Configuration configuration = CsvConfiguration.configure("D:\\SVN_HOME\\demo-projects\\Converters\\csv4j\\csv4j-config\\src\\test\\resources\\test-csv.xml").getConfiguration();
			System.out.println("Loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
