/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.SchedulerJmxContext
 * Date:	Aug 26, 2013  6:09:21 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@SuppressWarnings({ "unused"})
public final class SchedulerJmxContext {

	private JMXConnector jmxConnector;
	private Map<String , SchedulerMBean> qrtzSchedulers;
	private String[] mbeanNames;
	
	public SchedulerJmxContext(){
		qrtzSchedulers = new HashMap<>();
		mbeanNames = new String[0];
	}
	
	/**
	 * 
	 */
	public void initContext(String jmxUrl) throws Exception{
		try {
			JMXServiceURL serviceURL = new JMXServiceURL(jmxUrl);
			jmxConnector = JMXConnectorFactory.connect(serviceURL);
			MBeanServerConnection  mBeanServer = jmxConnector.getMBeanServerConnection();
			
			Set<ObjectInstance> objInstances = mBeanServer.queryMBeans(null, null);
			if(null != objInstances && objInstances.size() > 0){
				mbeanNames = new String[objInstances.size()];
				int i = 0;
				for (Iterator<ObjectInstance> iterator = objInstances.iterator(); iterator
						.hasNext();) {
					ObjectInstance objectInstance =  iterator.next();
					mbeanNames[i] = objectInstance.getObjectName().getCanonicalName();
					i++;
					
				}
				
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
}
