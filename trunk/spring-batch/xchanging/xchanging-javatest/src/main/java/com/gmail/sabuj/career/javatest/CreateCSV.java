package com.gmail.sabuj.career.javatest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.gmail.sabuj.career.common.AppConstants;
import com.gmail.sabuj.career.common.AppSettings;
import com.gmail.sabuj.career.common.model.TaskDetail;
import com.gmail.sabuj.career.common.util.CollectionUtil;
import com.gmail.sabuj.career.common.util.IOUtil;

/**
 * The Main entry point of the Javatest application.
 * 
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 *
 */
public class CreateCSV {

	private static final Logger logger = Logger.getLogger(CreateCSV.class);

	public static void main(String[] args) {
		try {
			DOMConfigurator.configure(AppSettings.appLoggerLocation);
			AppSettings.getInstance().configureSettings();
		} catch (IOException e) {
			logger.error("Cannot load initial settings. Exiting... ", e);
			System.exit(e.hashCode());
		}

		List<TaskDetail> taskDetails = AppSettings.getInstance()
				.getTaskDetails();
		if (!CollectionUtil.hasElements(taskDetails)) {
			logger.error("No Task to execute. Exiting... ");
			System.exit(0);
		}

		String tempDirName = AppSettings.getInstance().getProperty(
				AppConstants.PROP_NAME_TEMP_DIR);
		String tempFileName = tempDirName
				+ File.separator
				+ AppSettings.getInstance().getProperty(
						AppConstants.PROP_NAME_TARGET_XLS_FILENAME);
		String sourceUrl = AppSettings.getInstance().getProperty(
				AppConstants.PROP_NAME_SOURCE_XLS_URL);

		try {
			logger.info("START:: Download file from: " + sourceUrl);
			IOUtil.download(sourceUrl, tempFileName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Download Failed !!!\t" + e.getMessage(), e);
			System.exit(0);
		}

		String sourceFileName = tempFileName;
		String generatedDirName = AppSettings.getInstance().getProperty(
				AppConstants.PROP_NAME_GENERATED_CSV_PATH);
		String generatedFileName = generatedDirName
				+ File.separator
				+ AppSettings.getInstance().getProperty(
						AppConstants.PROP_NAME_GENERATED_CSV_FILENAME);

		try {
			for (TaskDetail taskDetail : taskDetails) {
				if (null != taskDetail) {
					logger.info("Started Processing task: "
							+ taskDetail.getTaskName());
					try {
						AbstractParser parser = ParserFactory.getFactory()
								.createParser(taskDetail, sourceFileName,
										generatedFileName);
						if (null != parser) {
							boolean parsed = parser.parse();
							if (parsed)
								logger.info("Parsed successfully");
							parser.destroy();
						}
						parser = null;
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
					logger.info("Completed Processing task: "
							+ taskDetail.getTaskName());
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
