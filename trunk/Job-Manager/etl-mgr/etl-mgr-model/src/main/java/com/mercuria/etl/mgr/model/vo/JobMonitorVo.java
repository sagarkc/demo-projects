package com.mercuria.etl.mgr.model.vo;

import java.io.Serializable;
import java.util.Date;

public class JobMonitorVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430011941593495688L;

	private String jobName;
	private Date startTime;
	private Date endTime;
	private String status;
	private String exitCode;
	private String exitMessage;

	public JobMonitorVo() {
		// TODO Auto-generated constructor stub
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

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

	public String getExitCode() {
		return exitCode;
	}

	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

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

	
}
