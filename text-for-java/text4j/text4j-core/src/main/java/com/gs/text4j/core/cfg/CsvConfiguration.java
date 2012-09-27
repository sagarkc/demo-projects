package com.gs.text4j.core.cfg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.gs.text4J.bod.csv.CsvConfigurationDocument;
import com.gs.text4J.bod.csv.MappingDocument.Mapping;
import com.gs.text4j.core.parser.CsvParserFactory;
import com.gs.utils.text.StringUtil;

public class CsvConfiguration {

	private static final Logger logger = Logger.getLogger(CsvConfiguration.class);
	
	private String configurationFile = "/csv-configuration.cfg.xml";
	
	private String mappingLocation;
	private String mappingPackage;
	
	private Set<String> csvFileNames;
	private Set<String> mappingResources;
	private Set<String> annotatedClasses;
	
	public CsvConfiguration() throws IOException, XmlException {
		csvFileNames = new HashSet<String>();
		mappingResources = new HashSet<String>();
		annotatedClasses = new HashSet<String>();
		configure();
	}
	
	public CsvConfiguration configure() throws IOException, XmlException {
		return configure(configurationFile);
	}

	public CsvConfiguration configure(String fileName) throws IOException, XmlException {
		loadConfigurationFile(fileName);
		return this;
	}
	
	private void loadConfigurationFile(String fileName) throws IOException, XmlException{
		InputStream inputStream = getClass().getResourceAsStream(fileName);
		if(null == inputStream){
			//TODO: throw ex
		}
		CsvConfigurationDocument document = CsvConfigurationDocument.Factory.parse(inputStream);
		if(null != document){
			com.gs.text4J.bod.csv.CsvConfigurationDocument.CsvConfiguration configuration 
				= document.getCsvConfiguration();
			if(null != configuration){
				mappingLocation = configuration.getMappingLocation();
				if(StringUtil.hasValidContent(mappingLocation)){
					File location = new File(mappingLocation);
					if(null != location && location.exists() && location.isDirectory()){
						File[] files = location.listFiles();
						for (File file : files) {
							if(file.getName().endsWith(".csv.xml")){
								mappingResources.add(file.getAbsolutePath());
							}
						}
					}
				}
				mappingPackage = configuration.getMappingPackage().getStringValue();
				//TODO:: Need to Implement package scanner
				
				Mapping mapping = configuration.getMapping();
				if(null != mapping){
					String[] mappingResources = mapping.getMappingResourceArray();
					if(null != mappingResources && mappingResources.length > 0){
						for (String ress : mappingResources) {
							this.mappingResources.add(ress);
						}
					}
					String[] annotatedClazzs = mapping.getAnnotatedClassArray();
					if(null != annotatedClazzs && annotatedClazzs.length > 0){
						for (String clazz : annotatedClazzs) {
							annotatedClasses.add(clazz);
						}
					}
				}
			}
		}
	}

	public CsvParserFactory buildParserFactory(){
		return null;
	}

	public String getConfigurationFile() {
		return configurationFile;
	}

	public String getMappingLocation() {
		return mappingLocation;
	}

	public String getMappingPackage() {
		return mappingPackage;
	}

	public Set<String> getCsvFileNames() {
		return csvFileNames;
	}

	public Set<String> getMappingResources() {
		return mappingResources;
	}

	public Set<String> getAnnotatedClasses() {
		return annotatedClasses;
	}
	
	
}
