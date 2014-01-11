/**
 * 
 */
package com.gs.question.master.model.dto;

import java.util.Date;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class QuestionBankDto {
	private Long bankId;
    private String bankName;
    private Date createdDate;
    
    /**
	 * 
	 */
	public QuestionBankDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
