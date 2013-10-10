package test.profile.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.StartLimitExceededException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ConfigurableApplicationContext;

public class SimplePrototypeJob extends AbstractJob {

	private ConfigurableApplicationContext applicationContext;
	
	private List<Step> steps = new ArrayList<Step>();

	/**
	 * Default constructor for job with null name
	 */
	public SimplePrototypeJob() {
		this(null);
	}

	/**
	 * @param name
	 */
	public SimplePrototypeJob(String name) {
		super(name);
	}

	/**
	 * Public setter for the steps in this job. Overrides any calls to
	 * {@link #addStep(Step)}.
	 * 
	 * @param steps the steps to execute
	 */
	public void setSteps(List<Step> steps) {
		this.steps.clear();
		this.steps.addAll(steps);
	}

	/**
	 * Convenience method for clients to inspect the steps for this job.
	 * 
	 * @return the step names for this job
	 */
	public Collection<String> getStepNames() {
		List<String> names = new ArrayList<String>();
		for (Step step : steps) {
			names.add(step.getName());
		}
		return names;
	}

	/**
	 * Convenience method for adding a single step to the job.
	 * 
	 * @param step a {@link Step} to add
	 */
	public void addStep(Step step) {
		this.steps.add(step);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.batch.core.job.AbstractJob#getStep(java.lang.String)
	 */
	public Step getStep(String stepName) {
		for (Step step : this.steps) {
			if (step.getName().equals(stepName)) {
				return step;
			}
		}
		return null;
	}

	/**
	 * Handler of steps sequentially as provided, checking each one for success
	 * before moving to the next. Returns the last {@link StepExecution}
	 * successfully processed if it exists, and null if none were processed.
	 * 
	 * @param execution the current {@link JobExecution}
	 * 
	 * @see AbstractJob#handleStep(Step, JobExecution)
	 */
	protected void doExecute(JobExecution execution) throws JobInterruptedException, JobRestartException,
			StartLimitExceededException {

		StepExecution stepExecution = null;
		for (Step step : steps) {
			stepExecution = handleStep(step, execution);
			if (stepExecution.getStatus() != BatchStatus.COMPLETED) {
				//
				// Terminate the job if a step fails
				//
				break;
			}
		}

		//
		// Update the job status to be the same as the last step
		//
		if (stepExecution != null) {
			logger.debug("Upgrading JobExecution status: " + stepExecution);
			execution.upgradeStatus(stepExecution.getStatus());
			execution.setExitStatus(stepExecution.getExitStatus());
		}
	}

}
