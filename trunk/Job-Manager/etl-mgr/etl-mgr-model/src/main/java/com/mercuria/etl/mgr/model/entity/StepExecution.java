/**
 * File :: com.mercuria.etl.mgr.model.entity.StepExecution
 * Date :: 20-Jul-2013
 */
package com.mercuria.etl.mgr.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sabuj Das 
 *
 */
@Entity
@Table(name = "BATCH_STEP_EXECUTION")
public class StepExecution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3580929442769086914L;

	@Id
	@Column(name="STEP_EXECUTION_ID", unique=true, nullable=false)
	private Long stepExecutionId;
	
	@Column(name="VERSION")
	private Long stepExecutionVersion;
	
	@Column(name="STEP_NAME", length=100, nullable=false)
	private String stepName;
	
	@ManyToOne
	@JoinColumn(name="JOB_EXECUTION_ID", nullable=false)
	private JobExecution jobExecution;
	
	@Column(name="START_TIME", nullable=false)
	private Timestamp startTime;
	
	@Column(name="END_TIME")
	private Timestamp endTime;
	
	@Column(name="STATUS", length=10)
	private String status;
	
	@Column(name="COMMIT_COUNT")
	private Long commitCount;
	
	//	READ_COUNT BIGINT ,
	@Column(name="READ_COUNT")
	private Long readCount;
	
	@Column(name="FILTER_COUNT")
	private Long filterCount;

	@Column(name="WRITE_COUNT")
	private Long writeCount;

	@Column(name="READ_SKIP_COUNT")
	private Long readSkipCount;

	@Column(name="WRITE_SKIP_COUNT")
	private Long writeSkipCount;

	@Column(name="PROCESS_SKIP_COUNT")
	private Long processSkipCount;

	@Column(name="ROLLBACK_COUNT")
	private Long rollbackCount;
	
	@Column(name="EXIT_CODE", length=100, nullable=true)
	private String exitCode;
	
	@Column(name="EXIT_MESSAGE", length=2500, nullable=true)
	private String exitMessage;
	
	@Column(name="LAST_UPDATED", nullable=true)
	private Timestamp lastUpdated;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "stepExecution", fetch = FetchType.LAZY)
    private StepExecutionContext stepExecutionContext;
	
	/**
	 * 
	 */
	public StepExecution() {
		// TODO Auto-generated constructor stub
	}

	public Long getStepExecutionId() {
		return stepExecutionId;
	}

	public void setStepExecutionId(Long stepExecutionId) {
		this.stepExecutionId = stepExecutionId;
	}

	public Long getStepExecutionVersion() {
		return stepExecutionVersion;
	}

	public void setStepExecutionVersion(Long stepExecutionVersion) {
		this.stepExecutionVersion = stepExecutionVersion;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
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

	public Long getCommitCount() {
		return commitCount;
	}

	public void setCommitCount(Long commitCount) {
		this.commitCount = commitCount;
	}

	public Long getReadCount() {
		return readCount;
	}

	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}

	public Long getFilterCount() {
		return filterCount;
	}

	public void setFilterCount(Long filterCount) {
		this.filterCount = filterCount;
	}

	public Long getWriteCount() {
		return writeCount;
	}

	public void setWriteCount(Long writeCount) {
		this.writeCount = writeCount;
	}

	public Long getReadSkipCount() {
		return readSkipCount;
	}

	public void setReadSkipCount(Long readSkipCount) {
		this.readSkipCount = readSkipCount;
	}

	public Long getWriteSkipCount() {
		return writeSkipCount;
	}

	public void setWriteSkipCount(Long writeSkipCount) {
		this.writeSkipCount = writeSkipCount;
	}

	public Long getProcessSkipCount() {
		return processSkipCount;
	}

	public void setProcessSkipCount(Long processSkipCount) {
		this.processSkipCount = processSkipCount;
	}

	public Long getRollbackCount() {
		return rollbackCount;
	}

	public void setRollbackCount(Long rollbackCount) {
		this.rollbackCount = rollbackCount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stepExecutionId == null) ? 0 : stepExecutionId.hashCode());
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
		if (!(obj instanceof StepExecution)) {
			return false;
		}
		StepExecution other = (StepExecution) obj;
		if (stepExecutionId == null) {
			if (other.stepExecutionId != null) {
				return false;
			}
		} else if (!stepExecutionId.equals(other.stepExecutionId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StepExecution [stepExecutionId=")
				.append(stepExecutionId).append(", stepName=").append(stepName)
				.append(", startTime=").append(startTime).append(", exitCode=")
				.append(exitCode).append("]");
		return builder.toString();
	}
	
	
}
