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
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.swing.SwingWorker;


import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.WorkerTaskConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class SecureFileJoiner extends SwingWorker<Void, Void> implements WorkerTaskConstants{

	private FileMetaData fileMetaData;
	private final File sourceFile;
	private final File targetFile;
	private File sourceDir;
	private File targetDir;
	private boolean isWorkerTask;
	
	public SecureFileJoiner(File sourceFile, File targetFile) {
		if(!sourceFile.exists()){
			throw new RuntimeException(new FileNotFoundException("The source file does not exists !!!"));
		}
		targetDir = targetFile.getParentFile();
		sourceDir = sourceFile.getParentFile();
		this.sourceFile = sourceFile;
		this.targetFile = targetFile;
		
		try {
			readFileMetaData();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public SecureFileJoiner(String source, String target) {
		this(new File(source), new File(target));
	}

	/**
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	private void readFileMetaData() throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = null;
		try{
			inputStream = new ObjectInputStream(new FileInputStream(sourceFile));
			
			Object object = inputStream.readObject();
			if(null != object && object instanceof FileMetaData){
				fileMetaData = (FileMetaData) object;
			}
		} finally {
			if(null != inputStream){
				inputStream.close();
			}
		}
	}
	
	public void join() throws IOException{
		if(!targetDir.exists()){
			targetDir.mkdirs();
		}
		
		BufferedOutputStream outputStream = null;
		try{
			outputStream = new BufferedOutputStream(
					new FileOutputStream(targetFile));
			
			int count = -1;
			int writeCount = 0;
			byte[] buffer = new byte[SplitterConstants.KB];
			
			for(PartMetaData partMetaData : fileMetaData.getPartMetaDataList()){
				
				BufferedInputStream inputStream = null;
				try{
					inputStream = new BufferedInputStream(
							new FileInputStream(
									new File(sourceDir, partMetaData.getPartName())));
					byte[] header = new byte[PartMetaData.HEADER_LENGTH];
					count = inputStream.read(header, 0, PartMetaData.HEADER_LENGTH);
					if(count < 0 && header.length != PartMetaData.HEADER_LENGTH){
						throw new RuntimeException("Invalid part... Header not found.");
					}
					byte[] totalParts = Arrays.copyOfRange(header, 0, 4);
					ByteBuffer totalPartsBuffer = ByteBuffer.allocate(4);
					totalPartsBuffer.putInt(partMetaData.getTotalPartCount());
					byte[] currentPart = Arrays.copyOfRange(header, 4, 8);
					ByteBuffer currentPartBuffer = ByteBuffer.allocate(4);
					currentPartBuffer.putInt(partMetaData.getCurrentPartNumber());
					byte[] chkSum = Arrays.copyOfRange(header, 8, 40);
					
					if(!Arrays.equals(totalPartsBuffer.array(), totalParts)){
						throw new RuntimeException("Invalid part... Total part count not matched.");
					}
					
					if(!Arrays.equals(currentPartBuffer.array(), currentPart)){
						throw new RuntimeException("Invalid part... Current part number not matched.");
					}
					
					if(!Arrays.equals(chkSum, partMetaData.getCheckSum())){
						throw new RuntimeException("Invalid part... Header CheckSum not matched.");
					}
					
					while((count = inputStream.read(buffer, 0, SplitterConstants.KB)) > 0){
						outputStream.write(buffer, 0, count);
						writeCount += count;
						if(isWorkerTask){
							firePropertyChange(PROPERTY_PROGRESS, null, 
									Math.min((int)((writeCount*1.0/fileMetaData.getOriginalFileSize()*1.0)*100.0), 100)
									);
						}
					}
				} finally {
					if(null != inputStream){
						try {
							inputStream.close();
						} catch (IOException e) { }
					}
				}
			}
			
		} finally {
			if(null != outputStream){
				try {
					outputStream.close();
				} catch (IOException e) { }
			}
		}
	}

	
	
	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		isWorkerTask = true;
		Long startTime = System.currentTimeMillis();
		Long totalTime = 0L;
		firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
		try {
			join();
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
