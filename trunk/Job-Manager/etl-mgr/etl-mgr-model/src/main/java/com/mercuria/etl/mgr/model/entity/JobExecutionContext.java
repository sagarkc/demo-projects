/**
 * File :: com.mercuria.etl.mgr.model.entity.JobExecutionContext
 * Date :: 20-Jul-2013
 */
package com.mercuria.etl.mgr.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Entity
@Table(name="BATCH_JOB_EXECUTION_CONTEXT")
public class JobExecutionContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3823204919586063205L;

	@Id
    @Column(name = "JOB_EXECUTION_ID")
    private Long jobExecutionId;
	
    @Basic(optional = false)
    @Column(name = "SHORT_CONTEXT")
    private String shortContext;
    
    @Lob
    @Column(name = "SERIALIZED_CONTEXT")
    private String serializedContext;
    
    @JoinColumn(name = "JOB_EXECUTION_ID", referencedColumnName = "JOB_EXECUTION_ID", 
    		insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private JobExecution jobExecution;
    
    /**
	 * 
	 */
	public JobExecutionContext() {
		// TODO Auto-generated constructor stub
	}

	public Long getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	public String getShortContext() {
		return shortContext;
	}

	public void setShortContext(String shortContext) {
		this.shortContext = shortContext;
	}

	public String getSerializedContext() {
		return serializedContext;
	}

	public void setSerializedContext(String serializedContext) {
		this.serializedContext = serializedContext;
	}

	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jobExecutionId == null) ? 0 : jobExecutionId.hashCode());
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
		if (!(obj instanceof JobExecutionContext)) {
			return false;
		}
		JobExecutionContext other = (JobExecutionContext) obj;
		if (jobExecutionId == null) {
			if (other.jobExecutionId != null) {
				return false;
			}
		} else if (!jobExecutionId.equals(other.jobExecutionId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobExecutionContext [jobExecutionId=")
				.append(jobExecutionId).append("]");
		return builder.toString();
	}
	
	
}
