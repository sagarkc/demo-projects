/**
 * File :: com.gs.demo.hr.dao.DepartmentDao
 * Date :: Apr 14, 2013
 */
package com.gs.demo.hr.dao;

import java.util.List;

import com.gs.demo.hr.model.entity.Department;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface DepartmentDao {

	public Long getRowCount();
	
	public Long getRowCount(String where);
	
	public List<Department> getAllDepertments();
	
	public List<Department> getPagedDepertments(long limitFrom, long rowCount);
	
	public Long saveDepartment(Department department);
	
	public Department getById(Long id);
}
