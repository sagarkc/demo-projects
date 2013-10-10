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
import java.util.Map;

import javax.management.remote.JMXConnector;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class SchedulerJmxContext {

	private JMXConnector jmxConnector;
	private Map<String, Map<String, String>> jobsByGroup;
	
	
	public SchedulerJmxContext(){
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

	
}
