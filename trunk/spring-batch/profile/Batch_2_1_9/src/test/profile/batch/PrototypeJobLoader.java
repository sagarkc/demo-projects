package test.profile.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.DefaultJobLoader;
import org.springframework.batch.core.configuration.support.JobLoader;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.context.ConfigurableApplicationContext;

public class PrototypeJobLoader implements JobLoader {

	private static Log logger = LogFactory.getLog(DefaultJobLoader.class);

	private JobRegistry jobRegistry;

	private Map<ApplicationContextFactory, ConfigurableApplicationContext> contexts 
		= new ConcurrentHashMap<ApplicationContextFactory, ConfigurableApplicationContext>();

	private Map<ConfigurableApplicationContext, Collection<String>> contextToJobNames 
		= new ConcurrentHashMap<ConfigurableApplicationContext, Collection<String>>();

	private ConfigurableApplicationContext applicationContext;
	
	/**
	 * Default constructor useful for declarative configuration.
	 */
	public PrototypeJobLoader() {
		this(null);
	}

	/**
	 * Create a job loader with the job registry provided.
	 * @param jobRegistry a {@link JobRegistry}
	 */
	public PrototypeJobLoader(JobRegistry jobRegistry) {
		this.jobRegistry = jobRegistry;
	}

	/**
	 * The {@link JobRegistry} to use for jobs created.
	 * 
	 * @param jobRegistry
	 */
	public void setJobRegistry(JobRegistry jobRegistry) {
		this.jobRegistry = jobRegistry;
	}

	/**
	 * Unregister all the jobs and close all the contexts created by this
	 * loader.
	 * 
	 * @see JobLoader#clear()
	 */
	public void clear() {
		for (ConfigurableApplicationContext context : contexts.values()) {
			if (context.isActive()) {
				context.close();
			}
		}
		for (String jobName : jobRegistry.getJobNames()) {
			jobRegistry.unregister(jobName);
		}
		contexts.clear();
	}

	public Collection<Job> reload(ApplicationContextFactory factory) {

		// If the same factory is loaded twice the context can be closed
		if (contexts.containsKey(factory)) {
			ConfigurableApplicationContext context = contexts.get(factory);
			for (String name : contextToJobNames.get(context)) {
				logger.debug("Unregistering job: " + name + " from context: " + context.getDisplayName());
				jobRegistry.unregister(name);
			}
			context.close();
		}

		try {
			return doLoad(factory, true);
		}
		catch (DuplicateJobException e) {
			throw new IllegalStateException("Found duplicte job in reload (it should have been unregistered "
					+ "if it was previously registered in this loader)", e);
		}
	}

	public Collection<Job> load(ApplicationContextFactory factory) throws DuplicateJobException {
		return doLoad(factory, false);
	}

	private Collection<Job> doLoad(ApplicationContextFactory factory, boolean unregister) throws DuplicateJobException {
		if(null == applicationContext){
			applicationContext = factory.createApplicationContext();
		}
		contexts.put(factory, this.applicationContext);
		Collection<String> jobsRegistered = new HashSet<String>();
		String[] names = this.applicationContext.getBeanNamesForType(Job.class);
		for (String name : names) {
			Job job = (Job) this.applicationContext.getBean(name);
			String jobName = job.getName();
			// On reload try to unregister first
			if (unregister) {
				logger.debug("Unregistering job: " + jobName + " from context: " + this.applicationContext.getDisplayName());
				jobRegistry.unregister(jobName);
			}
			logger.debug("Registering job: " + jobName + " from context: " + this.applicationContext.getDisplayName());
			JobFactory jobFactory = new PrototypeContextJobFactory(jobName, this.applicationContext);
			jobRegistry.register(jobFactory);
			jobsRegistered.add(jobName);
		}

		Collection<Job> result = new ArrayList<Job>();
		for (String name : jobsRegistered) {
			try {
				result.add(jobRegistry.getJob(name));
			}
			catch (NoSuchJobException e) {
				// should not happen;
				throw new IllegalStateException("Could not retrieve job that was should have been registered", e);
			}

		}

		contextToJobNames.put(this.applicationContext, jobsRegistered);

		return result;

	}

}
