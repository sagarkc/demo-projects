/**
 * File :: com.gs.demo.hr.controller.DepartmentController
 * Date :: Apr 14, 2013
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gs.demo.hr.model.vo.DepartmentVo;
import com.gs.demo.hr.model.vo.PaginationResultVo;
import com.gs.demo.hr.model.vo.PaginationVo;
import com.gs.demo.hr.service.DepartmentService;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private JsonOutputMapper flexigridJsonOutputMapper;
	
	@Autowired 
	private DepartmentService departmentService;
	
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public JsonOutputMapper getFlexigridJsonOutputMapper() {
		return flexigridJsonOutputMapper;
	}

	public void setFlexigridJsonOutputMapper(
			JsonOutputMapper flexigridJsonOutputMapper) {
		this.flexigridJsonOutputMapper = flexigridJsonOutputMapper;
	}
	
	@RequestMapping( method=RequestMethod.GET)
	public String department(){
		return "dept-home-view";
	}

	@RequestMapping("/list")
	public void getDepartmentlist(HttpServletRequest request, HttpServletResponse response){
		List<DepartmentVo> allDeptlist = listAllDemoDepts();
		
		int resultPerPage 	= Integer.parseInt(request.getParameter(FlexigridConstants.REQ_PARAM_ROW_PER_PAGE));
		int pageNumber 		= Integer.parseInt(request.getParameter(FlexigridConstants.REQ_PARAM_PAGE_NUMBER));
		String sortName 	= request.getParameter(FlexigridConstants.REQ_PARAM_SORT_NAME);
		String order 		= request.getParameter(FlexigridConstants.REQ_PARAM_SORT_ORDER);
		String searchType 	= request.getParameter(FlexigridConstants.REQ_PARAM_SEARCH_TYPE);
		String searchKey	= request.getParameter(FlexigridConstants.REQ_PARAM_SEARCH_KEY);
		
		PaginationVo pagination = new PaginationVo(pageNumber, resultPerPage, sortName, order, searchType, searchKey);
		
		
		PaginationResultVo<DepartmentVo> paginationResultVo = departmentService.getPaginatedDepartments(pagination);
		
		
		response.setContentType("application/json");
		
		FlexigridJsonCollection<DepartmentVo> jsonCollection 
			= new FlexigridJsonCollection<DepartmentVo>(
					pagination.getPageNumber(), 
					paginationResultVo.getTotalRecords(), 
					paginationResultVo.getResult());
		
		try {
			response.getWriter().append(getFlexigridJsonOutputMapper().getJonOutput(jsonCollection));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private List<DepartmentVo> listAllDemoDepts() {
		List<DepartmentVo> depts = new ArrayList<DepartmentVo>();
		
		for (int i = 0; i < 20; i++) {
			DepartmentVo vo = new DepartmentVo();
			vo.setId(i+1);
			vo.setName("Dept_ " +(i+1));
			depts.add(vo);
		}
		
		return depts;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showAddDepartment(ModelMap modelMap){
		modelMap.addAttribute("currentDepartment", new DepartmentVo());
		return "dept-add-view";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String showEditDepartment(@RequestParam("selectedId") Long id, ModelMap modelMap){
		DepartmentVo departmentVo = departmentService.getDepartmentById(id);
		if(null != departmentVo)
			modelMap.addAttribute("currentDepartment", departmentVo);
		else
			modelMap.addAttribute("currentDepartment", new DepartmentVo());
		return "dept-edit-view";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteDepartment(@RequestParam("selectedId") Long id){
		departmentService.deleteDepartmentById(id);
		return "redirect:/department.htm";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveDepartment(DepartmentVo currentDepartment){
		departmentService.saveDepartment(currentDepartment);
		return "redirect:/department.htm";
	}
	
	@RequestMapping(value="/viewDetails", method=RequestMethod.GET)
	public String showViewDetails(@RequestParam("selectedId") Long id, ModelMap modelMap){
		
		return "dept-details-view";
	}
}
