package com.xchanging.support.batch.admin.service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Sabuj
 *
 */
public class JobDataMapper {

	private Map<String, BatchJobDetail> batchJobDetailMap = new HashMap<String, BatchJobDetail>();
	
	public BatchJobDetail getBatchJobDetail(String jobName){
		return batchJobDetailMap.get(jobName);
	}
	
	public void addBatchJobDetail(BatchJobDetail batchJobDetail){
		batchJobDetailMap.put(batchJobDetail.getTargetJobName(), batchJobDetail);
	}
}
