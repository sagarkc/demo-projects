/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.endpoint.jmx.JmxJobRunnerServerEndpoint
 * Date:	Oct 8, 2013  1:03:04 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.server.endpoint.jmx;

import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.quartz.Scheduler;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContext;
import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextFactory;
import com.xchanging.etl.mgr.web.client.service.JmxJobRunnerService;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component(value=JmxJobRunnerService.RPC_TARGET)
public class JmxJobRunnerServerEndpoint implements JmxJobRunnerService {

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.service.JmxJobRunnerService#runJob(java.lang.String)
	 */
	@Override
	public String runJob(String jobName) {
		SchedulerJmxContext jmxContext = SchedulerJmxContextFactory.getInstance()
				.getSchedulerJmxContext("localSchedulerJmxContextProvider_localhost_9010");
		if(null != jmxContext){
			try{
				jmxContext.executeJob(jobName);
			} catch (Exception e){
				System.out.println(e);
				return "FAILED";
			}
		}
		return "SUCCESS";
	}
	
	public void launchJob(String jobName) throws Exception {
		SchedulerJmxContext jmxContext = SchedulerJmxContextFactory.getInstance()
				.getSchedulerJmxContext("localSchedulerJmxContextProvider_localhost_9010");
		MBeanServerConnection  mBeanServerCon = jmxContext.getJmxConnector().getMBeanServerConnection();
		
		ObjectName objectName = new ObjectName("scheduler.jmx.mbean:name=SchedulerMonitorBean");
		
		Set<ObjectInstance> insts = mBeanServerCon.queryMBeans(objectName, null);
		if(null != insts){
			Scheduler schedulerJmx = (Scheduler) MBeanServerInvocationHandler.newProxyInstance(
					mBeanServerCon, 
					objectName, Scheduler.class, 
					false);
			if(null != schedulerJmx){
				//schedulerJmx.triggerJob(jobName);
			}
		}
		
		JobParameters params = new JobParametersBuilder()
				.addString("jobName", jobName)
				.addLong("launchTime", System.currentTimeMillis())
			.toJobParameters();
		
		
		//BatchJobDetail jobDetail = schedulerJmx.getJobDetail(new JobKey(jobName) );
		
		//schedulerJmx.triggerJob(new JobKey(jobName));
		
		
		/*Job job = jobRegistry.getJob(jobName);
		JobExecution exec = jobLauncher.run(job, params);
		List<Throwable> errors = exec.getAllFailureExceptions();
		if (errors.size() > 0) {
			for (Throwable th : errors) {
				logger.severe(ExceptionUtils.getFullStackTrace(th));
			}
			throw new Exception("'" + jobName + "' job did not complete successfully");
		}*/
	}

	
	
}
