/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	org.etl.mgr.apps.quartz.QuartzSchedulerMBean
 * Date:	Aug 26, 2013  8:08:25 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package org.etl.mgr.apps.quartz;

import java.util.List;
import java.util.Set;

import com.xchanging.etl.mgr.core.jmx.JmxMethodInvocationException;
import com.xchanging.etl.mgr.core.jmx.SchedulerMBean;
import com.xchanging.etl.mgr.model.scheduler.JobDetail;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class QuartzSchedulerMBean implements SchedulerMBean {

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerMBean#getSchedulerName()
	 */
	@Override
	public String getSchedulerName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerMBean#getAllJobNames()
	 */
	@Override
	public Set<String> getAllJobNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerMBean#getAllJobNames(java.lang.String)
	 */
	@Override
	public Set<String> getAllJobNames(String jobGroupName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerMBean#getAllJobDetails()
	 */
	@Override
	public List<JobDetail> getAllJobDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerMBean#getAllJobDetails(java.lang.String)
	 */
	@Override
	public List<JobDetail> getAllJobDetails(String groupName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.core.jmx.SchedulerMBean#executeJob(com.xchanging.etl.mgr.model.scheduler.JobDetail)
	 */
	@Override
	public void executeJob(JobDetail jobDetail)
			throws JmxMethodInvocationException {
		// TODO Auto-generated method stub

	}

}
