package com.gs.demo.hr.service;

import java.util.List;

import com.gs.demo.hr.model.vo.EmployeeVo;
import com.gs.demo.hr.model.vo.PaginationResultVo;
import com.gs.demo.hr.model.vo.PaginationVo;

public interface EmployeeService {

	public List<EmployeeVo> getAllEmployees();
	
	public PaginationResultVo<EmployeeVo>  getPaginatedEmployees(PaginationVo paginationVo);
	
	public EmployeeVo getEmployeeById(Long id);
	
	public Long saveEmployee(EmployeeVo employeeVo);

	public void deleteEmployeeById(Long id);
}
