package com.gmail.sabuj.career.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import org.apache.log4j.Logger;


/**
 * This util class is for all the IO related utilities.
 * 
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 *
 */
public class IOUtil {

	private static final Logger logger = Logger.getLogger(IOUtil.class);
	
	public static final int BUFFER_SIZE = 1024 * 1024;
	
	/**
	 * 	Colse the Writer/Reader/Streams silently.
	 * @param T extends Closeable
	 * @param t
	 */
	public static synchronized <T extends Closeable> void close(T t){
		close(t, true);
	}
	
	/**
	 * 	Colse the Writer/Reader/Streams and if there is any exception, then log that based on the value of silent.
	 * @param T extends Closeable
	 * @param t
	 * @param silent
	 */
	public static synchronized <T extends Closeable> void close(T t, boolean silent){
		if(null != t){
			try {
				t.close();
			} catch (IOException e) {
				if(!silent){
					logger.error(e);
				}
			}
		}
	}

	/**
	 * Write as text.
	 *
	 * @param fileName the file name
	 * @param text the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static synchronized void writeAsText(String fileName, String text) throws IOException {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName)));
			bufferedWriter.write(text);
		} finally {
			close(bufferedWriter);
		}
	}
	
	
	
	/**
	 * Creates the empty file.
	 *
	 * @param fileName the file name
	 * @param encoding the encoding
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static synchronized void createEmptyFile(String fileName, String encoding) throws IOException {
		OutputStreamWriter outputStreamWriter = null;
		try {
			outputStreamWriter = new OutputStreamWriter(
					new FileOutputStream(fileName), encoding);
			outputStreamWriter.flush();
		} finally{
			IOUtil.close(outputStreamWriter, false);
		}
	}
	
	/**
	 * Download file from the source URL and save in the local system to target.
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static synchronized void download(String source, String target) throws IOException{
		if(!StringUtil.hasValidContent(source) || !StringUtil.hasValidContent(target)){
			throw new IllegalArgumentException("Source and/or target not provided!!!");
		}
		File targetFile = new File(target);
		
		URL sourceUrl = new URL(source);
		download(sourceUrl, targetFile);
	}
	
	private static synchronized void download(URL sourceUrl, File targetFile) throws IOException{
		InputStream inputStream = sourceUrl.openStream();
		BufferedOutputStream outputStream = null;
		if(null != inputStream && inputStream.available() > 0){
			int count = 0;
			byte[] dataBuffer = new byte[BUFFER_SIZE];
			outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
			try{
				while((count = inputStream.read(dataBuffer, 0, BUFFER_SIZE)) > 0){
					outputStream.write(dataBuffer, 0, count);
				}
			} finally {
				close(inputStream);
				close(outputStream);
			}
		}
		
	}
}
