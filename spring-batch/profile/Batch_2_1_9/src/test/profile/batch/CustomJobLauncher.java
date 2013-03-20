package test.profile.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;


public class CustomJobLauncher extends SimpleJobLauncher {

	private static Map<String, String> jobExecutions = new HashMap<String, String>();

	@Override
	public JobExecution run(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

		
			JobExecution jobExecution = null;
		
			String status = jobExecutions.get(job.getName());

			if (status == null || !status.equals("RUNNING")) {
				jobExecutions.put(job.getName(), "RUNNING");
				try {
					try {
						jobExecution = super.run(job, jobParameters);
					} catch (JobParametersInvalidException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				} finally {
					jobExecutions.put(job.getName(), "FINISHED");
				}
				return jobExecution;

			} else {
				throw new JobExecutionAlreadyRunningException("Job '" + job.getName() + "'is running already.");
			}
		

	}
}
