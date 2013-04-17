/**
 * File :: com.gs.demo.hr.service.DepartmentService
 * Date :: Apr 15, 2013
 */
package com.gs.demo.hr.service;

import java.util.List;

import com.gs.demo.hr.model.vo.DepartmentVo;
import com.gs.demo.hr.model.vo.PaginationResultVo;
import com.gs.demo.hr.model.vo.PaginationVo;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface DepartmentService {

	public List<DepartmentVo> getAllDepartments();
	
	public PaginationResultVo<DepartmentVo>  getPaginatedDepartments(PaginationVo paginationVo);
	
	public DepartmentVo getDepartmentById(Long id);
	
	public Long saveDepartment(DepartmentVo departmentVo);

	public void deleteDepartmentById(Long id);
	
}
