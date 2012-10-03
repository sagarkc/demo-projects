package net.sf.tools4csv.config;

import net.sf.tools4csv.config.CsvConfiguration;
import net.sf.tools4csv.config.model.Configuration;

public class CsvConfigurationTest {

	public static void main(String[] args) {
		try {
			Configuration configuration = CsvConfiguration.configure("D:\\CVS_ROOT\\TEMP\\in\\test-csv.xml").getConfiguration();
			System.out.println("Loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
