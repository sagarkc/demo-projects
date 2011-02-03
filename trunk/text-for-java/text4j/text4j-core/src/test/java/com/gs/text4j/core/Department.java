package com.gs.text4j.core;

import java.io.Serializable;

import com.gs.text4j.core.annotation.CsvEntity;
import com.gs.text4j.core.annotation.CsvFile;

@CsvEntity
@CsvFile(fileName="", separator=',')
public class Department implements Serializable, Comparable<Department> {

	private Integer departmentId;
	private String departmentName;
	
	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((departmentId == null) ? 0 : departmentId.hashCode());
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
		if (departmentId == null) {
			if (other.departmentId != null) {
				return false;
			}
		} else if (!departmentId.equals(other.departmentId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName="
				+ departmentName + "]";
	}

	public int compareTo(Department o) {
		return this.departmentId.compareTo(o.departmentId);
	}

	
	
}
