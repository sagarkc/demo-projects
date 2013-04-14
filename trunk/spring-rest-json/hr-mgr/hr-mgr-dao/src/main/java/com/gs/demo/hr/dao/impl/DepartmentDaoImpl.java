/**
 * File :: com.gs.demo.hr.dao.impl.DepartmentDaoImpl
 * Date :: Apr 14, 2013
 */
package com.gs.demo.hr.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gs.demo.hr.dao.DepartmentDao;
import com.gs.demo.hr.impl.HRCommonDaoImpl;
import com.gs.demo.hr.model.entity.Department;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Repository
public class DepartmentDaoImpl extends HRCommonDaoImpl<Department, Long> implements DepartmentDao{

	@Override
	public List<Department> getAllDepertments() {
		return getAll();
	}

	@Override
	public List<Department> getPagedDepertments(long limitFrom, long limitTo) {
		String hql = "from Department limit ";
		
		
		return null;
	}

	@Override
	public Long saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
