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
import java.util.Map;

import javax.management.remote.JMXConnector;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class SchedulerJmxContext {

	private JMXConnector jmxConnector;
	private Map<String , SchedulerMBean> schedulerMbaenMap;
	private String[] mbeanNames;
	
	public SchedulerJmxContext(){
		schedulerMbaenMap = new HashMap<>();
		mbeanNames = new String[0];
	}

	public JMXConnector getJmxConnector() {
		return jmxConnector;
	}

	public void setJmxConnector(JMXConnector jmxConnector) {
		this.jmxConnector = jmxConnector;
	}

	public Map<String, SchedulerMBean> getSchedulerMbaenMap() {
		return schedulerMbaenMap;
	}

	public void setSchedulerMbaenMap(Map<String, SchedulerMBean> schedulerMbaenMap) {
		this.schedulerMbaenMap = schedulerMbaenMap;
	}

	public String[] getMbeanNames() {
		return mbeanNames;
	}

	public void setMbeanNames(String[] mbeanNames) {
		this.mbeanNames = mbeanNames;
	}
	
	
}
