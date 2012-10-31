/**
 *
 */
package net.sf.tools.gsplit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class SplitterContext{

    private static SplitterContext context;
    private AppSettings appSettings;

    private SplitterContext() {
        
    }

    /**
     * @return the context
     */
    public static SplitterContext getContext() {
        if (null != context) {
            return context;
        }
        synchronized (SplitterContext.class) {
            context = new SplitterContext();
        }
        return context;
    }

    public AppSettings getAppSettings() {
        return appSettings;
    }
    
    
    public void initContext() {
        File settingsFile = new File(SplitterConstants.SETTINGS_FILE_NAME);
        if(settingsFile.exists()){
            this.appSettings = readAppSettings(settingsFile);
        } else {
            this.appSettings = AppSettings.DEFAULT_SETTINGS;
        }
        initLogger();
    }
    
    public void saveContext(){
        writeAppSettings(appSettings);
    }
    
    private AppSettings readAppSettings(File file){
        AppSettings appSettings = null;
        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(
                    new FileInputStream(file));
            appSettings = (AppSettings) inputStream.readObject();
        } catch (Exception ex){
            return AppSettings.DEFAULT_SETTINGS;
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException ignore) {
                }
            }
        }
        return appSettings;
    }
    
    private void writeAppSettings(AppSettings settings){
        File settingsFile = new File(SplitterConstants.SETTINGS_FILE_NAME);
        File dir = settingsFile.getParentFile();
        if(!dir.exists()){
            dir.mkdirs();
        }
        ObjectOutputStream outputStream = null;
        try{
            outputStream = new ObjectOutputStream(
                    new FileOutputStream(settingsFile));
            outputStream.writeObject(settings);
        } catch (Exception ex){
            
        } finally {
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
    
    private void initLogger() {
        Properties log4jProperties = new Properties();
        log4jProperties.setProperty("log4j.rootLogger", appSettings.getLogLevel() + ", APP_LOG");
        log4jProperties.setProperty("log4j.appender.APP_LOG", "org.apache.log4j.RollingFileAppender");
        log4jProperties.setProperty("log4j.appender.APP_LOG.layout", "org.apache.log4j.PatternLayout");
        log4jProperties.setProperty("log4j.appender.APP_LOG.layout.ConversionPattern", appSettings.getLoggerPattern());
        log4jProperties.setProperty("log4j.appender.APP_LOG.file", appSettings.getLogFileName());
        log4jProperties.setProperty("log4j.appender.APP_LOG.MaxFileSize", appSettings.getMaxLogFileSize());
        log4jProperties.setProperty("log4j.appender.APP_LOG.maxBackupIndex", "" + appSettings.getMaxLogFileCount());
        
        PropertyConfigurator.configure(log4jProperties);
    }

    public void updateSettings(AppSettings appSettings){
        this.appSettings.setLafClassName(appSettings.getLafClassName());
        this.appSettings.setLastAccessedPathName(appSettings.getLastAccessedPathName());
        this.appSettings.setLogFileName(appSettings.getLogFileName());
        this.appSettings.setLogLevel(appSettings.getLogLevel());
        this.appSettings.setLoggerPattern(appSettings.getLoggerPattern());
        this.appSettings.setMaxLogFileCount(appSettings.getMaxLogFileCount());
        this.appSettings.setMaxLogFileSize(appSettings.getMaxLogFileSize());
        initLogger();
    }
    
    public AppSettings copyAppSettings(){
        AppSettings settings = new AppSettings();
        settings.setLafClassName(this.appSettings.getLafClassName());
        settings.setLastAccessedPathName(this.appSettings.getLastAccessedPathName());
        settings.setLogFileName(this.appSettings.getLogFileName());
        settings.setLogLevel(this.appSettings.getLogLevel());
        settings.setLoggerPattern(this.appSettings.getLoggerPattern());
        settings.setMaxLogFileCount(this.appSettings.getMaxLogFileCount());
        settings.setMaxLogFileSize(this.appSettings.getMaxLogFileSize());
        
        return settings;
    }
}
