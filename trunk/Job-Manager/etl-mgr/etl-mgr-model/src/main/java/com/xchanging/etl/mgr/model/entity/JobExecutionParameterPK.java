/**
 * File :: com.xchanging.etl.mgr.model.entity.JobExecutionParameterPK
 * Date :: 20-Jul-2013
 */
package com.xchanging.etl.mgr.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Sabuj Das 
 *
 */
@Embeddable
public class JobExecutionParameterPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1077039444227881631L;
	
	@ManyToOne
	@JoinColumn(name="JOB_EXECUTION_ID", nullable=false)
	private JobExecution jobExecution;
	@Column(name="TYPE_CD", length=6, nullable=false)
	private String dataTypeCode;
	@Column(name="KEY_NAME", length=100, nullable=false)
	private String keyName;
	
}
