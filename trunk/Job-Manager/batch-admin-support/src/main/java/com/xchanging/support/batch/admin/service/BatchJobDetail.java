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
package com.xchanging.support.batch.admin.service;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class BatchJobDetail {

	private String jobDetailName;
	private String targetJobName;
	private JobRegistry jobRegistry;
	private JobLauncher jobLauncher;
	
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

	public JobRegistry getJobRegistry() {
		return jobRegistry;
	}

	public void setJobRegistry(JobRegistry jobRegistry) {
		this.jobRegistry = jobRegistry;
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
	
	
}
