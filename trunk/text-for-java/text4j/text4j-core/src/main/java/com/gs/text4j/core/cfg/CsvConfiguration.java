package com.gs.text4j.core.cfg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import org.apache.xmlbeans.XmlException;

import com.gs.text4J.bod.csv.CsvConfigurationDocument;
import com.gs.text4j.core.parser.CsvParserFactory;

public class CsvConfiguration {

	private String configurationFile = "csv-configuration.cfg.xml";
	
	private Set<String> csvFileNames;
	private Set<String> mappingResources;
	private Set<String> annotatedClasses;
	
	public CsvConfiguration() {
		
	}
	
	public CsvConfiguration configure() {
		return this;
	}

	public CsvConfiguration configure(String fileName) {
		return this;
	}
	
	private void loadConfigurationFile(String fileName) throws IOException, XmlException{
		InputStream inputStream = null;
		
		inputStream = getClass().getResourceAsStream(fileName);
		
		if(null == inputStream){
			//TODO: throw ex
		}
		
		/*BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer xmlBuffer = new StringBuffer();
		int count = 0;
		char[] cbuff = new char[1024];
		while((count = reader.read(cbuff, 0, 1024)) > 0){
			xmlBuffer.append(cbuff, 0, count);
		}
		*/
		CsvConfigurationDocument document = CsvConfigurationDocument.Factory.parse(inputStream);
	}

	public CsvParserFactory buildParserFactory(){
		return null;
	}
}
