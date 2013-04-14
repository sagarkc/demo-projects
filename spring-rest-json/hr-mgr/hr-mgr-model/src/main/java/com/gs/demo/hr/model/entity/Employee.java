/**
 * File :: cm.gs.demo.hr.model.entity.Employee
 * Date :: Apr 13, 2013
 */
package com.gs.demo.hr.model.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
@Entity
@Table(name = "HRMGR_EMPLOYEE", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"EMAIL_ID", "DOB" }) })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "MIDDLE_NAME")
	private String middleName;
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL_ID", unique = true, nullable = false)
	private String emailId;
	@Column(name = "DOB", nullable = false)
	private Timestamp dateOfBirth;
	@Column(name = "JOINING_DATE")
	private Timestamp joiningDate;
	@Column(name = "RELEAVING_DATE")
	private Timestamp releavingDate;
	
	@OneToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
	private Set<Employee> supervisedEmployees = new HashSet<Employee>();

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DEPT_ID")
	private Department department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Timestamp getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Timestamp getReleavingDate() {
		return releavingDate;
	}

	public void setReleavingDate(Timestamp releavingDate) {
		this.releavingDate = releavingDate;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
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
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (emailId == null) {
			if (other.emailId != null) {
				return false;
			}
		} else if (!emailId.equals(other.emailId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth + "]";
	}

}
