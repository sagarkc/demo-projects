/**
 * 
 */
package net.sf.tools.gsplit.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.util.MD5Util;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class FileSplitter {

	private FileMetaData fileMetaData;
	private final File sourceFile;
	private final File targetDir;
	
	public FileSplitter(String fileName, String targeDirName){
		this(new File(fileName), new File(targeDirName));
	}
	
	public FileSplitter(File sourceFile, File targetDir){
		if(!sourceFile.exists()){
			throw new RuntimeException(new FileNotFoundException("The source file does not exists !!!"));
		}
		if(targetDir.exists() && !targetDir.isDirectory()){
			throw new RuntimeException("The target path must be a directory.");
		}
		this.sourceFile = sourceFile;
		this.targetDir = targetDir;
		this.fileMetaData = new FileMetaData();
	}
	
	/**
	 * @return the fileMetaData
	 */
	public FileMetaData getFileMetaData() {
		return fileMetaData;
	}

	/**
	 * @param fileMetaData the fileMetaData to set
	 */
	public void setFileMetaData(FileMetaData fileMetaData) {
		this.fileMetaData = fileMetaData;
	}

	

	/**
	 * @return the sourceFile
	 */
	public File getSourceFile() {
		return sourceFile;
	}

	public String getSourceFileName() {
		return getSourceFile().getName();
	}
	
	/**
	 * @return the targetDir
	 */
	public File getTargetDir() {
		return targetDir;
	}

	public void splitBySize(long byteCount) throws IOException{
		if(byteCount < 1){
			throw new RuntimeException("The target byte count should be >= 1.");
		}
		long size = sourceFile.length();
		if(size <= 0){
			return;
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
		
		fileMetaData.setTotalPartCount(partCount);
		
		int bufferSize = SplitterConstants.KB;
		if(byteCount > SplitterConstants.KB && byteCount < SplitterConstants.MB){
			bufferSize = SplitterConstants.KB * 10;
		} else if(byteCount >= SplitterConstants.MB && byteCount < SplitterConstants.GB){
			bufferSize = SplitterConstants.MB;
		} else if(byteCount >= SplitterConstants.GB){
			bufferSize = SplitterConstants.MB * 10;
		}
		
		BufferedInputStream inputStream = null;
		
		boolean readComplete = false;
		try{
			inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
			int currentPart = 0;
			while((currentPart < partCount) && !readComplete){
				String targetFileName = getSourceFileName() 
						+ SplitterConstants.PART_EXT 
						+ currentPart;
				
				PartMetaData partMetaData = new PartMetaData(targetFileName);
				partMetaData.setTotalPartCount(fileMetaData.getTotalPartCount());
				partMetaData.setCurrentPartNumber(currentPart);
				try {
					partMetaData.setCheckSum(MD5Util.getSalt(targetFileName));
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
					partMetaData.setCheckSum(new byte[32]);
				}
				
				BufferedOutputStream outputStream = null;
				try{
					outputStream = new BufferedOutputStream(
							new FileOutputStream(new File(targetDir, targetFileName)));
					
					outputStream.write(partMetaData.getWritableBytes(), 0, 40);
					
					
					int count = -1;
					long partSize = 0;
					byte[] buffer = new byte[bufferSize];
					while((count = inputStream.read(buffer, 0, bufferSize)) >= 0){
						outputStream.write(buffer, 0, count);
						partSize += count;
						if(partSize == byteCount)
							break;
					}
					if(count < 0)
						readComplete = true;
				} finally {
					if(null != outputStream)
						try {
							outputStream.close();
						} catch (IOException e) {
						}
				}
				
				
				fileMetaData.getPartMetaDataList().add(partMetaData);
				
				currentPart ++;
			}
			
		} finally {
			if(null != inputStream)
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			
		}
		
		writeFileMetaData();
	}

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	private void writeFileMetaData() throws FileNotFoundException, IOException {
		String targetFileName = getSourceFileName() 
				+ SplitterConstants.METADATA_EXT;
		Collections.sort(fileMetaData.getPartMetaDataList());
		ObjectOutputStream outputStream = null;
		try{
			outputStream = new ObjectOutputStream(
					new FileOutputStream(new File(targetDir, targetFileName)));
			outputStream.writeObject(fileMetaData);
			
		} finally {
			if(null != outputStream)
				try {
					outputStream.close();
				} catch (IOException e) {
				}
		}
	}

}
