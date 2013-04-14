/**
 * File :: com.gs.demo.hr.model.entity.Department
 * Date :: Apr 13, 2013
 */
package com.gs.demo.hr.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gs.demo.hr.common.annotations.Conversion;
import com.gs.demo.hr.common.annotations.FieldMapper;
import com.gs.demo.hr.common.annotations.ResultSetColumn;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
@Entity
@Table(name="HRMGR_DEPARTMENT")
@Conversion(targetClassName="com.gs.demo.hr.model.vo.DepartmentVo")
public class Department {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPT_ID", nullable=false, unique=true)
	private Long id;
	@Column(name="DEPT_NAME", nullable=false, unique=true)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="DEPT_ID")
	private Set<Employee> employees = new HashSet<Employee>();

	@FieldMapper(targetFieldName="id")
	@ResultSetColumn(propertyName="id", mappedColumnName="DEPT_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@FieldMapper(targetFieldName="name")
	@ResultSetColumn(propertyName="name", mappedColumnName="DEPT_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof Department)) {
			return false;
		}
		Department other = (Department) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + "]";
	}

}
