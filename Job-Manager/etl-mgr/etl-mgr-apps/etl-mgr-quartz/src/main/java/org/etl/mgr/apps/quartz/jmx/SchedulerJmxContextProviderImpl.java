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
import java.util.Set;

import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;
import org.etl.mgr.apps.quartz.QuartzSchedulerMBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContext;
import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextProvider;
import com.xchanging.etl.mgr.model.scheduler.JobDetail;

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
	
	private SchedulerJmxContext schedulerJmxContext;
	
	/**
	 * @param hostName
	 * @param portNumber
	 */
	public SchedulerJmxContextProviderImpl(String hostName, int portNumber) {
		this.hostName = hostName;
		this.portNumber = portNumber;
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
	public SchedulerJmxContextProviderImpl(String jmxUrl) {
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
		//JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(serviceURL, null, null);
		JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);//jmxConnectorServer.toJMXConnector(null);
		schedulerJmxContext.setJmxConnector(jmxConnector);
		MBeanServerConnection  mBeanServerCon = jmxConnector.getMBeanServerConnection();
		
		Set<ObjectInstance> objInstances = mBeanServerCon.queryMBeans(null, null);
		if(null != objInstances && objInstances.size() > 0){
			String[] mbeanNames = new String[objInstances.size()];
			int i = 0;
			for (Iterator<ObjectInstance> iterator = objInstances.iterator(); iterator.hasNext();) {
				ObjectInstance objectInstance =  iterator.next();
				String name = objectInstance.getObjectName().getCanonicalName();
				mbeanNames[i] = name;
				QuartzSchedulerMBean quartzSchedulerMBean = new QuartzSchedulerMBean();
				
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
							if(null == quartzSchedulerMBean.getJobNamesByGroup().get(grpName)){
								quartzSchedulerMBean.getJobNamesByGroup().put(grpName, new LinkedHashSet<String>());
							}
							if(null == quartzSchedulerMBean.getJobDetailsByGroup().get(grpName)){
								quartzSchedulerMBean.getJobDetailsByGroup().put(grpName, new ArrayList<JobDetail>());
							}
							Object jobObject = mBeanServerCon.invoke(
									objectInstance.getObjectName(), 
									"getJobNames", 
									new Object[]{grpName}, 
									new String[]{"java.lang.String"});
							if(null != jobObject && jobObject instanceof String[]){
								String[] jobNames = (String[]) jobObject;
								for (String jobName : jobNames) {
									quartzSchedulerMBean.getJobNamesByGroup().get(grpName).add(jobName);
									/*JobKey jobKey = new JobKey(jobName, grpName);
									
									Object jobDetailObject = mBeanServerCon.invoke(
											objectInstance.getObjectName(), 
											"getJobDetail", 
											new Object[]{jobKey}, 
											new String[]{"org.quartz.JobKey"});
									if(null != jobDetailObject && jobDetailObject instanceof org.quartz.JobDetail){
										org.quartz.JobDetail detail = (org.quartz.JobDetail) jobDetailObject;
										JobDetail jobDetail = new JobDetail();
										jobDetail.setGroupName(grpName);
										jobDetail.setJobName(jobName);
										jobDetail.setDescription(detail.getDescription());
										jobDetail.setJobClassName(detail.getJobClass().getCanonicalName());
										quartzSchedulerMBean.getJobDetailsByGroup().get(grpName).add(jobDetail);
									} */
								}
							}
						}
					}
					
					schedulerJmxContext.getSchedulerMbaenMap().put(name, quartzSchedulerMBean);
					i++;
				}
				schedulerJmxContext.setMbeanNames(mbeanNames);
			}
		}
	}



	

}
