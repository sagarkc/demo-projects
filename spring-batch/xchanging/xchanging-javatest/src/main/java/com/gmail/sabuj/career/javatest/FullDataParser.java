package com.gmail.sabuj.career.javatest;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class FullDataParser extends AbstractParser {

	public FullDataParser(String sourceFileName, String targetFileName) throws IOException {
		super(sourceFileName, targetFileName);
	}

	@Override
	public boolean parse() {
		throw new UnsupportedOperationException("Un Implemented functionalities");
	}

	
	public boolean parse(final HSSFSheet currentSheet) {
		throw new UnsupportedOperationException("Un Implemented functionalities");
	}

}
