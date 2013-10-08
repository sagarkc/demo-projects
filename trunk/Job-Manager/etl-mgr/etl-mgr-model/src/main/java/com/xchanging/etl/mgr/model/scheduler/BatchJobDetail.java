/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.model.scheduler.BatchJobDetail
 * Date:	Aug 26, 2013  4:05:40 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.model.scheduler;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class BatchJobDetail implements Serializable, IsSerializable{

	public interface Fields{
		String JOB_NAME = "jobName";
		String GROUP_NAME = "groupName";
		String JOB_CLASS_NAME = "jobClassName";
		String DESCRIPTION = "description";
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 15465746546876541L;

	private String jobName;
	private String jobDetailName;
    private String groupName;
    private String description;
    private String jobClassName;

    /**
	 * 
	 */
	public BatchJobDetail() {
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getJobDetailName() {
		return jobDetailName;
	}

	public void setJobDetailName(String jobDetailName) {
		this.jobDetailName = jobDetailName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BatchJobDetail)) {
			return false;
		}
		BatchJobDetail other = (BatchJobDetail) obj;
		if (groupName == null) {
			if (other.groupName != null) {
				return false;
			}
		} else if (!groupName.equals(other.groupName)) {
			return false;
		}
		if (jobName == null) {
			if (other.jobName != null) {
				return false;
			}
		} else if (!jobName.equals(other.jobName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BatchJobDetail [");
		if (jobName != null)
			builder.append("jobName=").append(jobName).append(", ");
		if (groupName != null)
			builder.append("groupName=").append(groupName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
