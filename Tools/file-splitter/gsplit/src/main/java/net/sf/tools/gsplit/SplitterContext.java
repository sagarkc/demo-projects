/**
 *
 */
package net.sf.tools.gsplit;

import java.io.File;
import java.io.Serializable;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class SplitterContext implements Serializable {

    private static SplitterContext context;

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
    
    public String lastAccessedPathName = ".";
    private Properties log4jProperties = new Properties();
    
    private void initLogger() {
        log4jProperties.setProperty("log4j.rootLogger", "DEBUG, APP_LOG");
        log4jProperties.setProperty("log4j.appender.APP_LOG", "org.apache.log4j.RollingFileAppender");
        log4jProperties.setProperty("log4j.appender.APP_LOG.layout", "org.apache.log4j.PatternLayout");
        log4jProperties.setProperty("log4j.appender.APP_LOG.layout.ConversionPattern", "%d{dd-MMM-yyyy HH:mm:ss.SSS} %-5p %C{1}: %m%n");
        log4jProperties.setProperty("log4j.appender.APP_LOG.file", "./logs/gsplit.log");
        log4jProperties.setProperty("log4j.appender.APP_LOG.MaxFileSize", "10MB");
        log4jProperties.setProperty("log4j.appender.APP_LOG.maxBackupIndex", "10");
        
        PropertyConfigurator.configure(log4jProperties);
    }

    public void updateLogger(String propertyName, String propertyValue){
        log4jProperties.setProperty(propertyName, propertyValue);
        initLogger();
    }
    
    public void updateLogger(Properties properties){
        log4jProperties.putAll(properties);
        initLogger();
    }
    
    public void initContext() {
        // load context from file
        initLogger();
    }
}
