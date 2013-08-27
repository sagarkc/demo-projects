package test.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanProfiler {

	public static void main(String[] args) {
		try {
			//new ClassPathXmlJobRegistry();
			ApplicationContext applicationContext
				= new ClassPathXmlApplicationContext(new String[]{"app-context.xml", "jmx-context.xml"});
			System.out.println("Total initilized bean count: "+applicationContext.getBeanDefinitionCount());
			Scheduler scheduler = (Scheduler) applicationContext.getBean("scheduler");
			System.out.println(scheduler.getJobNames(Scheduler.DEFAULT_GROUP));
			//scheduler.shutdown();
			/*if(null != applicationContext.getBean("mbeanServer")){
				MBeanServer mBeanServer = (MBeanServer) applicationContext.getBean("mbeanServer");
				ObjectName objectName = new ObjectName("scheduler.jmx.mbean:name=SchedulerMonitorBean");
				Set<ObjectInstance> insts = mBeanServer.queryMBeans(objectName, null);
				if(null != insts){
					for (ObjectInstance oi : insts) {
						MBeanInfo info = mBeanServer.getMBeanInfo( oi.getObjectName() );
						System.out.println(info.getClassName());
						MBeanOperationInfo[] ops = info.getOperations();
						if(null != ops){
							for (MBeanOperationInfo op : ops) {
								System.out.println(op.getName());
								List<String> groups = new ArrayList<String>();
								if("getJobGroupNames".equals(op.getName())){
									Object ob = mBeanServer.invoke(
											oi.getObjectName(), 
											"getJobGroupNames", 
											null, 
											null);
									System.out.println(ob);
								}
								
								if("getJobNames".equals(op.getName())){
									Object ob = mBeanServer.invoke(
											oi.getObjectName(), 
											"getJobNames", 
											new Object[]{"quartz-batch"}, 
											new String[]{"java.lang.String"});
									System.out.println(ob);
								}
							}
						}
						
					}
					
					System.out.println(objectName);
					Scheduler schedulerJmx = (Scheduler) MBeanServerInvocationHandler.newProxyInstance(mBeanServer, 
							objectName, Scheduler.class, 
							false);
					MBeanInfo info = mBeanServer.getMBeanInfo( objectName );
					schedulerJmx.getJobDetail(Scheduler.DEFAULT_GROUP, "");
					System.out.println(schedulerJmx);
				}
			}*/
			//scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
