/**
 * File :: com.mercuria.etl.mgr.model.entity.JobExecution
 * Date :: 20-Jul-2013
 */
package com.mercuria.etl.mgr.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * {@link http://static.springsource.org/spring-batch/reference/html/metaDataSchema.html}
 * @author Sabuj Das 
 *
 */
@Entity
@Table(name="BATCH_JOB_EXECUTION")
public class JobExecution implements Serializable{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -4593610059016194211L;

	//JOB_EXECUTION_ID
	@Id
	@Column(name="JOB_EXECUTION_ID", nullable=false)
	private Long executionId;
	
	@Column(name="VERSION")
	private Long executionVersion;
	
	@ManyToOne
	@JoinColumn(name="JOB_INSTANCE_ID", nullable=false)
	private JobInstance jobInstance;
	
	@Column(name="CREATE_TIME", nullable=false)
	private Timestamp createTime;
	
	@Column(name="START_TIME", nullable=true)
	private Timestamp startTime;
	
	@Column(name="END_TIME", nullable=true)
	private Timestamp endTime;
	
	@Column(name="STATUS", length=10, nullable=true)
	private String status;
	
	@Column(name="EXIT_CODE", length=100, nullable=true)
	private String exitCode;
	
	@Column(name="EXIT_MESSAGE", length=2500, nullable=true)
	private String exitMessage;
	
	@Column(name="LAST_UPDATED", nullable=true)
	private Timestamp lastUpdated;
	
	@OneToMany(mappedBy="jobExecution", fetch=FetchType.LAZY)
	private List<JobExecutionParameter> executionParameters;
	
	@OneToMany(mappedBy="jobExecution", fetch=FetchType.LAZY)
	private Set<StepExecution> stepExecutions;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "jobExecution", fetch = FetchType.LAZY)
    private JobExecutionContext jobExecutionContext;
	
	/**
	 * 
	 */
	public JobExecution() {
		executionParameters = new ArrayList<>();
		stepExecutions = new HashSet<>();
	}

	public Long getExecutionId() {
		return executionId;
	}

	public void setExecutionId(Long executionId) {
		this.executionId = executionId;
	}

	public Long getExecutionVersion() {
		return executionVersion;
	}

	public void setExecutionVersion(Long executionVersion) {
		this.executionVersion = executionVersion;
	}

	public JobInstance getJobInstance() {
		return jobInstance;
	}

	public void setJobInstance(JobInstance jobInstance) {
		this.jobInstance = jobInstance;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExitCode() {
		return exitCode;
	}

	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

	public String getExitMessage() {
		return exitMessage;
	}

	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<JobExecutionParameter> getExecutionParameters() {
		return executionParameters;
	}

	public void setExecutionParameters(
			List<JobExecutionParameter> executionParameters) {
		this.executionParameters = executionParameters;
	}

	public Set<StepExecution> getStepExecutions() {
		return stepExecutions;
	}

	public void setStepExecutions(Set<StepExecution> stepExecutions) {
		this.stepExecutions = stepExecutions;
	}

	public JobExecutionContext getJobExecutionContext() {
		return jobExecutionContext;
	}

	public void setJobExecutionContext(JobExecutionContext jobExecutionContext) {
		this.jobExecutionContext = jobExecutionContext;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((executionId == null) ? 0 : executionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof JobExecution)) {
			return false;
		}
		JobExecution other = (JobExecution) obj;
		if (executionId == null) {
			if (other.executionId != null) {
				return false;
			}
		} else if (!executionId.equals(other.executionId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobExecution [executionId=").append(executionId)
				.append(", startTime=").append(startTime).append(", endTime=")
				.append(endTime).append(", exitCode=").append(exitCode)
				.append("]");
		return builder.toString();
	}

	
	
	
}
