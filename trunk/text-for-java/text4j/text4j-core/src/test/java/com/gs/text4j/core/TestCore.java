package com.gs.text4j.core;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import com.gs.text4j.core.cfg.CsvConfiguration;
import com.gs.text4j.core.parser.csv.CsvParser;

public class TestCore {
	
	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		try {
			CsvParser parser = new CsvConfiguration().configure()
					.buildParserFactory().buildParser();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		}
		
	}
}
