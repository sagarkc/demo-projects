/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.shared.dto.JobExecutionVo
 * Date:	Aug 1, 2013  4:05:42 PM
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
public class JobExecutionHistoryVo implements Serializable, IsSerializable {

	private String jobName;
	private Long jobExecutionId;
	private Date startTime;
	private Date endTime;
	private String exitCode;
	private String exitMessage;

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName
	 *            the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobExecutionId
	 */
	public Long getJobExecutionId() {
		return jobExecutionId;
	}

	/**
	 * @param jobExecutionId
	 *            the jobExecutionId to set
	 */
	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the exitCode
	 */
	public String getExitCode() {
		return exitCode;
	}

	/**
	 * @param exitCode
	 *            the exitCode to set
	 */
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

	/**
	 * @return the exitMessage
	 */
	public String getExitMessage() {
		return exitMessage;
	}

	/**
	 * @param exitMessage
	 *            the exitMessage to set
	 */
	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jobExecutionId == null) ? 0 : jobExecutionId.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobExecutionHistoryVo other = (JobExecutionHistoryVo) obj;
		if (jobExecutionId == null) {
			if (other.jobExecutionId != null)
				return false;
		} else if (!jobExecutionId.equals(other.jobExecutionId))
			return false;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobExecutionHistoryVo [");
		if (jobName != null)
			builder.append("jobName=").append(jobName).append(", ");
		if (jobExecutionId != null)
			builder.append("jobExecutionId=").append(jobExecutionId);
		builder.append("]");
		return builder.toString();
	}

}
