package test.profile;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
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
			/*Scheduler scheduler = (Scheduler) applicationContext.getBean("scheduler");
			//scheduler.shutdown();
			if(null != applicationContext.getBean("mbeanServer")){
				MBeanServer mBeanServerCon = (MBeanServer) applicationContext.getBean("mbeanServer");
				ObjectName objectName = new ObjectName("scheduler.jmx.mbean:name=SchedulerMonitorBean");
				Set<ObjectInstance> insts = mBeanServerCon.queryMBeans(objectName, null);
				if(null != insts){
					ObjectInstance objectInstance = insts.iterator().next();
					MBeanInfo mBeanInfo = mBeanServerCon.getMBeanInfo( objectInstance.getObjectName() );
					if(null != mBeanInfo){
						Object groupObject = mBeanServerCon.invoke(
								objectInstance.getObjectName(), 
								"getJobGroupNames", 
								null, 
								null);
						if(null != groupObject && groupObject instanceof String[]){
							String[] grpNames = (String[]) groupObject;
							for (String grpName : grpNames) {
								
								Object jobObject = mBeanServerCon.invoke(
										objectInstance.getObjectName(), 
										"getJobNames", 
										new Object[]{grpName}, 
										new String[]{"java.lang.String"});
								if(null != jobObject && jobObject instanceof String[]){
									String[] jobNames = (String[]) jobObject;
									for (String jobName : jobNames) {
										System.out.println(jobName);
										Object jobDetailObject = mBeanServerCon.invoke(
												objectInstance.getObjectName(), 
												"getJobDetail", 
												new Object[]{jobName, grpName}, 
												new String[]{"java.lang.String","java.lang.String"});
										if(null != jobDetailObject){
											JobDetail detail = (JobDetail) jobDetailObject;
											BatchJobDetail batchJobDetail = new BatchJobDetail();
											batchJobDetail.setJobDetailName(jobName);
											Set<String> keys = detail.getJobDataMap().keySet();
											for (String k : keys) {
												Object val = detail.getJobDataMap().get(k);
												if(null != val){
													if(val instanceof JobLauncher){
														JobLauncher jobLauncher = (JobLauncher) val;
														batchJobDetail.setJobLauncher(jobLauncher);
													} else if(val instanceof JobRegistry){
														JobRegistry jobRegistry = (JobRegistry) val;
														batchJobDetail.setJobRegistry(jobRegistry);
													} else if(val instanceof String){
														String targetJobName = val.toString();
														batchJobDetail.setTargetJobName(targetJobName);
													}
												}
											}
										}
									}
								}
							}
						}
						
					}
				}
			}*/
			//scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
