package com.xchanging.support.batch.admin.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Sabuj
 *
 */
public class JobDataMapper implements Serializable{

	private static final long serialVersionUID = 6287074265700259569L;
	
	private Map<String, Map<String, String>> batchJobDetailMapByJobGroup ;
	
	public static final String JMX_MBEAN_NAME = "scheduler.jmx.mbean:name=JobdetailMapperBean";
	
	/**
	 * 
	 */
	public JobDataMapper() {
		batchJobDetailMapByJobGroup = new HashMap<String, Map<String, String>>();
	}
	
	public synchronized void addBatchJobDetail(BatchJobDetail batchJobDetail){
		if(null == batchJobDetailMapByJobGroup.get(batchJobDetail.getJobGroupName())){
			batchJobDetailMapByJobGroup.put(batchJobDetail.getJobGroupName(), new HashMap<String, String>());
		} 
		batchJobDetailMapByJobGroup.get(batchJobDetail.getJobGroupName())
			.put(batchJobDetail.getTargetJobName(), batchJobDetail.getJobDetailName());
	}
	
	public String getBatchJobDetail(String jobGroupName, String jobName){
		if(null != batchJobDetailMapByJobGroup.get(jobGroupName)){
			return batchJobDetailMapByJobGroup.get(jobGroupName).get(jobName);
		}
		return null;
	}

	public Map<String, Map<String, String>> getBatchJobDetailMapByJobGroup() {
		return batchJobDetailMapByJobGroup;
	}
	
	
}
