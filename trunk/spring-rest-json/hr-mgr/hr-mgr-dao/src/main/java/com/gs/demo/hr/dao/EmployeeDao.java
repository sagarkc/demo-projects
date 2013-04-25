package com.gs.demo.hr.dao;

import java.util.List;

import com.gs.demo.hr.model.entity.Employee;

public interface EmployeeDao {

public Long getRowCount();
	
	public Long getRowCount(String where);
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> getPagedEmployees(long limitFrom, long rowCount);
	
	public List<Employee> getPagedEmployees(long limitFrom, long rowCount, String order);
	
	public List<Employee> getPagedEmployees(long limitFrom, long rowCount, String where, String order);
	
	public Long saveEmployee(Employee employee);
	
	public Employee getById(Long id);

	public void deleteById(Long id);
	
}
