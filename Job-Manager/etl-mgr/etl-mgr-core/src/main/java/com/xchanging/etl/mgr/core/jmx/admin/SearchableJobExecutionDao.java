/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.admin.SearchableJobExecutionDao
 * Date:	Oct 8, 2013  5:17:31 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx.admin;

import java.util.Collection;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.dao.JobExecutionDao;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface SearchableJobExecutionDao extends JobExecutionDao {

	/**
	 * @return the total number of {@link JobExecution} instances
	 */
	int countJobExecutions();

	/**
	 * Get the {@link JobExecution JobExecutions} for a specific job name in
	 * reverse order of creation (so normally of execution).
	 * 
	 * @param jobName the name of the job
	 * @param start the start index of the instances
	 * @param count the maximum number of instances to return
	 * @return the {@link JobExecution} instances requested
	 */
	List<JobExecution> getJobExecutions(String jobName, int start, int count);

	/**
	 * Get the {@link JobExecution JobExecutions} in reverse order of creation
	 * (so normally of execution).
	 * 
	 * @param start the start index of the instances
	 * @param count the maximum number of instances to return
	 * @return the {@link JobExecution} instances requested
	 */
	List<JobExecution> getJobExecutions(int start, int count);

	/**
	 * @param jobName the name of a job
	 * @return the number of {@link JobExecution JobExecutions} belonging to
	 * this job
	 */
	int countJobExecutions(String jobName);

	/**
	 * Find all the running executions (status less than STOPPING).
	 * 
	 * @return all the {@link JobExecution} instances that are currently running
	 */
	Collection<JobExecution> getRunningJobExecutions();

}
