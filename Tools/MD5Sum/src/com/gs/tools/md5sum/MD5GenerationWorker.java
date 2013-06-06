/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.md5sum;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import javax.swing.SwingWorker;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Sabuj
 */
public class MD5GenerationWorker extends SwingWorker<Void, Void>
    implements WorkerTaskConstants{
    private static final int BUFFER_SIZE = 1024;
    private final String sourceFileName;
    private final String algorithmName;
        
    public MD5GenerationWorker(String sourceFileName, String algorithmName) {
        this.sourceFileName = sourceFileName;
        this.algorithmName = algorithmName;
    }

    @Override
    protected Void doInBackground() throws Exception {
        firePropertyChange(TASK_STATUS_START, null, null);
		byte[] digest = new byte[0];
		File file = new File(sourceFileName);
        long size = file.length();
        try {
            MessageDigest md = DigestUtils.getDigest(algorithmName);//MessageDigest.getInstance(algorithmName);
            
            DigestInputStream inputStream = null;
            try{
                inputStream = new DigestInputStream(
                        new FileInputStream(sourceFileName), md);
                byte[] b = new byte[BUFFER_SIZE];
                int c = 0;
                long s = 0;
                int progress = 0;
                firePropertyChange(PROPERTY_PROGRESS, null, progress);
                while((c = inputStream.read(b, 0, BUFFER_SIZE)) > -1){
                    s += c;
                    progress = (int) (((s*1.0) / (size*1.0)) * 100.0);
                    md.update(b, 0, c);
                    firePropertyChange(PROPERTY_PROGRESS, null, progress);
                }
            } finally {
                if(null != inputStream){
                    inputStream.close();
                }
            }
            digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            firePropertyChange(TASK_STATUS_DONE, null, sb.toString());
        } catch (Exception e) {
            firePropertyChange(TASK_STATUS_FAILED, null, e.getMessage());
			return null;
        }
        
		return null;
	}
	
	public void stop() {
		cancel(true);
		firePropertyChange(TASK_STATUS_ABORT, null, null);
	}

    
    
}
