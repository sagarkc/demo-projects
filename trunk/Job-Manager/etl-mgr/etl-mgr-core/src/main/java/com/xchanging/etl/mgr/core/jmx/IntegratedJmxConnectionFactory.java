/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.IntegratedJmxConnectionFactory
 * Date:	Aug 26, 2013  8:14:32 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

import java.util.List;

import javax.management.MBeanServerConnection;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class IntegratedJmxConnectionFactory {

	private List<MBeanServerConnection> mbeanServerConnections;

	/**
	 * @param mbeanServerConnections
	 */
	public IntegratedJmxConnectionFactory(
			List<MBeanServerConnection> mbeanServerConnections) {
		this.mbeanServerConnections = mbeanServerConnections;
	}

	public List<MBeanServerConnection> getMbeanServerConnections() {
		return mbeanServerConnections;
	}

	public void setMbeanServerConnections(
			List<MBeanServerConnection> mbeanServerConnections) {
		this.mbeanServerConnections = mbeanServerConnections;
	}
	
	
	
}
