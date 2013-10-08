/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.admin.SearchableJobInstanceDao
 * Date:	Oct 8, 2013  5:18:00 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx.admin;

import org.springframework.batch.core.repository.dao.JobInstanceDao;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface SearchableJobInstanceDao extends JobInstanceDao {

	/**
	 * @param name the name of the job instances
	 * @return the number of instances
	 */
	int countJobInstances(String name);

}