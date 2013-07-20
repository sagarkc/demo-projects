/**
 * File :: com.mercuria.etl.mgr.model.entity.JobInstance
 * Date :: 20-Jul-2013
 */
package com.mercuria.etl.mgr.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * {@link http://static.springsource.org/spring-batch/reference/html/metaDataSchema.html}
 * @author Sabuj Das 
 *
 */
@Entity
@Table(name="BATCH_JOB_INSTANCE",
		uniqueConstraints={@UniqueConstraint(columnNames={"JOB_NAME","JOB_KEY"})})
public class JobInstance implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -4292439219524915610L;

	@Id
	@Column(name="JOB_INSTANCE_ID", nullable=false)
	private BigInteger instanceId;
	
	@Column(name="VERSION")
	private BigInteger instanceVersion;
	
	@Column(name="JOB_NAME", length=100, nullable=false)
	private String jobName;
	
	@Column(name="JOB_KEY", length=32, nullable=false)
	private String jobKey;
	
	@OneToMany(mappedBy="jobInstance")
	private Set<JobExecution> jobExecutions;
	
	/**
	 * 
	 */
	public JobInstance() {
		jobExecutions = new HashSet<>();
	}

	public BigInteger getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(BigInteger instanceId) {
		this.instanceId = instanceId;
	}

	public BigInteger getInstanceVersion() {
		return instanceVersion;
	}

	public void setInstanceVersion(BigInteger instanceVersion) {
		this.instanceVersion = instanceVersion;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	public Set<JobExecution> getJobExecutions() {
		return jobExecutions;
	}

	public void setJobExecutions(Set<JobExecution> jobExecutions) {
		this.jobExecutions = jobExecutions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobKey == null) ? 0 : jobKey.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
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
		if (!(obj instanceof JobInstance)) {
			return false;
		}
		JobInstance other = (JobInstance) obj;
		if (jobKey == null) {
			if (other.jobKey != null) {
				return false;
			}
		} else if (!jobKey.equals(other.jobKey)) {
			return false;
		}
		if (jobName == null) {
			if (other.jobName != null) {
				return false;
			}
		} else if (!jobName.equals(other.jobName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobInstance [instanceId=").append(instanceId)
				.append(", jobName=").append(jobName).append("]");
		return builder.toString();
	}
	
	
}
