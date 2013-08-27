/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.model.scheduler.SchedulerDetail
 * Date:	Aug 27, 2013  11:38:36 AM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.model.scheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class SchedulerDetail implements Serializable, IsSerializable{

	public interface Fields{
		String SCHEDULER_NAME = "schedulerName";
		String ALL_JOB_DETAILS = "allJobDetails";
		String JOB_DETAILS_BY_JOB_GROUP = "jobDetailsByJobGroup";
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 15465746546876541L;
	
	private String schedulerName;
	private List<JobDetail> allJobDetails;
	private Map<String, List<JobDetail>> jobDetailsByJobGroup;
	
	/**
	 * 
	 */
	public SchedulerDetail() {
		jobDetailsByJobGroup = new LinkedHashMap<String, List<JobDetail>>();
		allJobDetails = new ArrayList<JobDetail>();
	}

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public List<JobDetail> getAllJobDetails() {
		return allJobDetails;
	}

	public void setAllJobDetails(List<JobDetail> allJobDetails) {
		this.allJobDetails = allJobDetails;
	}

	public Map<String, List<JobDetail>> getJobDetailsByJobGroup() {
		return jobDetailsByJobGroup;
	}

	public void setJobDetailsByJobGroup(
			Map<String, List<JobDetail>> jobDetailsByJobGroup) {
		this.jobDetailsByJobGroup = jobDetailsByJobGroup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SchedulerDetail [");
		if (schedulerName != null)
			builder.append("schedulerName=").append(schedulerName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
