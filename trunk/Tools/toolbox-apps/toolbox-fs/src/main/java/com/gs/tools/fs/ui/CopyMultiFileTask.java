package com.gs.tools.fs.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;


public class CopyMultiFileTask extends SwingWorker<Void, List<String>>{

	public static final String TASK_STATUS_DONE = "TASK_STATUS_DONE";
	public static final String TASK_STATUS_START = "TASK_STATUS_START";
	public static final String TASK_STATUS_ABORT = "TASK_STATUS_ABORT";
	public static final String TASK_STATUS_FAILED = "TASK_STATUS_FAILED";
	
	public static final String PROPERTY_PROGRESS = "PROPERTY_PROGRESS";
	public static final String PROPERTY_MESSAGE = "PROPERTY_MESSAGE";
	
	private List<String> fileNameList;
	private String targetRoot;
	private String sourceRoot;
	private boolean createDirectory;
	
	public CopyMultiFileTask() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		Long startTime = System.currentTimeMillis();
		Long totalTime = 0L;
		int hitCount = 0;
		firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
		setProgress(0);
		if(null != fileNameList && fileNameList.size() > 0){
			for (int i = 0; i < fileNameList.size(); i++) {
				String fileName = fileNameList.get(i);
				if(fileName.startsWith(sourceRoot)){
					String sourceFolders = targetRoot + 
						fileName.substring(sourceRoot.length(), 
							fileName.lastIndexOf("\\"));
					String distFileName = targetRoot + 
						fileName.substring(sourceRoot.length());
					File dir = new File(sourceFolders);
					if(!dir.exists() && createDirectory){
						dir.mkdirs();
					}
					try {
						if(dir.exists()){
							firePropertyChange(PROPERTY_PROGRESS, fileName, TASK_STATUS_START);
							FileUtils.copyFile(new File(fileName), new File(distFileName));
							hitCount ++;
							firePropertyChange(PROPERTY_PROGRESS, new Object[]{fileName, i, fileNameList.size()}, TASK_STATUS_DONE);
							int percent = (int)(((float)(float)i / (float)fileNameList.size()) * 100.0F);
							setProgress(percent);
						} else {
							throw new FileNotFoundException(dir.getAbsolutePath());
						}
					} catch (FileNotFoundException e) {
						firePropertyChange(PROPERTY_PROGRESS, new Object[]{"Cannot find path: " + dir.getAbsolutePath(), i, fileNameList.size()}, TASK_STATUS_FAILED);
						int percent = (int)(((float)(float)i / (float)fileNameList.size()) * 100.0F);
						setProgress(percent);
						e.printStackTrace();
					} catch (IOException e) {
						firePropertyChange(PROPERTY_PROGRESS, new Object[]{e.getMessage(), i, fileNameList.size()}, TASK_STATUS_FAILED);
						int percent = (int)(((float)(float)i / (float)fileNameList.size()) * 100.0F);
						setProgress(percent);
						e.printStackTrace();
					}
				} else {
					firePropertyChange(PROPERTY_PROGRESS, 
							new Object[]{"ERROR:- File: [ " + fileName + " ] does not belongs to the source: " + sourceRoot, i, fileNameList.size()}
							, TASK_STATUS_FAILED);
					int percent = (int)(((float)(float)i / (float)fileNameList.size()) * 100.0F);
					setProgress(percent);
				}
			}
		} else {
			Long endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			firePropertyChange(TASK_STATUS_DONE, totalTime, new Integer(0));
			setProgress(0);
		}
		Long endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		firePropertyChange(TASK_STATUS_DONE, totalTime, new Integer(hitCount));
		setProgress(100);
		return null;
	}
	
	

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public void setFileNameList(List<String> fileNameList) {
		this.fileNameList = fileNameList;
	}

	public String getTargetRoot() {
		return targetRoot;
	}

	public void setTargetRoot(String targetRoot) {
		this.targetRoot = targetRoot;
	}

	public String getSourceRoot() {
		return sourceRoot;
	}

	public void setSourceRoot(String sourceRoot) {
		this.sourceRoot = sourceRoot;
	}

	public boolean isCreateDirectory() {
		return createDirectory;
	}

	public void setCreateDirectory(boolean createDirectory) {
		this.createDirectory = createDirectory;
	}

	
	
}


