/**
 * 
 */
package net.sf.tools.gsplit;

import java.io.File;
import java.io.IOException;

import net.sf.tools.gsplit.core.FileAutoJoiner;
import net.sf.tools.gsplit.core.FileSplitter;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class GreenSplitter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSplitter fileSplitter = new FileSplitter(
				"D:\\temp\\eclipse.zip", 
				"d:\\temp\\eclipseparts");
		FileAutoJoiner fileJoiner = new FileAutoJoiner(
				new File("d:\\temp\\eclipseparts\\eclipse.zip.mdat"),
				new File("d:\\temp\\eclipse-join\\eclipse.zip")
				);
		try {
			//fileSplitter.splitBySize(50 * SplitterConstants.MB);
			fileJoiner.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
