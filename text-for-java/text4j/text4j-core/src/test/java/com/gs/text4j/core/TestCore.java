package com.gs.text4j.core;

import com.gs.text4j.core.cfg.CsvConfiguration;
import com.gs.text4j.core.parser.csv.CsvParser;

public class TestCore {
	
	public void test() {
		CsvParser parser = new CsvConfiguration().configure()
				.buildParserFactory().buildParser();
		
	}
}
