/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.model.vo.JobExecutionSummaryVo
 * Date:	Aug 22, 2013  5:44:13 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.model.vo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobExecutionSummaryVo implements IsSerializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133918111011522122L;
	
	public interface Fields{
		String TOTAL_JOB_COUNT = "totalJobCount";
		String COMPLETED_COUNT = "completedCount";
		String FAILED_COUNT = "failedCount";
		String STARTED_COUNT = "startedCount";
		String UNKNOWN_COUNT = "unknownCount";
	}
	
	private long totalJobCount;
	private long completedCount;
	private long failedCount;
	private long startedCount;
	private long unknownCount;
	
	/**
	 * 
	 */
	public JobExecutionSummaryVo() {
		
	}

	/**
	 * @return the totalJobCount
	 */
	public long getTotalJobCount() {
		return totalJobCount;
	}

	/**
	 * @param totalJobCount the totalJobCount to set
	 */
	public void setTotalJobCount(long totalJobCount) {
		this.totalJobCount = totalJobCount;
	}

	/**
	 * @return the completedCount
	 */
	public long getCompletedCount() {
		return completedCount;
	}

	/**
	 * @param completedCount the completedCount to set
	 */
	public void setCompletedCount(long completedCount) {
		this.completedCount = completedCount;
	}

	/**
	 * @return the failedCount
	 */
	public long getFailedCount() {
		return failedCount;
	}

	/**
	 * @param failedCount the failedCount to set
	 */
	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}

	/**
	 * @return the startedCount
	 */
	public long getStartedCount() {
		return startedCount;
	}

	/**
	 * @param startedCount the startedCount to set
	 */
	public void setStartedCount(long startedCount) {
		this.startedCount = startedCount;
	}

	/**
	 * @return the unknownCount
	 */
	public long getUnknownCount() {
		return unknownCount;
	}

	/**
	 * @param unknownCount the unknownCount to set
	 */
	public void setUnknownCount(long unknownCount) {
		this.unknownCount = unknownCount;
	}
	
	
}
