/**
 * File :: com.mercuria.etl.mgr.model.entity.JobExecutionParameter
 * Date :: 20-Jul-2013
 */
package com.mercuria.etl.mgr.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@link http://static.springsource.org/spring-batch/reference/html/metaDataSchema.html}
 * 
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Entity
@Table(name="BATCH_JOB_EXECUTION_PARAMS")
public class JobExecutionParameter implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5450793243119716099L;

	@Id
	@Embedded
	private JobExecutionParameterPK id;
	@ManyToOne
	@JoinColumn(name="JOB_EXECUTION_ID", nullable=false, insertable=false, updatable=false)
	private JobExecution jobExecution;
	@Column(name="TYPE_CD", length=6, nullable=false, insertable=false, updatable=false)
	private String dataTypeCode;
	@Column(name="KEY_NAME", length=100, nullable=false, insertable=false, updatable=false)
	private String keyName;
	@Column(name="STRING_VAL", length=250, nullable=true)
	private String stringValue;
	@Column(name="DATE_VAL")
	private Timestamp dateValue;
	@Column(name="LONG_VAL")
	private Long longValue;
	@Column(name="DOUBLE_VAL")
	private Double doubleValue;
	@Column(name="IDENTIFYING", length=1, nullable=false)
	private Character identifying;
	
	
	/**
	 * 
	 */
	public JobExecutionParameter() {
		// TODO Auto-generated constructor stub
	}


	public JobExecutionParameterPK getId() {
		return id;
	}


	public void setId(JobExecutionParameterPK id) {
		this.id = id;
	}


	public JobExecution getJobExecution() {
		return jobExecution;
	}


	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}


	public String getDataTypeCode() {
		return dataTypeCode;
	}


	public void setDataTypeCode(String dataTypeCode) {
		this.dataTypeCode = dataTypeCode;
	}


	public String getKeyName() {
		return keyName;
	}


	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}


	public String getStringValue() {
		return stringValue;
	}


	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}


	public Timestamp getDateValue() {
		return dateValue;
	}


	public void setDateValue(Timestamp dateValue) {
		this.dateValue = dateValue;
	}


	public Long getLongValue() {
		return longValue;
	}


	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}


	public Double getDoubleValue() {
		return doubleValue;
	}


	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}


	public Character getIdentifying() {
		return identifying;
	}


	public void setIdentifying(Character identifying) {
		this.identifying = identifying;
	}
	
	
}
