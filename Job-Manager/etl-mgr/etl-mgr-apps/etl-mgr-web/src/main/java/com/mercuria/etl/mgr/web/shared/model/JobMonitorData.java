/**
 * File :: com.xchanging.etl.mgr.web.shared.model.JobMonitorData
 * Date :: 27-Jul-2013
 */
package com.xchanging.etl.mgr.web.shared.model;

import java.util.Date;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface JobMonitorData {

	public Long getJobInstanceId();
	public String getJobName();
	public Date getStartTime();
	public Date getEndTime();
	public String getStatus();
	public String getExitCode();
	public String getExitMessage();
}
