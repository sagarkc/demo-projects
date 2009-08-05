package com.gs.demo.jxls.vo;

import java.io.Serializable;

public class MtgBankVo implements Serializable{

	private String bankId;
	private String employeeId;
	private String empManagerId;
	
	
	/**
	 * @return the bankId
	 */
	public String getBankId() {
		return bankId;
	}
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the empManagerId
	 */
	public String getEmpManagerId() {
		return empManagerId;
	}
	/**
	 * @param empManagerId the empManagerId to set
	 */
	public void setEmpManagerId(String empManagerId) {
		this.empManagerId = empManagerId;
	}
	
	public String toString() {
		return "{ " + bankId + ", " + employeeId + ", " + empManagerId + " }";
	}
}
