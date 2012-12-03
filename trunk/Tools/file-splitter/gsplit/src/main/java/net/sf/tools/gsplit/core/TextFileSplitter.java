package net.sf.tools.gsplit.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.SwingWorker;

import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.WorkerTaskConstants;

/**
 * 
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class TextFileSplitter extends SwingWorker<Void, Void> implements
		WorkerTaskConstants {

	private final File sourceFile;
	private final File targetDir;
	private boolean isWorkerTask;
	private int maximumLineCount;

	public TextFileSplitter(String fileName, String targeDirName) {
		this(new File(fileName), new File(targeDirName));
	}

	public TextFileSplitter(File sourceFile, File targetDir) {
		this.sourceFile = sourceFile;
		this.targetDir = targetDir;
	}

	public int getMaximumLineCount() {
		return maximumLineCount;
	}

	public void setMaximumLineCount(int maximumLineCount) {
		this.maximumLineCount = maximumLineCount;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public File getTargetDir() {
		return targetDir;
	}

	public boolean isWorkerTask() {
		return isWorkerTask;
	}

	public void setWorkerTask(boolean isWorkerTask) {
		this.isWorkerTask = isWorkerTask;
	}

	public void splitByLineCount() throws IOException {
		BufferedReader inputReader = null;
		BufferedWriter outputWriter = null;
		try{
			int partCount = 0;
			String line = "";
			int currentLineCount = 0;
			
			inputReader = new BufferedReader(
					new FileReader(sourceFile)
				);
			
			while((line = inputReader.readLine()) != null){
				currentLineCount ++;
				if(currentLineCount <= maximumLineCount){
					if(null == outputWriter ){
						String targetFileName = getSourceFile().getName() 
								+ SplitterConstants.PART_EXT 
								+ partCount;
						outputWriter = new BufferedWriter(
								new FileWriter(new File(getTargetDir(), targetFileName))
							);
					}
					outputWriter.append(line + System.getProperty("line.separator"));
				} else {
					partCount ++;
					currentLineCount = 1;
					if(null != outputWriter){
						outputWriter.close();
						outputWriter = null;
					}
					if(null == outputWriter ){
						String targetFileName = getSourceFile().getName() 
								+ SplitterConstants.PART_EXT 
								+ partCount;
						outputWriter = new BufferedWriter(
								new FileWriter(new File(getTargetDir(), targetFileName))
							);
					}
					outputWriter.append(line + System.getProperty("line.separator"));
				}
			}
		} finally {
			if(null != inputReader){
				inputReader.close();
			}
			if(null != outputWriter){
				outputWriter.close();
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
			splitByLineCount();
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
