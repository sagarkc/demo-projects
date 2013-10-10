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

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
public final class SchedulerJmxContext {

	private JMXConnector jmxConnector;
	private MBeanServerConnection mbeanServerConnection;
	private ObjectInstance schedulerJmxInstance;
	private Map<String, Map<String, String>> jobsByGroup;

	public SchedulerJmxContext() {
		jobsByGroup = new LinkedHashMap<String, Map<String, String>>();
	}

	public JMXConnector getJmxConnector() {
		return jmxConnector;
	}

	public void setJmxConnector(JMXConnector jmxConnector) {
		this.jmxConnector = jmxConnector;
	}

	public Map<String, Map<String, String>> getJobsByGroup() {
		return jobsByGroup;
	}

	public void setJobsByGroup(Map<String, Map<String, String>> jobsByGroup) {
		this.jobsByGroup = jobsByGroup;
	}

	public ObjectInstance getSchedulerJmxInstance() {
		return schedulerJmxInstance;
	}

	public void setSchedulerJmxInstance(ObjectInstance schedulerJmxInstance) {
		this.schedulerJmxInstance = schedulerJmxInstance;
	}

	public MBeanServerConnection getMbeanServerConnection() {
		return mbeanServerConnection;
	}

	public void setMbeanServerConnection(MBeanServerConnection mbeanServerConnection) {
		this.mbeanServerConnection = mbeanServerConnection;
	}

	public synchronized void executeJob(String jobName)
			throws JmxMethodInvocationException, InstanceNotFoundException,
			MBeanException, ReflectionException, IOException {
		for (String grp : jobsByGroup.keySet()) {
			if(null != jobsByGroup.get(grp)){
				if(jobsByGroup.get(grp).get(jobName) != null){
					mbeanServerConnection.invoke(
							schedulerJmxInstance.getObjectName(), "triggerJob",
							new Object[] { jobsByGroup.get(grp).get(jobName),
									grp }, new String[] {
									"java.lang.String", "java.lang.String" });
					System.out.println(jobName + " is triggred");
					return;
				}
			}
		}
	}
}
