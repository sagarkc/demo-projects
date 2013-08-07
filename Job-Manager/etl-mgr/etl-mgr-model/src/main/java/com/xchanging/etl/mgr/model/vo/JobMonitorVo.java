package com.xchanging.etl.mgr.model.vo;

import java.io.Serializable;
import java.util.Date;




import com.google.gwt.user.client.rpc.IsSerializable;
import com.xchanging.etl.mgr.common.annotations.ResultSetColumn;

public class JobMonitorVo implements Serializable, IsSerializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430011941593495688L;

	private Long jobInstanceId;
	private String jobName;
	private Date startTime;
	private Date endTime;
	private String status;
	private String exitCode;
	private String exitMessage;

	public JobMonitorVo() {
		// TODO Auto-generated constructor stub
	}

	@ResultSetColumn(propertyName="jobInstanceId", mappedColumnName="JOB_INSTANCE_ID")
	public Long getJobInstanceId() {
		return jobInstanceId;
	}


	public void setJobInstanceId(Long jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
	}


	@ResultSetColumn(propertyName="jobName", mappedColumnName="JOB_NAME")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@ResultSetColumn(propertyName="startTime", mappedColumnName="JOB_START_TIME")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@ResultSetColumn(propertyName="endTime", mappedColumnName="JOB_END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ResultSetColumn(propertyName="exitCode", mappedColumnName="EXIT_CODE")
	public String getExitCode() {
		return exitCode;
	}

	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

	@ResultSetColumn(propertyName="exitMessage", mappedColumnName="EXIT_MESSAGE")
	public String getExitMessage() {
		return exitMessage;
	}

	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}

	@Override
	public String toString() {
		return "JobMonitorVo [jobName=" + jobName + ", status=" + status
				+ ", exitCode=" + exitCode + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jobInstanceId == null) ? 0 : jobInstanceId.hashCode());
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
		if (!(obj instanceof JobMonitorVo)) {
			return false;
		}
		JobMonitorVo other = (JobMonitorVo) obj;
		if (jobInstanceId == null) {
			if (other.jobInstanceId != null) {
				return false;
			}
		} else if (!jobInstanceId.equals(other.jobInstanceId)) {
			return false;
		}
		return true;
	}

	
	
}
