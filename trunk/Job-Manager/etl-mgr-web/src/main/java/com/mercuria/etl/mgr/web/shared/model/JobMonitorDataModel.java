/**
 * File :: com.mercuria.etl.mgr.web.shared.model.JobMonitorDataModel
 * Date :: 27-Jul-2013
 */
package com.mercuria.etl.mgr.web.shared.model;

import java.util.Date;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class JobMonitorDataModel implements JobMonitorData {

	private Long jobInstanceId;
	private String jobName;
	private Date startTime;
	private Date endTime;
	private String status;
	private String exitCode;
	private String exitMessage;

	public Long getJobInstanceId() {
		return jobInstanceId;
	}

	public void setJobInstanceId(Long jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
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

}
