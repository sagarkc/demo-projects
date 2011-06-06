/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.net.core;

import java.io.File;
import java.util.List;

/**
 *
 * @author 50120C1509
 */
public final class UploadRunner implements Runnable{

    private Configuration configuration;
    private List<File> uploadFileList;

    public UploadRunner(Configuration configuration, List<File> uploadFileList) {
        this.configuration = configuration;
        this.uploadFileList = uploadFileList;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<File> getUploadFileList() {
        return uploadFileList;
    }
    
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
