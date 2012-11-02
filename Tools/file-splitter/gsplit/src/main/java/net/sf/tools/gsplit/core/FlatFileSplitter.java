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
import java.io.UnsupportedEncodingException;

import net.sf.tools.gsplit.SplitterConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class FlatFileSplitter extends BinaryFileSplitter {

	/**
	 * @param fileName
	 * @param targeDirName
	 */
	public FlatFileSplitter(String fileName, String targeDirName) {
		super(fileName, targeDirName);
	}

	/**
	 * @param sourceFile
	 * @param targetDir
	 */
	public FlatFileSplitter(File sourceFile, File targetDir) {
		super(sourceFile, targetDir);
	}

	/* (non-Javadoc)
	 * @see net.sf.tools.gsplit.core.BinaryFileSplitter#splitByPart()
	 */
	@Override
	public void splitByPart() throws IOException {
		SplitterPartInfo splitterPartIfo = calculatePartIfoByMaxPart();
		if(null != splitterPartIfo){
			createParts(splitterPartIfo);
		}
	}

	/* (non-Javadoc)
	 * @see net.sf.tools.gsplit.core.BinaryFileSplitter#splitBySize()
	 */
	@Override
	public void splitBySize() throws IOException {
		SplitterPartInfo splitterPartIfo = calculatePartIfoByMaxByte();
		if(null != splitterPartIfo){
			createParts(splitterPartIfo);
		}
	}

	/* (non-Javadoc)
	 * @see net.sf.tools.gsplit.core.BinaryFileSplitter#createParts(net.sf.tools.gsplit.core.BinaryFileSplitter.SplitterPartIfo)
	 */
	@Override
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
				
				BufferedOutputStream outputStream = null;
				try{
					outputStream = new BufferedOutputStream(
							new FileOutputStream(new File(getTargetDir(), targetFileName)));
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
					if(null != outputStream){
						try {
							outputStream.close();
						} catch (IOException e) {
						}
					}
				}
				currentPart ++;
				if(isWorkerTask()){
					firePropertyChange(PROPERTY_PROGRESS, null, 
							Math.min((int)((currentPart*1.0/splitterPartInfo.partCount*1.0)*100.0), 100)
							);
					
				}
			}
			
		} finally {
			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
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
