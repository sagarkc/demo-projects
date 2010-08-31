
package com.gs.rm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.AccessType;

@Embeddable
@AccessType(value="property")
public class AuditInfo implements java.io.Serializable{

	
	private Long createBy;
	
	private Date createDate;
	
	private Long updateBy;
	
	private Date updateDate;

	@Column(updatable=false)
	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	
}
