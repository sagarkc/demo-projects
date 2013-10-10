/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	org.etl.mgr.apps.quartz.jmx.SchedulerJmxContextProviderImpl
 * Date:	Aug 27, 2013  12:22:59 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package org.etl.mgr.apps.quartz.jmx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;
import org.etl.mgr.apps.quartz.QuartzSchedulerMBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContext;
import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextProvider;
import com.xchanging.etl.mgr.model.scheduler.BatchJobDetail;
import com.xchanging.support.batch.admin.model.JobDataMapper;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component
public class SchedulerJmxContextProviderImpl implements
		SchedulerJmxContextProvider, FactoryBean<SchedulerJmxContext> {

	private static Logger logger = Logger.getLogger(SchedulerJmxContextProviderImpl.class);
	
	private String hostName;
	private int portNumber;
	private String jmxUrl;
	private String mbeanObjectName;
	private SchedulerJmxContext schedulerJmxContext;
	
	/**
	 * @param hostName
	 * @param portNumber
	 */
	public SchedulerJmxContextProviderImpl(String hostName, int portNumber, String mbeanObjectName) {
		this.hostName = hostName;
		this.portNumber = portNumber;
		this.mbeanObjectName = mbeanObjectName;
		this.jmxUrl = formJmxUrl(hostName, portNumber); 
		try {
			createContext(jmxUrl);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	

	/**
	 * @param jmxUrl
	 */
	public SchedulerJmxContextProviderImpl(String jmxUrl, String mbeanObjectName) {
		this.mbeanObjectName = mbeanObjectName;
		this.jmxUrl = jmxUrl;
		try {
			createContext(jmxUrl);
		} catch (Exception e) {
			logger.error(e);
		}
	}



	public String getHostName() {
		return hostName;
	}



	public int getPortNumber() {
		return portNumber;
	}



	public String getJmxUrl() {
		return jmxUrl;
	}



	public String getMbeanObjectName() {
		return mbeanObjectName;
	}



	public SchedulerJmxContext getSchedulerJmxContext() {
		return schedulerJmxContext;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public SchedulerJmxContext getObject() throws Exception {
		return schedulerJmxContext;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return SchedulerJmxContext.class;
	}



	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextProvider#formJmxUrl(java.lang.String, int)
	 */
	@Override
	public String formJmxUrl(String host, int port) {
		
		return "service:jmx:rmi:///jndi/rmi://"+host+":"+port+"/jmxrmi";
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextProvider#createContext(java.lang.String)
	 */
	@Override
	public void createContext(String jmxUrl) throws Exception {
		schedulerJmxContext = new SchedulerJmxContext();
		JMXServiceURL serviceURL = new JMXServiceURL(jmxUrl);
		JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
		schedulerJmxContext.setJmxConnector(jmxConnector);
		
		MBeanServerConnection  mBeanServerCon = jmxConnector.getMBeanServerConnection();
		
		ObjectName schedulerObjectName = new ObjectName(mbeanObjectName);
		ObjectName jobDataObjectName = new ObjectName(JobDataMapper.JMX_MBEAN_NAME);
		
		/*Set<ObjectInstance> insts = mBeanServerCon.queryMBeans(objectName, null);
		if(null != insts){
			Scheduler schedulerJmx = (Scheduler) MBeanServerInvocationHandler.newProxyInstance(
					mBeanServerCon, 
					objectName, Scheduler.class, 
					false);
			MBeanInfo info = mBeanServerCon.getMBeanInfo( objectName );
		}*/
		
		
		Set<ObjectInstance> jobDataObjInstances = mBeanServerCon.queryMBeans(jobDataObjectName, null);
		Set<ObjectInstance> schedulerObjInstances = mBeanServerCon.queryMBeans(schedulerObjectName, null);
		if(null != schedulerObjInstances && schedulerObjInstances.size() > 0
				&& null != jobDataObjInstances && jobDataObjInstances.size() > 0){
			
			ObjectInstance jobDataObjectInstance = jobDataObjInstances.iterator().next();
			Object jobDataMapObject = mBeanServerCon.invoke(
					jobDataObjectInstance.getObjectName(), 
					"getBatchJobDetailMapByJobGroup", 
					null, 
					null);
			Map<String, Map<String, String>> jobDataMap = null;
			if(jobDataMapObject != null && jobDataMapObject instanceof Map){
				jobDataMap = (Map<String, Map<String, String>>) jobDataMapObject;
			}
			
			schedulerJmxContext.setJobsByGroup(jobDataMap);
			schedulerJmxContext.setSchedulerJmxInstance(schedulerObjInstances.iterator().next());
			schedulerJmxContext.setMbeanServerConnection(mBeanServerCon);
			
			/*for (Iterator<ObjectInstance> iterator = schedulerObjInstances.iterator(); 
					iterator.hasNext() && null != jobDataMap && jobDataMap.size() > 0;) {
				ObjectInstance objectInstance =  iterator.next();
				String schedulerMbeanName = objectInstance.getObjectName().getCanonicalName();
				QuartzSchedulerMBean quartzSchedulerMBean = new QuartzSchedulerMBean();
				
				MBeanInfo mBeanInfo = mBeanServerCon.getMBeanInfo( objectInstance.getObjectName() );
				quartzSchedulerMBean.setSchedulerJmxInstance(objectInstance);
				quartzSchedulerMBean.setMbeanServerConnection(mBeanServerCon);
				if(null != mBeanInfo){
					Object groupObject = mBeanServerCon.invoke(
							objectInstance.getObjectName(), 
							"getJobGroupNames", 
							null, 
							null);
					if(null != groupObject && groupObject instanceof String[]){
						String[] grpNames = (String[]) groupObject;
						for (String grpName : grpNames) {
							if(null == quartzSchedulerMBean.getJobNamesByGroup().get(grpName)){
								quartzSchedulerMBean.getJobNamesByGroup().put(grpName, new LinkedHashSet<String>());
							}
							if(null == quartzSchedulerMBean.getJobDetailsByGroup().get(grpName)){
								quartzSchedulerMBean.getJobDetailsByGroup().put(grpName, new ArrayList<BatchJobDetail>());
							}
							Object jobObject = mBeanServerCon.invoke(
									objectInstance.getObjectName(), 
									"getJobNames", 
									new Object[]{grpName}, 
									new String[]{"java.lang.String"});
							if(null != jobObject && jobObject instanceof String[]){
								String[] jobNames = (String[]) jobObject;
								for (String jobBeanName : jobNames) {
									quartzSchedulerMBean.getJobNamesByGroup().get(grpName).add(jobBeanName);
									BatchJobDetail jobDetail = new BatchJobDetail();
									jobDetail.setGroupName(grpName);
									jobDetail.setJobDetailName(jobDataMap.get(grpName).get(jobBeanName));
									jobDetail.setJobName(jobBeanName);
									quartzSchedulerMBean.getJobDetailsByGroup().get(grpName).add(jobDetail);
									
									
									
									Object jobDetailObject = mBeanServerCon.invoke(
											objectInstance.getObjectName(), 
											"getJobDetail", 
											new Object[]{jobBeanName, grpName}, 
											new String[]{"java.lang.String","java.lang.String"});
									if(null != jobDetailObject && jobDetailObject instanceof org.quartz.JobDetail){
										org.quartz.JobDetail detail = (org.quartz.JobDetail) jobDetailObject;
										BatchJobDetail jobDetail = new BatchJobDetail();
										jobDetail.setGroupName(grpName);
										jobDetail.setJobDetailName(jobBeanName);
										jobDetail.setJobName(detail.getJobDataMap().getString("jobName"));
										jobDetail.setDescription(detail.getDescription());
										jobDetail.setJobClassName(detail.getJobClass().getCanonicalName());
										quartzSchedulerMBean.getJobDetailsByGroup().get(grpName).add(jobDetail);
									} 
								}
							}
						}
					}
					
				}
				schedulerJmxContext.getSchedulerMBeanMap().put(schedulerMbeanName, quartzSchedulerMBean);
			}*/
		}
	}



	

}
