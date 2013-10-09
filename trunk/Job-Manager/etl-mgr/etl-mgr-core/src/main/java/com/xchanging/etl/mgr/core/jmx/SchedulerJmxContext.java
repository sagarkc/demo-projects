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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.management.remote.JMXConnector;

import com.xchanging.etl.mgr.model.scheduler.BatchJobDetail;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class SchedulerJmxContext {

	private JMXConnector jmxConnector;
	private Map<String, Map<String, String>> jobsByGroup;
	private Map<String, SchedulerMBean> schedulerMBeanMap;
	
	
	public SchedulerJmxContext(){
		jobsByGroup = new LinkedHashMap<String, Map<String, String>>();
		schedulerMBeanMap = new LinkedHashMap<String, SchedulerMBean>();
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

	public Map<String, SchedulerMBean> getSchedulerMBeanMap() {
		return schedulerMBeanMap;
	}

	public void setSchedulerMBeanMap(Map<String, SchedulerMBean> schedulerMBeanMap) {
		this.schedulerMBeanMap = schedulerMBeanMap;
	}

	
	public void triggerJob(String jobName) throws Exception{
		SchedulerMBean schedulerMBean = schedulerMBeanMap.get("scheduler.jmx.mbean:name=SchedulerMonitorBean");
		if(null != schedulerMBean){
			schedulerMBean.executeJob(jobName);
		}
	}
	
}
