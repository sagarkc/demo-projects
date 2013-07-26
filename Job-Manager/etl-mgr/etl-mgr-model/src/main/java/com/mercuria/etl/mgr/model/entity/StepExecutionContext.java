/**
 * File :: com.mercuria.etl.mgr.model.entity.StepExecutionContext
 * Date :: 20-Jul-2013
 */
package com.mercuria.etl.mgr.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sabuj Das 
 *
 */
@Entity
@Table(name = "BATCH_STEP_EXECUTION_CONTEXT")
public class StepExecutionContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3580925454578454814L;

	@Id
	@Column(name="STEP_EXECUTION_ID", unique=true, nullable=false)
	private Long stepExecutionId;
	
	@Column(name="SHORT_CONTEXT", length=2500, nullable=false)
	private String shortContext;
	
	@Column(name="SERIALIZED_CONTEXT", columnDefinition=" TEXT ")
	private String serializedContext;
	
	@JoinColumn(name = "STEP_EXECUTION_ID", referencedColumnName = "STEP_EXECUTION_ID", 
			insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private StepExecution stepExecution;
	
	/**
	 * 
	 */
	public StepExecutionContext() {
		// TODO Auto-generated constructor stub
	}

	public Long getStepExecutionId() {
		return stepExecutionId;
	}

	public void setStepExecutionId(Long stepExecutionId) {
		this.stepExecutionId = stepExecutionId;
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

	public StepExecution getStepExecution() {
		return stepExecution;
	}

	public void setStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
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
		if (!(obj instanceof StepExecutionContext)) {
			return false;
		}
		StepExecutionContext other = (StepExecutionContext) obj;
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
		builder.append("StepExecutionContext [stepExecutionId=")
				.append(stepExecutionId).append("]");
		return builder.toString();
	}
	
	
}
