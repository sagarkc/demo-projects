/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.JobServer
 * Date:	Aug 30, 2013  4:38:48 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

import javax.management.MBeanServerConnection;
import javax.sql.DataSource;

import com.xchanging.etl.mgr.core.JobServerFactory;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobServer {

	private String name;
	private int index;
	private DataSource dataSource;
	private String tablePrefix;
	private MBeanServerConnection mBeanServerConnection;

	private JobServerFactory jobServerFactory;
	
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

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the jobServerFactory
	 */
	public JobServerFactory getJobServerFactory() {
		return jobServerFactory;
	}

	/**
	 * @param jobServerFactory the jobServerFactory to set
	 */
	public void setJobServerFactory(JobServerFactory jobServerFactory) {
		this.jobServerFactory = jobServerFactory;
		if(null != jobServerFactory){
			jobServerFactory.addJobServer(this);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof JobServer)) {
			return false;
		}
		JobServer other = (JobServer) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobServer [");
		if (name != null)
			builder.append("name=").append(name);
		builder.append("]");
		return builder.toString();
	}

	
}
