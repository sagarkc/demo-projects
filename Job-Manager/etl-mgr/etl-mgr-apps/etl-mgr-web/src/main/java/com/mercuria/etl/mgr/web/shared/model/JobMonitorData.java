package com.mercuria.etl.mgr.web.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionData;
import com.mercuria.etl.mgr.web.client.core.GWTGridColumnHeader;

public class JobMonitorData implements IsSerializable, GWTCollectionData {

	private String jobName;
	private String status;

	public JobMonitorData() {
		// TODO Auto-generated constructor stub
	}

	public JobMonitorData(String jobName, String status) {
		this.jobName = jobName;
		this.status = status;
	}

	@GWTGridColumnHeader(index=0,fieldName="jobName", title="JOB Name")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@GWTGridColumnHeader(index=1,fieldName="status", title="JOB Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
