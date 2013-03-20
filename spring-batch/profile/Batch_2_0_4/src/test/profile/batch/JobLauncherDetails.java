package test.profile.batch;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class JobLauncherDetails extends QuartzJobBean {
	/**
	 * Special key in job data map for the name of a job to run.
	 */
	static final String JOB_NAME = "jobName";

	private JobLocator jobLocator;

	private JobLauncher jobLauncher;

	/**
	 * Public setter for the {@link JobLocator}.
	 * 
	 * @param jobLocator
	 *            the {@link JobLocator} to set
	 */
	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	/**
	 * Public setter for the {@link JobLauncher}.
	 * 
	 * @param jobLauncher
	 *            the {@link JobLauncher} to set
	 */
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	@SuppressWarnings("unchecked")
	protected void executeInternal(JobExecutionContext context) {

		long sleepTime = (long) (Math.random() * 5000);
		// Sleep for a random amount of time to help prevent deadlock
		System.out.println(Thread.currentThread().getName() + " sleeping for " + sleepTime);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Map<String, Object> jobDataMap = context.getMergedJobDataMap();
		String jobName = (String) jobDataMap.get(JOB_NAME);
		boolean emailEnabled = MapUtils.getBooleanValue(jobDataMap, "emailEnabled", true);
		System.out.println("'" + jobName + "' launching.  Thread ID = "+Thread.currentThread().getId());
		JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
		try {
			jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
		} catch (Exception e) {
			if (e instanceof DeadlockLoserDataAccessException || e instanceof DataAccessResourceFailureException) {
				System.out.println("Could not execute " + jobName + ":" + e.toString());
			} else {
				System.err.println("Could not execute " + jobName + ":" + ExceptionUtils.getFullStackTrace(e));
			}
			

		} finally {
			System.out.println("'" + jobName + "' launch finished.  Thread ID = "+Thread.currentThread().getId());
		}
	}

	/*
	 * Copy parameters that are of the correct type over to {@link
	 * JobParameters}, ignoring jobName.
	 * 
	 * @return a {@link JobParameters} instance
	 */
	private JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) {

		JobParametersBuilder builder = new JobParametersBuilder();

		// Added by Trey: Each job launch needs unique parameters or a job
		// already ran exception will be thrown
		builder.addString("Job Launch Time", DateFormatUtils.format(new Date(), "yyyyMMdd hh:mm:ss a")+":"+Math.random());

		for (Entry<String, Object> entry : jobDataMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String && !key.equals(JOB_NAME)) {
				builder.addString(key, (String) value);
			} else if (value instanceof Float || value instanceof Double) {
				builder.addDouble(key, ((Number) value).doubleValue());
			} else if (value instanceof Integer || value instanceof Long) {
				builder.addLong(key, ((Number) value).longValue());
			} else if (value instanceof Date) {
				builder.addDate(key, (Date) value);
			} else {
				System.out.println("JobDataMap contains values which are not job parameters (ignoring).");
			}
		}

		return builder.toJobParameters();

	}

}
