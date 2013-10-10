package test.profile;

import org.quartz.Scheduler;
import org.springframework.batch.core.Job;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {

	public static void main(String[] args) {
		try {
			//new ClassPathXmlJobRegistry();
			ApplicationContext applicationContext
				= new ClassPathXmlApplicationContext(new String[]{"app-context.xml","bpp-context.xml"});
			System.out.println("Total initilized bean count: "+applicationContext.getBeanDefinitionCount());
			
			Job job = applicationContext.getBean("job_Test_Hello_01", Job.class);
			System.out.println(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
