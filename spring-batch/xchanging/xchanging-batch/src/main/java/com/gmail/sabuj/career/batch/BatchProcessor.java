package com.gmail.sabuj.career.batch;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchProcessor {

	private static final Logger logger = Logger.getLogger(BatchProcessor.class);
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		/*File f = new File("./app-config/properties/batch-settings.properties");
		System.out.println(f.exists());*/
		
		logger.info("Starting Batch Processor");
        try {
        	logger.info("Load Spring ApplicationContext.....................................");
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                    new String[] { "classpath:context/application-context.xml"});
            
            if(null != applicationContext){
                System.out.println("ApplicationContext is loaded.....................................");
                logger.info("ApplicationContext is loaded.....................................");
            }else{
            	logger.info("Cannot Load ApplicationContext.....................................");
            	System.out.println("Cannot Load ApplicationContext.....................................");
            	System.exit(0);
            }
            
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
	}
	
}
