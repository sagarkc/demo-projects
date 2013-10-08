/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.admin.NoSuchStepExecutionException
 * Date:	Oct 8, 2013  5:14:55 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx.admin;

import org.springframework.batch.core.JobExecutionException;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class NoSuchStepExecutionException extends JobExecutionException {

	public NoSuchStepExecutionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchStepExecutionException(String msg) {
		super(msg);
	}

}
