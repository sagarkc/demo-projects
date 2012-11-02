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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.util.MD5Util;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class SecureFileSplitter extends BinaryFileSplitter{

	private FileMetaData fileMetaData;
	
	public SecureFileSplitter(String fileName, String targeDirName){
		this(new File(fileName), new File(targeDirName));
	}
	
	public SecureFileSplitter(File sourceFile, File targetDir){
		super(sourceFile, targetDir);
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

	public void splitBySize() throws IOException{
		SplitterPartInfo splitterPartIfo = calculatePartIfoByMaxByte();
		if(null != splitterPartIfo){
			createParts(splitterPartIfo);
			writeFileMetaData();
		}
	}

	public void splitByPart() throws IOException{
		SplitterPartInfo splitterPartIfo = calculatePartIfoByMaxPart();
		if(null != splitterPartIfo){
			createParts(splitterPartIfo);
			writeFileMetaData();
		}
	}

	/**
	 * @param splitterPartInfo
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void createParts(SplitterPartInfo splitterPartInfo)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		BufferedInputStream inputStream = null;
		
		boolean readComplete = false;
		try{
			inputStream = new BufferedInputStream(new FileInputStream(getSourceFile()));
			int currentPart = 0;
			while((currentPart < splitterPartInfo.partCount) && !readComplete){
				String targetFileName = getSourceFileName() 
						+ SplitterConstants.PART_EXT 
						+ currentPart;
				
				PartMetaData partMetaData = new PartMetaData(targetFileName);
				partMetaData.setTotalPartCount(getFileMetaData().getTotalPartCount());
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
							new FileOutputStream(new File(getTargetDir(), targetFileName)));
					
					outputStream.write(partMetaData.getWritableBytes(), 0, 40);
					
					
					int count = -1;
					long partSize = 0;
					byte[] buffer = new byte[splitterPartInfo.bufferSize];
					while((count = inputStream.read(buffer, 0, splitterPartInfo.bufferSize)) >= 0){
						outputStream.write(buffer, 0, count);
						partSize += count;
						if(partSize >= splitterPartInfo.maxBytes)
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
				
				
				getFileMetaData().getPartMetaDataList().add(partMetaData);
				
				currentPart ++;
				if(isWorkerTask()){
					firePropertyChange(PROPERTY_PROGRESS, null, 
							Math.min((int)((currentPart*1.0/splitterPartInfo.partCount*1.0)*100.0), 100)
							);
					
				}
			}
			
		} finally {
			if(null != inputStream)
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			
		}
	}
	
	

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	private void writeFileMetaData() throws FileNotFoundException, IOException {
		String targetFileName = getSourceFileName() 
				+ SplitterConstants.METADATA_EXT;
		Collections.sort(getFileMetaData().getPartMetaDataList());
		ObjectOutputStream outputStream = null;
		try{
			outputStream = new ObjectOutputStream(
					new FileOutputStream(new File(getTargetDir(), targetFileName)));
			outputStream.writeObject(getFileMetaData());
			
		} finally {
			if(null != outputStream)
				try {
					outputStream.close();
				} catch (IOException e) {
				}
		}
	}

	@Override
	protected Void doInBackground() throws Exception {
		setWorkerTask(true);
		Long startTime = System.currentTimeMillis();
		Long totalTime = 0L;
		firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
		try {
			if(isSplitByPartCount()){
				splitByPart();
			} else {
				splitBySize();
			}
		} catch (RuntimeException e) {
			firePropertyChange(TASK_STATUS_FAILED, null, e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			firePropertyChange(TASK_STATUS_FAILED, null, e.getMessage());
			e.printStackTrace();
		}
		Long endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		firePropertyChange(TASK_STATUS_DONE, null, totalTime);
		return null;
	}
}
