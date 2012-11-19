/**
 * 
 */
package net.sf.tools.gsplit.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.WorkerTaskConstants;
import net.sf.tools.gsplit.util.CollectionUtils;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class FlatFileJoiner extends SwingWorker<Void, Void> implements WorkerTaskConstants{

	private List<File> sourceFiles;
	private File targetFile;
	private boolean isWorkerTask;
	
	/**
	 * 
	 */
	public FlatFileJoiner() {
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
	
	public void join() throws IOException{
		File dir = targetFile.getParentFile();
		if(!dir.exists()){
			dir.mkdirs();
		}
		if(!CollectionUtils.hasElements(getSourceFiles())){
			return;
		}
		
		BufferedOutputStream outputStream = null;
		try{
			outputStream = new BufferedOutputStream(
					new FileOutputStream(targetFile));
			
			int count = -1;
			int writeCount = 0;
			byte[] buffer = new byte[SplitterConstants.KB];
			int fileCount = 0;
			for (File sourceFile : sourceFiles) {
				
				BufferedInputStream inputStream = null;
				try{
					inputStream = new BufferedInputStream(
							new FileInputStream(sourceFile));

					while((count = inputStream.read(buffer, 0, SplitterConstants.KB)) > 0){
						outputStream.write(buffer, 0, count);
						writeCount += count;
						if(isWorkerTask){
							firePropertyChange(PROPERTY_PROGRESS, null, 
									Math.min((int)((fileCount*1.0/sourceFiles.size()*1.0)*100.0), 100)
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
				fileCount ++;
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
