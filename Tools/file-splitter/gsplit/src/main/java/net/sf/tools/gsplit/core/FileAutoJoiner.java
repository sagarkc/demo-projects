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
import java.util.Arrays;

import javax.swing.SwingWorker;

import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.WorkerTaskConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class FileAutoJoiner extends SwingWorker<Void, Void> implements WorkerTaskConstants{

	private FileMetaData fileMetaData;
	private final File sourceFile;
	private final File targetFile;
	private File sourceDir;
	private File targetDir;
	
	public FileAutoJoiner(File sourceFile, File targetFile) {
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
					byte[] chkSum = Arrays.copyOfRange(header, 8, 40);
					
					if(!Arrays.equals(chkSum, partMetaData.getCheckSum())){
						throw new RuntimeException("Invalid part... Header CheckSum not matched.");
					}
					
					while((count = inputStream.read(buffer, 0, SplitterConstants.KB)) > 0){
						outputStream.write(buffer, 0, count);
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

	/**
	 * @param partMetaData
	 * @return
	 */
	private boolean validPart(PartMetaData partMetaData) {
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
		Long startTime = System.currentTimeMillis();
		Long totalTime = 0L;
		try {
			join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
