package test.profile;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.FactoryBean;

public class JobDataMapperServiceTest implements
		FactoryBean<Map<String, String>> {

	
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
	public Map<String, String> createJobDataMapper() {
		Map<String, String> dataMapper = new HashMap<String, String>();
		try {
			String[] groupNames = scheduler.getJobGroupNames();
			for (String groupName : groupNames) {
				String[] jobDetailNames = scheduler.getJobNames(groupName);
				for (String jobDetailName : jobDetailNames) {
					JobDetail jobDetail = scheduler.getJobDetail(jobDetailName, groupName);
					dataMapper.put(jobDetail.getJobDataMap().getString(jobNameKey), jobDetailName);
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMapper;
	}

	@Override
	public Map<String, String> getObject() throws Exception {
		return createJobDataMapper();
	}

	@Override
	public Class<?> getObjectType() {
		return HashMap.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
	
}
