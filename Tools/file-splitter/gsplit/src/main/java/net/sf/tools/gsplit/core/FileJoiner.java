/**
 * 
 */
package net.sf.tools.gsplit.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class FileJoiner {

	private List<File> sourceFiles;
	private File sourceDir;
	private File targetFile;
	
	/**
	 * 
	 */
	public FileJoiner() {
		sourceFiles = new ArrayList<File>(0);
	}

	/**
	 * @return the sourceFiles
	 */
	public List<File> getSourceFiles() {
		return sourceFiles;
	}

	/**
	 * @param sourceFiles the sourceFiles to set
	 */
	public void setSourceFiles(List<File> sourceFiles) {
		this.sourceFiles = sourceFiles;
	}

	/**
	 * @return the targetFile
	 */
	public File getTargetFile() {
		return targetFile;
	}

	/**
	 * @param targetFile the targetFile to set
	 */
	public void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}
	
	public void join(){
		
	}
}
