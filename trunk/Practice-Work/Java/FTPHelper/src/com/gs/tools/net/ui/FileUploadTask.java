/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.net.ui;

import com.gs.tools.net.core.Configuration;
import com.gs.tools.net.core.FtpClientHelper;
import com.gs.tools.net.core.FtpUploadData;
import com.gs.tools.net.core.FtpUploadReturnData;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author 50120C1509
 */
public class FileUploadTask extends SwingWorker<FtpUploadReturnData, FtpUploadData> {

    public static final String TASK_STATUS_DONE = "TASK_STATUS_DONE";
    public static final String TASK_STATUS_START = "TASK_STATUS_START";
    public static final String TASK_STATUS_ABORT = "TASK_STATUS_ABORT";
    public static final String TASK_STATUS_FAILED = "TASK_STATUS_FAILED";
    public static final String PROPERTY_PROGRESS = "PROPERTY_PROGRESS";
    public static final String PROPERTY_MESSAGE = "PROPERTY_MESSAGE";
    private Configuration configuration;
    private List<String> uploadFileList;
    private String remoteLocation;

    public FileUploadTask(Configuration configuration, List<String> uploadFileList) {
        this.configuration = configuration;
        this.uploadFileList = uploadFileList;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<String> getUploadFileList() {
        return uploadFileList;
    }

    public String getRemoteLocation() {
        return remoteLocation;
    }

    public void setRemoteLocation(String remoteLocation) {
        this.remoteLocation = remoteLocation;
    }

    @Override
    protected FtpUploadReturnData doInBackground() throws Exception {
        Long startTime = System.currentTimeMillis();
        Long totalTime = 0L;

        firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
        try {
            if (null == getConfiguration()) {
                firePropertyChange(TASK_STATUS_ABORT, null, "No Configuration found !!!");
            }
            if (null == getUploadFileList() || getUploadFileList().size() <= 0) {
                firePropertyChange(TASK_STATUS_ABORT, null, "No Files found to upload !!!");
            }

            FtpClientHelper clientHelper = new FtpClientHelper();
            FTPClient ftpClient = null;
            try {
                ftpClient = clientHelper.connect(
                        configuration.getHostName(),
                        configuration.getUserName(),
                        configuration.getPassword());
            } catch (SocketException e) {
                firePropertyChange(TASK_STATUS_ABORT, null, "Cannot connect to FTP\n\n" + e.getMessage());
            } catch (IOException e) {
                firePropertyChange(TASK_STATUS_ABORT, null, "Cannot connect to FTP\n\n" + e.getMessage());
            }

            if (null != ftpClient) {
                int count = 0;
                for (String fileName : uploadFileList) {
                    FtpUploadReturnData oldData = new FtpUploadReturnData();
                    oldData.completedFileCount = count;
                    oldData.currentFileName = fileName;
                    oldData.percentageDone = 0;
                    firePropertyChange(PROPERTY_PROGRESS, null, oldData);
                    try {
                        clientHelper.upload(ftpClient, fileName, remoteLocation);

                    } catch (SocketException e) {
                        e.printStackTrace();
                        firePropertyChange(TASK_STATUS_ABORT, null, "Cannot upload to FTP\n\n" + e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        firePropertyChange(TASK_STATUS_ABORT, null, "Cannot upload to FTP\n\n" + e.getMessage());
                    }
                    count ++;
                    FtpUploadReturnData newData = new FtpUploadReturnData();
                    newData.completedFileCount = count;
                    newData.currentFileName = fileName;
                    newData.percentageDone = 100;
                    firePropertyChange(PROPERTY_PROGRESS, null, newData);
                }

                try {
                    clientHelper.disconnect(ftpClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception exception) {
            firePropertyChange(TASK_STATUS_FAILED, totalTime, exception);
        }
        FtpUploadReturnData newData = new FtpUploadReturnData();
        newData.completedFileCount = uploadFileList.size();
        newData.currentFileName = "All Files Uploaded Successfully.";
        newData.percentageDone = 100;
        firePropertyChange(TASK_STATUS_DONE, null, newData);
        return newData;
    }
}
