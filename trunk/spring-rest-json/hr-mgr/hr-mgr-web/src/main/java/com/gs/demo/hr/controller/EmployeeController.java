/**
 * File :: com.gs.demo.hr.controller.EmployeeController
 * Date :: Apr 15, 2013
 */
package com.gs.demo.hr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jquery.tags.ui.flexigrid.model.FlexigridConstants;
import net.sf.jsonizer.core.FlexigridJsonCollection;
import net.sf.jsonizer.service.JsonOutputMapper;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gs.demo.hr.model.vo.DepartmentVo;
import com.gs.demo.hr.model.vo.EmployeeVo;
import com.gs.demo.hr.model.vo.PaginationResultVo;
import com.gs.demo.hr.model.vo.PaginationVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private JsonOutputMapper flexigridJsonOutputMapper;
	
	@RequestMapping( method=RequestMethod.GET)
	public String employee(){
		return "emp-home-view";
	}

	@RequestMapping("/list")
	public void getEmployeelist(HttpServletRequest request, HttpServletResponse response){
		
		int resultPerPage 	= Integer.parseInt(request.getParameter(FlexigridConstants.REQ_PARAM_ROW_PER_PAGE));
		int pageNumber 		= Integer.parseInt(request.getParameter(FlexigridConstants.REQ_PARAM_PAGE_NUMBER));
		String sortName 	= request.getParameter(FlexigridConstants.REQ_PARAM_SORT_NAME);
		String order 		= request.getParameter(FlexigridConstants.REQ_PARAM_SORT_ORDER);
		String searchType 	= request.getParameter(FlexigridConstants.REQ_PARAM_SEARCH_TYPE);
		String searchKey	= request.getParameter(FlexigridConstants.REQ_PARAM_SEARCH_KEY);
		
		PaginationVo pagination = new PaginationVo(pageNumber, resultPerPage, sortName, order, searchType, searchKey);
		
		
		//PaginationResultVo<DepartmentVo> paginationResultVo = departmentService.getPaginatedDepartments(pagination);
		
		
		response.setContentType("application/json");
		List<EmployeeVo> l = listAllDemoEmpss();
		FlexigridJsonCollection<EmployeeVo> jsonCollection 
			= new FlexigridJsonCollection<EmployeeVo>(
					pagination.getPageNumber(), 
					l.size(), 
					l.subList(
							(int)((pagination.getPageNumber()-1) * pagination.getResultPerPage()),
							(int)(pagination.getPageNumber() * pagination.getResultPerPage())
							));
		
		try {
			response.getWriter().append(flexigridJsonOutputMapper.getJonOutput(jsonCollection));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private List<EmployeeVo> listAllDemoEmpss() {
		List<EmployeeVo> depts = new ArrayList<EmployeeVo>();
		
		for (int i = 0; i < 60; i++) {
			EmployeeVo vo = new EmployeeVo();
			vo.setId(i+1L);
			vo.setFirstName("First " +(i+1));
			vo.setLastName("Last " +(i+1));
			vo.setEmailId("Email@" +(i+1));
			depts.add(vo);
		}
		
		return depts;
	}
}
