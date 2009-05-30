package com.conversion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.xalan.xslt.Process;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import com.conversion.mapping.AlmoCatalogData;
import com.conversion.util.AlmoConversionUtil;

public class App {

	
	public static void main(String[] args) {
		//AlmoConversionUtil.convertXml2Csv("D:/AlmoCatalog-2142008.xml", null, ',', "mappings/almo-mapping.xml");
		
		Process p = new Process();
		
		p.main(new String[]{
				"-IN", 
				"test/AlmoCatalog-2142008.xml",
				"-XSL",
				"test/TestXslt.xsl", 
				"-OUT",
				"test/test.csv"
		});
	}
	
}
