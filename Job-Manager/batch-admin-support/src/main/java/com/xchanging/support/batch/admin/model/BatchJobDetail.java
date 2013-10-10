/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	test.profile.BatchJobDetail
 * Date:	Oct 8, 2013  5:57:40 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.support.batch.admin.model;

import java.io.Serializable;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class BatchJobDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3937801689863744172L;
	private String jobDetailName;
	private String targetJobName;
	private String jobGroupName;
	/**
	 * 
	 */
	public BatchJobDetail() {
		// TODO Auto-generated constructor stub
	}

	public String getJobDetailName() {
		return jobDetailName;
	}

	public void setJobDetailName(String jobDetailName) {
		this.jobDetailName = jobDetailName;
	}

	public String getTargetJobName() {
		return targetJobName;
	}

	public void setTargetJobName(String targetJobName) {
		this.targetJobName = targetJobName;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jobDetailName == null) ? 0 : jobDetailName.hashCode());
		result = prime * result
				+ ((jobGroupName == null) ? 0 : jobGroupName.hashCode());
		result = prime * result
				+ ((targetJobName == null) ? 0 : targetJobName.hashCode());
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
		if (!(obj instanceof BatchJobDetail)) {
			return false;
		}
		BatchJobDetail other = (BatchJobDetail) obj;
		if (jobDetailName == null) {
			if (other.jobDetailName != null) {
				return false;
			}
		} else if (!jobDetailName.equals(other.jobDetailName)) {
			return false;
		}
		if (jobGroupName == null) {
			if (other.jobGroupName != null) {
				return false;
			}
		} else if (!jobGroupName.equals(other.jobGroupName)) {
			return false;
		}
		if (targetJobName == null) {
			if (other.targetJobName != null) {
				return false;
			}
		} else if (!targetJobName.equals(other.targetJobName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("BatchJobDetail [ %s / %s / %s ]",
						jobGroupName, jobDetailName, targetJobName);
	}

	
}
