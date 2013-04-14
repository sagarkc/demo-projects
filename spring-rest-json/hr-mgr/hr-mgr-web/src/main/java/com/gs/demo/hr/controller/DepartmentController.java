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
import javax.validation.Valid;

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
import com.gs.demo.hr.model.vo.PaginationVo;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private JsonOutputMapper flexigridJsonOutputMapper;
	
	
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
		
		
		int fromIndex = (resultPerPage*pageNumber) - resultPerPage;
		int toIndex = Math.min((resultPerPage*pageNumber), allDeptlist.size());
		List<DepartmentVo> list = allDeptlist.subList(fromIndex, toIndex);
		
		response.setContentType("application/json");
		
		FlexigridJsonCollection<DepartmentVo> jsonCollection = new FlexigridJsonCollection<DepartmentVo>(pageNumber, allDeptlist.size(), list);
		
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
		modelMap.addAttribute("currentDepartment", new DepartmentVo());
		return "dept-add-view";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveDepartment(@Valid DepartmentVo currentDepartment){
		
		return "redirect:/department.htm";
	}
	
}
