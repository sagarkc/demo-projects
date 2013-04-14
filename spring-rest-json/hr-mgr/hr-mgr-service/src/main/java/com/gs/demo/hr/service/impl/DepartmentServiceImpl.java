/**
 * File :: com.gs.demo.hr.service.impl.DepartmentServiceImpl
 * Date :: Apr 15, 2013
 */
package com.gs.demo.hr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gs.demo.hr.dao.DepartmentDao;
import com.gs.demo.hr.model.comverter.DepartmentConverter;
import com.gs.demo.hr.model.entity.Department;
import com.gs.demo.hr.model.vo.DepartmentVo;
import com.gs.demo.hr.model.vo.PaginationResultVo;
import com.gs.demo.hr.model.vo.PaginationVo;
import com.gs.demo.hr.service.DepartmentService;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 
	 */
	public DepartmentServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	/* ........................ */
	
	@Override
	public List<DepartmentVo> getAllDepartments() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PaginationResultVo<DepartmentVo> getPaginatedDepartments(
			PaginationVo paginationVo) {
		if(null == paginationVo){
			return new PaginationResultVo<DepartmentVo>();
		}
		long limitFrom = (paginationVo.getPageNumber() - 1 ) * paginationVo.getResultPerPage();
		long rowCount = paginationVo.getResultPerPage() ;
		List<Department> deptList = departmentDao.getPagedDepertments(limitFrom, rowCount);
		if(null == deptList){
			deptList = new ArrayList<Department>(0);
		}
		
		List<DepartmentVo> vos = DepartmentConverter.convertToVos(deptList);
		
		PaginationResultVo<DepartmentVo> paginationResultVo = new PaginationResultVo<DepartmentVo>();
		paginationResultVo.setTotalRecords(departmentDao.getRowCount());
		paginationResultVo.setPaginationVo(paginationVo);
		paginationResultVo.setResult(vos);
		
		return paginationResultVo;
	}

	@Override
	public DepartmentVo getDepartmentById(Long id) {
		return DepartmentConverter.convertToVo(getDepartmentDao().getById(id));
	}

	@Override
	public Long saveDepartment(DepartmentVo departmentVo) {
		if(departmentVo == null)
			return null;
		Department department = DepartmentConverter.convertToModel(departmentVo);
		return getDepartmentDao().saveDepartment(department);
	}
	
	
	
}
