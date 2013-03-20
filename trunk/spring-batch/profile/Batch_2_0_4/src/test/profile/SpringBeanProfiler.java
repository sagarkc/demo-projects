package test.profile;

import org.quartz.Scheduler;
import org.springframework.batch.core.configuration.support.ClassPathXmlJobRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanProfiler {

	public static void main(String[] args) {
		try {
			//new ClassPathXmlJobRegistry();
			ApplicationContext applicationContext
				= new ClassPathXmlApplicationContext(new String[]{"app-context.xml"});
			System.out.println("Total initilized bean count: "+applicationContext.getBeanDefinitionCount());
			Scheduler scheduler = (Scheduler) applicationContext.getBean("scheduler");
	
			//scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
