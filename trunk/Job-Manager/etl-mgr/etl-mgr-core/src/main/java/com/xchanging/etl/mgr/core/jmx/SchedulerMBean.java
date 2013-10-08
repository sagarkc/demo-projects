/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.SchedulerMBean
 * Date:	Aug 26, 2013  7:25:09 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

import java.util.List;
import java.util.Set;

import com.xchanging.etl.mgr.model.scheduler.BatchJobDetail;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
public interface SchedulerMBean {

	//String getJobDetailName(String jobName);
	
	String getSchedulerName();

	Set<String> getAllJobNames();

	Set<String> getAllJobNames(String jobGroupName);

	List<BatchJobDetail> getAllJobDetails();

	List<BatchJobDetail> getAllJobDetails(String groupName);

	void executeJob(String jobName) throws JmxMethodInvocationException;
	
	void executeJob(BatchJobDetail jobDetail) throws JmxMethodInvocationException;

	/*Set<String> getTriggerGroupNames();

	Set<String> getAllTriggerNames();

	Set<String> getAllTriggerNames(String troggerGroupName);

	void executeTrigger(String triggerName, String triggerGroupName)
			throws JmxMethodInvocationException;*/
}
