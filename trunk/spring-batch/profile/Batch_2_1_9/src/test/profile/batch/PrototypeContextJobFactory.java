package test.profile.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class PrototypeContextJobFactory implements JobFactory {

	private final String jobName;
	private final ConfigurableApplicationContext applicationContext;
	private Job job;
	
	/**
	 * @param jobName the id of the {@link Job} in the application context to be
	 * created
	 * @param applicationContextFactory a factory for an application context
	 * containing a job with the job name provided
	 */
	public PrototypeContextJobFactory(String jobName, ConfigurableApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		this.jobName = jobName;
	}

	/**
	 * Create an {@link ApplicationContext} from the factory provided and pull
	 * out a bean with the name given during initialization.
	 * 
	 * @see org.springframework.batch.core.configuration.JobFactory#createJob()
	 */
	public final Job createJob() {
		job = (Job) applicationContext.getBean(jobName, Job.class);
		return job;
	}
	
	/**
	 * Just return the name of instance passed in on initialization.
	 * 
	 * @see JobFactory#getJobName()
	 */
	public String getJobName() {
		job = (Job) this.applicationContext.getBean(jobName, Job.class);
		return job.getName();
	}

}