package com.gmail.sabuj.career.javatest;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.gmail.sabuj.career.common.model.TaskDetail;

public final class ParserFactory implements Cloneable {

	private static final Logger logger = Logger.getLogger(ParserFactory.class);

	private static ParserFactory factory;

	private ParserFactory() {
		if(logger.isDebugEnabled()){
			logger.debug("Parser Factory is initializing....");
		}
	}

	public static ParserFactory getFactory() {
		synchronized (ParserFactory.class) {
			if (null == factory)
				factory = new ParserFactory();
		}
		return factory;
	}

	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(
				"Singleton Object should not be cloneable!!!");
	}

	public AbstractParser createParser(final TaskDetail taskDetail,
			final String sourceFile, final String targetFile)
			throws IOException {
		logger.info("Creating target parser....");
		if (null == taskDetail) {
			throw new IllegalArgumentException("Task Details not provided.");
		}

		if (!taskDetail.isHasWorkBookFilter()) {
			logger.info("Created Parser: FullDataParser");
			return new FullDataParser(sourceFile, targetFile);
		} else {
			if(taskDetail.isHasSourceKeyFilter() && !taskDetail.isHasUnitTypeFilter()){
				logger.info("Created Parser: SourceKeyFilteredDataParser");
				return new SourceKeyFilteredDataParser(sourceFile, targetFile, taskDetail);
			}
			else if(!taskDetail.isHasSourceKeyFilter() && taskDetail.isHasUnitTypeFilter()){
				logger.info("Created Parser: UnitFilteredDataParser");
				return new UnitFilteredDataParser(sourceFile, targetFile, taskDetail);
			}
			else if(taskDetail.isHasSourceKeyFilter() && taskDetail.isHasUnitTypeFilter()){
				logger.info("Created Parser: SKUFilteredDataParser");
				return new SKUFilteredDataParser(sourceFile, targetFile, taskDetail);
			} 
			else {
				logger.info("Created Parser: WorkSheetDataParser");
				return new WorkSheetDataParser(sourceFile, targetFile, taskDetail);
			}
		}
		
	}

}
