/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.shared.dto.JobExecutionVo
 * Date:	Aug 1, 2013  4:05:42 PM
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.xchanging.etl.mgr.common.annotations.ResultSetColumn;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
public class JobExecutionHistoryVo implements Serializable, IsSerializable {

	public interface Fields{
		String JOB_NAME = "jobName";
		String JOB_EXECUTION_ID = "jobExecutionId";
		String START_TIME = "startTime";
		String END_TIME = "endTime";
		String EXIT_CODE = "exitCode";
		String STATUS_CODE = "statusCode";
		String EXIT_MESSAGE = "exitMessage";
	}
	
	private String jobName;
	private Long jobExecutionId;
	private Date startTime;
	private Date endTime;
	private String exitCode;
	private String exitMessage;
	private String statusCode;

	@ResultSetColumn(propertyName="statusCode", mappedColumnName="STATUS_CODE")
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the jobName
	 */
	@ResultSetColumn(propertyName="jobName", mappedColumnName="JOB_NAME")
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
	@ResultSetColumn(propertyName="jobExecutionId", mappedColumnName="JOB_EXECUTION_ID")
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
	@ResultSetColumn(propertyName="startTime", mappedColumnName="JOB_START_TIME")
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
	@ResultSetColumn(propertyName="endTime", mappedColumnName="JOB_END_TIME")
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
	@ResultSetColumn(propertyName="exitCode", mappedColumnName="EXIT_CODE")
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
	@ResultSetColumn(propertyName="exitMessage", mappedColumnName="EXIT_MESSAGE")
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
