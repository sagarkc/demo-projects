/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.tools.gsplit;

import java.io.Serializable;
import javax.swing.UIManager;
import org.apache.log4j.lf5.LogLevel;

/**
 *
 * @author SG1736
 */
public final class AppSettings implements Serializable{
    
    private String lafClassName;
    private String logLevel;
    private String logFileName;
    private String loggerPattern;
    private String maxLogFileSize;
    private int maxLogFileCount;
    
    private String lastAccessedPathName;

    public static final AppSettings DEFAULT_SETTINGS;
    static{
        DEFAULT_SETTINGS = new AppSettings();
        DEFAULT_SETTINGS.lafClassName = UIManager.getSystemLookAndFeelClassName();
        DEFAULT_SETTINGS.lastAccessedPathName = ".";
        
        DEFAULT_SETTINGS.logLevel = LogLevel.INFO.getLabel();
        DEFAULT_SETTINGS.logFileName = SplitterConstants.LOG_FILE_NAME;
        DEFAULT_SETTINGS.loggerPattern = "%d{dd/MM/yy HH:mm:ss} %-5p %C{1}: %m%n";
        DEFAULT_SETTINGS.maxLogFileCount = 10;
        DEFAULT_SETTINGS.maxLogFileSize = "10MB";
    }
    
    public AppSettings() {
    }

    public String getLafClassName() {
        return lafClassName;
    }

    public void setLafClassName(String lafClassName) {
        this.lafClassName = lafClassName;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public String getLoggerPattern() {
        return loggerPattern;
    }

    public void setLoggerPattern(String loggerPattern) {
        this.loggerPattern = loggerPattern;
    }

    public String getMaxLogFileSize() {
        return maxLogFileSize;
    }

    public void setMaxLogFileSize(String maxLogFileSize) {
        this.maxLogFileSize = maxLogFileSize;
    }

    public int getMaxLogFileCount() {
        return maxLogFileCount;
    }

    public void setMaxLogFileCount(int maxLogFileCount) {
        this.maxLogFileCount = maxLogFileCount;
    }

    public String getLastAccessedPathName() {
        return lastAccessedPathName;
    }

    public void setLastAccessedPathName(String lastAccessedPathName) {
        this.lastAccessedPathName = lastAccessedPathName;
    }
    
    
}
