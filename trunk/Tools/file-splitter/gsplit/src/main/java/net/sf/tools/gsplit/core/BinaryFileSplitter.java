/**
 * 
 */
package net.sf.tools.gsplit.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.SwingWorker;

import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.WorkerTaskConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class BinaryFileSplitter extends SwingWorker<Void, Void> implements WorkerTaskConstants{

	private final File sourceFile;
	private final File targetDir;
	private boolean isWorkerTask;
	private long byteCount;
	private int maximumPartCount;
	private boolean splitByPartCount;
	
	public BinaryFileSplitter(String fileName, String targeDirName){
		this(new File(fileName), new File(targeDirName));
	}
	
	public BinaryFileSplitter(File sourceFile, File targetDir){
		if(!sourceFile.exists()){
			throw new RuntimeException(new FileNotFoundException("The source file does not exists !!!"));
		}
		if(targetDir.exists() && !targetDir.isDirectory()){
			throw new RuntimeException("The target path must be a directory.");
		}
		this.sourceFile = sourceFile;
		this.targetDir = targetDir;
		
	}

	/**
	 * @return the isWorkerTask
	 */
	public boolean isWorkerTask() {
		return isWorkerTask;
	}

	/**
	 * @param isWorkerTask the isWorkerTask to set
	 */
	public void setWorkerTask(boolean isWorkerTask) {
		this.isWorkerTask = isWorkerTask;
	}

	/**
	 * @return the byteCount
	 */
	public long getByteCount() {
		return byteCount;
	}

	/**
	 * @param byteCount the byteCount to set
	 */
	public void setByteCount(long byteCount) {
		this.byteCount = byteCount;
	}

	/**
	 * @return the maximumPartCount
	 */
	public int getMaximumPartCount() {
		return maximumPartCount;
	}

	/**
	 * @param maximumPartCount the maximumPartCount to set
	 */
	public void setMaximumPartCount(int maximumPartCount) {
		this.maximumPartCount = maximumPartCount;
	}

	/**
	 * @return the splitByPartCount
	 */
	public boolean isSplitByPartCount() {
		return splitByPartCount;
	}

	/**
	 * @param splitByPartCount the splitByPartCount to set
	 */
	public void setSplitByPartCount(boolean splitByPartCount) {
		this.splitByPartCount = splitByPartCount;
	}

	/**
	 * @return the sourceFile
	 */
	public File getSourceFile() {
		return sourceFile;
	}

	/**
	 * @return the targetDir
	 */
	public File getTargetDir() {
		return targetDir;
	}
	
	public String getSourceFileName() {
		return getSourceFile().getName();
	}
	
	public SplitterPartInfo calculatePartIfoByMaxByte(){
		SplitterPartInfo splitterPartIfo = new SplitterPartInfo();
		if(byteCount < 1){
			throw new RuntimeException("The target byte count should be >= 1.");
		}
		long size = sourceFile.length();
		splitterPartIfo.totalFileSize = size;
		if(size <= 0){
			return null;
		}
		
		if(!targetDir.exists()){
			targetDir.mkdirs();
		}
		
		int partCount = 0;
		if(size % byteCount == 0){
			partCount = Math.round(size/byteCount);
		} else {
			partCount = Math.round(size/byteCount) + 1;
		}
		
		splitterPartIfo.partCount = partCount;
		
		int bufferSize = SplitterConstants.KB;
		if(byteCount > SplitterConstants.KB && byteCount < SplitterConstants.MB){
			bufferSize = SplitterConstants.KB * 10;
		} else if(byteCount >= SplitterConstants.MB && byteCount < SplitterConstants.GB){
			bufferSize = SplitterConstants.MB;
		} else if(byteCount >= SplitterConstants.GB){
			bufferSize = SplitterConstants.MB * 10;
		}
		splitterPartIfo.bufferSize = bufferSize;
		return splitterPartIfo;
	}
	
	public SplitterPartInfo calculatePartIfoByMaxPart(){
		SplitterPartInfo splitterPartIfo = new SplitterPartInfo();
		if(maximumPartCount < 1){
			throw new RuntimeException("The maximum part count should be > 1.");
		}
		long size = sourceFile.length();
		splitterPartIfo.totalFileSize = size;
		if(size <= 0){
			return null;
		}
		if(!targetDir.exists()){
			targetDir.mkdirs();
		}
		long maxBytes = 0;
		if(size % maximumPartCount == 0){
			maxBytes = Math.round(size/maximumPartCount);
		} else {
			maxBytes = Math.round(size/maximumPartCount) + 1;
		}
		splitterPartIfo.partCount = maximumPartCount;
		
		int bufferSize = SplitterConstants.KB;
		if(maxBytes > SplitterConstants.KB && byteCount < SplitterConstants.MB){
			bufferSize = SplitterConstants.KB * 10;
		} else if(maxBytes >= SplitterConstants.MB && byteCount < SplitterConstants.GB){
			bufferSize = SplitterConstants.MB;
		} else if(maxBytes >= SplitterConstants.GB){
			bufferSize = SplitterConstants.MB * 10;
		}
		splitterPartIfo.bufferSize = bufferSize;
		return splitterPartIfo;
	}
	
	public abstract void splitByPart() throws IOException;
	
	public abstract void splitBySize() throws IOException;
	
	public abstract void createParts(SplitterPartInfo splitterPartIfo)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException ;


	class SplitterPartInfo{
		
		long totalFileSize = 0;
		long maxBytes = 0;
		int partCount = 0;
		int bufferSize = 0;
		
	}
}
