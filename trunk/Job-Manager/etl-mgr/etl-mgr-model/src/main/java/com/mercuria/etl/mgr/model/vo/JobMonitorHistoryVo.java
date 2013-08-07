/**
 * -------------------------------------------------------------------------- *
 * 							   ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.shared.dto.JobMonitorHistoryVo
 * Date:	Aug 1, 2013  4:06:21 PM
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.mercuria.etl.mgr.common.annotations.ResultSetColumn;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobMonitorHistoryVo implements Serializable, IsSerializable{

	public interface Fields{
		String JOB_NAME = "jobName";
		String STATUS = "status";
		String LAST_STARTED_TIME = "lastStartedTime";
		String LAST_ENDED_TIME = "lastEndedTime";
		String EXECUTION_DETAILS = "executionDetails";
	}
	
	private String jobName;
	private String status;
	private Date lastStartedTime;
	private Date lastEndedTime;
	
	private List<JobExecutionHistoryVo> executionDetails;
	
	/**
	 * 
	 */
	public JobMonitorHistoryVo() {
		executionDetails = new ArrayList<JobExecutionHistoryVo>();
	}

	/**
	 * @return the jobName
	 */
	@ResultSetColumn(propertyName="jobName", mappedColumnName="JOB_NAME")
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the status
	 */
	@ResultSetColumn(propertyName="status", mappedColumnName="STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the lastStartedTime
	 */
	@ResultSetColumn(propertyName="lastStartedTime", mappedColumnName="MAX_START_TIME")
	public Date getLastStartedTime() {
		return lastStartedTime;
	}

	/**
	 * @param lastStartedTime the lastStartedTime to set
	 */
	public void setLastStartedTime(Date lastStartedTime) {
		this.lastStartedTime = lastStartedTime;
	}

	/**
	 * @return the lastEndedTime
	 */
	@ResultSetColumn(propertyName="lastEndedTime", mappedColumnName="MAX_END_TIME")
	public Date getLastEndedTime() {
		return lastEndedTime;
	}

	/**
	 * @param lastEndedTime the lastEndedTime to set
	 */
	public void setLastEndedTime(Date lastEndedTime) {
		this.lastEndedTime = lastEndedTime;
	}

	/**
	 * @return the executionDetails
	 */
	public List<JobExecutionHistoryVo> getExecutionDetails() {
		return executionDetails;
	}

	/**
	 * @param executionDetails the executionDetails to set
	 */
	public void setExecutionDetails(List<JobExecutionHistoryVo> executionDetails) {
		this.executionDetails = executionDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		JobMonitorHistoryVo other = (JobMonitorHistoryVo) obj;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobMonitorHistoryVo [");
		if (jobName != null)
			builder.append("jobName=").append(jobName).append(", ");
		if (status != null)
			builder.append("status=").append(status).append(", ");
		if (lastStartedTime != null)
			builder.append("lastStartedTime=").append(lastStartedTime)
					.append(", ");
		if (lastEndedTime != null)
			builder.append("lastEndedTime=").append(lastEndedTime);
		builder.append("]");
		return builder.toString();
	}

	

	
	
	
}
