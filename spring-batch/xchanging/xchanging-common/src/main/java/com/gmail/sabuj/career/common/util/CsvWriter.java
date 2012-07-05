/**
 * 
 */
package com.gmail.sabuj.career.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The Class CsvWriter.
 *
 * @param <T> the generic type
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 */
public class CsvWriter<T> {

	/** The default encoding type. */
	public static final String DEFAULT_ENCODING_TYPE = "UTF-8";
	
	/** The default line break. */
	private static final String DEFAULT_LINE_BREAK = System.getProperty("line.separator");
	
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(CsvWriter.class);
	
	/** The value separator. */
	private char valueSeperator;
	
	/** The write column headers. */
	private boolean writeColumnHeaders;
	
	/** The line break. */
	private String lineBreak = DEFAULT_LINE_BREAK;
	
	/** The encoding type. */
	private String encodingType = DEFAULT_ENCODING_TYPE;
	
	/** The use quote. */
	private boolean useQuote = false;
	
	/**
	 * Instantiates a new csv writer.
	 */
	public CsvWriter() {
		valueSeperator = ',';
		writeColumnHeaders = true;
	}
	
	/**
	 * Checks if is use quote.
	 *
	 * @return true, if is use quote
	 */
	public boolean isUseQuote() {
		return useQuote;
	}

	/**
	 * Sets the use quote.
	 *
	 * @param useQuote the new use quote
	 */
	public void setUseQuote(boolean useQuote) {
		this.useQuote = useQuote;
	}

	/**
	 * Checks if is write column headers.
	 *
	 * @return true, if is write column headers
	 */
	public boolean isWriteColumnHeaders() {
		return writeColumnHeaders;
	}

	/**
	 * Sets the write column headers.
	 *
	 * @param writeColumnHeaders the new write column headers
	 */
	public void setWriteColumnHeaders(boolean writeColumnHeaders) {
		this.writeColumnHeaders = writeColumnHeaders;
	}

	/**
	 * Gets the value seperator.
	 *
	 * @return the value seperator
	 */
	public char getValueSeperator() {
		return valueSeperator;
	}

	/**
	 * Sets the value seperator.
	 *
	 * @param valueSeperator the new value seperator
	 */
	public void setValueSeperator(char valueSeperator) {
		this.valueSeperator = valueSeperator;
	}

	/**
	 * Gets the line break.
	 *
	 * @return the line break
	 */
	public String getLineBreak() {
		return lineBreak;
	}

	/**
	 * Sets the line break.
	 *
	 * @param lineBreak the new line break
	 */
	public void setLineBreak(String lineBreak) {
		this.lineBreak = lineBreak;
	}

	/**
	 * Gets the encoding type.
	 *
	 * @return the encoding type
	 */
	public String getEncodingType() {
		return encodingType;
	}

	/**
	 * Sets the encoding type.
	 *
	 * @param encodingType the new encoding type
	 */
	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

	/**
	 * Write the collection of object in the csv file.
	 *
	 * @param fileName the file name
	 * @param data the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(String fileName, List<T> data) throws IOException{
		if(logger.isDebugEnabled()){
			logger.debug("START: write() ");
		}
		if(!CollectionUtil.hasElements(data)){
			IOUtil.createEmptyFile(fileName, DEFAULT_ENCODING_TYPE);
			if(logger.isDebugEnabled()){
				logger.debug("DONE: write() : NO DATA TO WRITE !!! EMPTY FILE CREATED." );
			}
			return;
		}
		if(!StringUtil.hasValidContent(fileName)){
			if(logger.isDebugEnabled()){
				logger.debug("DONE: write() : NO FILE TO WRITE !!!" );
			}
			return;
		}
		
		logger.info("Writing [ " + data.size() + " ] items in the file: " + fileName );
		
		CsvWriterDataModel<T> csvWriterDataModel = new CsvWriterDataModel<T>(data);
		
		if(null != csvWriterDataModel){
			OutputStreamWriter outputStreamWriter = null;
			try {
				outputStreamWriter = new OutputStreamWriter(
						new FileOutputStream(fileName), getEncodingType());
				StringBuffer columnBuffer = new StringBuffer();
				List<String> columnHeaders = csvWriterDataModel.getColumnNameList();
				if(CollectionUtil.hasElements(columnHeaders) && isWriteColumnHeaders()){
					for (int i = 0; i < columnHeaders.size(); i++) {
						String header = columnHeaders.get(i);
						columnBuffer.append(header);
						if(i < columnHeaders.size()-1){
							columnBuffer.append(getValueSeperator());
						}
					}
					outputStreamWriter.append(columnBuffer.toString());
					outputStreamWriter.append(getLineBreak());
				}
				
				for (int i = 0; i < csvWriterDataModel.getRowCount(); i++) {
					StringBuffer valueBuffer = new StringBuffer();
					for (int j = 0; j < csvWriterDataModel.getColumnCount(); j++) {
						Object cellValue = csvWriterDataModel.getValueAt(i, j);
						if(null == cellValue){
							valueBuffer.append("");
						} else {
							if(cellValue instanceof List){
								List<String> values = (List<String>) cellValue;
								for (int k = 0; k < values.size(); k++) {
									valueBuffer.append(StringUtil.getCsvString(String.valueOf(values.get(k)), isUseQuote()));
									if(k <= values.size()-1){
										valueBuffer.append(getValueSeperator());
									}
								}
							}
							else {
								valueBuffer.append(StringUtil.getCsvString(String.valueOf(cellValue), isUseQuote()));
							}
						}
						if(j < csvWriterDataModel.getColumnCount()-1){
							valueBuffer.append(getValueSeperator());
						}
					}
					valueBuffer.append(getLineBreak());
					outputStreamWriter.append(valueBuffer.toString());
				}
				outputStreamWriter.flush();
				
			} catch (IOException e) {
				throw e;
			} finally{
				IOUtil.close(outputStreamWriter, false);
			}
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("DONE: write() : Write SUCCESSFUL !!!");
		}
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		List<Model> list = new ArrayList<Model>();
		for (int i = 0; i < 10; i++) {
			Model model = new Model();
			model.setId(i+1);
			model.setName("Model_" + (i+1));
			model.setValue("\"Val'u,\"e_\n" + (i+1));
			list.add(model);
		}
		CsvWriter<Model> csvWriter = new CsvWriter<Model>();
		csvWriter.setValueSeperator(',');
		csvWriter.setWriteColumnHeaders(true);
		try {
			csvWriter.write("d:\\list.csv", list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

class Model{
	private Integer id;
	private String name;
	private String value;
	
	public Model() {
		// TODO Auto-generated constructor stub
	}

	@CsvColumnHeader(index=0, title="MODEL_ID")
	public Integer getId() {
		return id;
	}

	@CsvColumnHeader(index=1, title="MODEL_NAME")
	public String getName() {
		return name;
	}

	@CsvColumnHeader(index=2, title="MODEL_VALUE")
	public String getValue() {
		return value;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}