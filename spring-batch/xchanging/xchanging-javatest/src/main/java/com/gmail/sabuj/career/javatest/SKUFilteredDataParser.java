package com.gmail.sabuj.career.javatest;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.gmail.sabuj.career.common.model.TaskDetail;

public class SKUFilteredDataParser extends FilteredDataParser {

	public SKUFilteredDataParser(String sourceFileName, String targetFileName,
			TaskDetail taskDetail) throws IOException {
		super(sourceFileName, targetFileName, taskDetail);
	}

	@Override
	public boolean parse(final HSSFSheet currentSheet) {
		throw new UnsupportedOperationException("Un Implemented functionalities");
	}

}
