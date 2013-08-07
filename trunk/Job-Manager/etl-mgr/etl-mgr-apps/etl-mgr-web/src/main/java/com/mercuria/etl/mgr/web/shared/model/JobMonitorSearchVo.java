package com.xchanging.etl.mgr.web.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class JobMonitorSearchVo implements IsSerializable{

	private Long searchId;
	private String searchName;
	private List<String> selectedJobNames;
	private Date executionStartDate;
	private Date executionEndTime;
	
	public JobMonitorSearchVo() {
		selectedJobNames = new ArrayList<String>();
	}

	public Long getSearchId() {
		return searchId;
	}

	public void setSearchId(Long searchId) {
		this.searchId = searchId;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<String> getSelectedJobNames() {
		return selectedJobNames;
	}

	public void setSelectedJobNames(List<String> selectedJobNames) {
		this.selectedJobNames = selectedJobNames;
	}

	public Date getExecutionStartDate() {
		return executionStartDate;
	}

	public void setExecutionStartDate(Date executionStartDate) {
		this.executionStartDate = executionStartDate;
	}

	public Date getExecutionEndTime() {
		return executionEndTime;
	}

	public void setExecutionEndTime(Date executionEndTime) {
		this.executionEndTime = executionEndTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((searchName == null) ? 0 : searchName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobMonitorSearchVo other = (JobMonitorSearchVo) obj;
		if (searchName == null) {
			if (other.searchName != null)
				return false;
		} else if (!searchName.equals(other.searchName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JobMonitorSearchVo [searchName=" + searchName + "]";
	}
	
	
}
