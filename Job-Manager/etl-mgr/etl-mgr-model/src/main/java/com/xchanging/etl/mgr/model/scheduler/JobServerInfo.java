/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.model.scheduler.JobServerInfo
 * Date:	Aug 30, 2013  4:36:00 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.model.scheduler;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.sql.DataSource;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobServerInfo {

	private DataSource dataSource;
	private String tablePrefix;
	private JMXConnector jmxConnector;
	private MBeanServerConnection mBeanServerConnection;
	
	/**
	 * 
	 */
	public JobServerInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the jmxConnector
	 */
	public JMXConnector getJmxConnector() {
		return jmxConnector;
	}

	/**
	 * @param jmxConnector the jmxConnector to set
	 */
	public void setJmxConnector(JMXConnector jmxConnector) {
		this.jmxConnector = jmxConnector;
	}

	/**
	 * @return the mBeanServerConnection
	 */
	public MBeanServerConnection getmBeanServerConnection() {
		return mBeanServerConnection;
	}

	/**
	 * @param mBeanServerConnection the mBeanServerConnection to set
	 */
	public void setmBeanServerConnection(MBeanServerConnection mBeanServerConnection) {
		this.mBeanServerConnection = mBeanServerConnection;
	}

	/**
	 * @return the tablePrefix
	 */
	public String getTablePrefix() {
		return tablePrefix;
	}

	/**
	 * @param tablePrefix the tablePrefix to set
	 */
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
	
	
	
}
