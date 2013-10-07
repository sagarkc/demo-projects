package test.profile;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanProfiler {

	public static void main(String[] args) {
		try {
			
			DOMConfigurator.configure(SpringBeanProfiler.class.getResource("/logger/log4j.xml"));
			
			Logger logger = Logger.getLogger(SpringBeanProfiler.class);
			logger.info("starting app...");
			//new ClassPathXmlJobRegistry();
			ApplicationContext applicationContext
				= new ClassPathXmlApplicationContext(new String[]{"app-context.xml", "jmx-context.xml"});
			System.out.println("Total initilized bean count: "+applicationContext.getBeanDefinitionCount());
			
			//scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

