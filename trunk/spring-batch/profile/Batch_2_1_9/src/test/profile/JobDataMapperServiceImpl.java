/**
 * 
 */
package test.profile;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Sabuj
 *
 */
public class JobDataMapperServiceImpl implements FactoryBean<JobDataMapper> {

	private Scheduler scheduler;
	private String jobNameKey;
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void setJobNameKey(String jobNameKey) {
		this.jobNameKey = jobNameKey;
	}

	/* (non-Javadoc)
	 * @see com.xchanging.support.batch.admin.service.JobDataMapperService#createJobDataMapper()
	 */
	public JobDataMapper createJobDataMapper() {
		JobDataMapper dataMapper = new JobDataMapper();
		try {
			String[] groupNames = scheduler.getJobGroupNames();
			for (String groupName : groupNames) {
				String[] jobDetailNames = scheduler.getJobNames(groupName);
				for (String jobDetailName : jobDetailNames) {
					JobDetail jobDetail = scheduler.getJobDetail(jobDetailName, groupName);
					BatchJobDetail batchJobDetail = new BatchJobDetail();
					batchJobDetail.setJobDetailName(jobDetailName);
					batchJobDetail.setTargetJobName(jobDetail.getJobDataMap().getString(jobNameKey));
					dataMapper.addBatchJobDetail(batchJobDetail);
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMapper;
	}

	@Override
	public JobDataMapper getObject() throws Exception {
		return createJobDataMapper();
	}

	@Override
	public Class<?> getObjectType() {
		return JobDataMapper.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	
}
