/**
 * File :: com.gs.demo.hr.model.vo.EmployeeVo
 * Date :: Apr 15, 2013
 */
package com.gs.demo.hr.model.vo;

import java.util.Date;

import net.sf.jsonizer.annotation.JsonDynamicProperty;
import net.sf.jsonizer.annotation.JsonObject;
import net.sf.jsonizer.annotation.JsonProperty;
import net.sf.jsonizer.core.Jsonizable;

import com.gs.demo.hr.common.annotations.Conversion;
import com.gs.demo.hr.common.annotations.FieldMapper;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
@Conversion(targetClassName = "com.gs.demo.hr.model.entity.Employee")
@JsonObject(dynamicProperties = { @JsonDynamicProperty(name = "links", textFormat = "<a href=\"employee/edit.htm?selectedId=%d\">%s</a>", propertyNames = {
		"prop:id", "key:lbl.employee.edit" }) })
public class EmployeeVo implements Jsonizable {

	public static final String DATE_FORMAT = "MM-dd-yyyy HH:mm:ss";

	@JsonProperty(order = 0)
	private Long id;
	@JsonProperty(order = 1)
	private String firstName;

	private String middleName;
	@JsonProperty(order = 2)
	private String lastName;
	@JsonProperty(order = 3)
	private String emailId;
	@JsonProperty(order = 4, sourceDateFormat = DATE_FORMAT, targetDateFormat = DATE_FORMAT)
	private Date dateOfBirth;
	@JsonProperty(order = 5, sourceDateFormat = DATE_FORMAT, targetDateFormat = DATE_FORMAT)
	private Date joiningDate;
	@JsonProperty(order = 6, sourceDateFormat = DATE_FORMAT, targetDateFormat = DATE_FORMAT)
	private Date releavingDate;

	@FieldMapper(targetFieldName="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@FieldMapper(targetFieldName="firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@FieldMapper(targetFieldName="middleName")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@FieldMapper(targetFieldName="lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@FieldMapper(targetFieldName="emailId")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@FieldMapper(targetFieldName="dateOfBirth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@FieldMapper(targetFieldName="joiningDate")
	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@FieldMapper(targetFieldName="releavingDate")
	public Date getReleavingDate() {
		return releavingDate;
	}

	public void setReleavingDate(Date releavingDate) {
		this.releavingDate = releavingDate;
	}

}
