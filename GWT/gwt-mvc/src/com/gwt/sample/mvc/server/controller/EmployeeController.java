package com.gwt.sample.mvc.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwt.sample.mvc.server.model.Employee;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private List<Employee> list = new ArrayList<Employee>();
	
	public EmployeeController() {
		list.add(new Employee(1, "Emp 01", 1001));
		list.add(new Employee(2, "Emp 02", 1002));
		list.add(new Employee(3, "Emp 03", 1003));
		list.add(new Employee(4, "Emp 04", 1004));
		list.add(new Employee(5, "Emp 05", 1005));
		list.add(new Employee(6, "Emp 06", 1006));
		list.add(new Employee(7, "Emp 07", 1007));
	}
	
	@RequestMapping(value = "getAll", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Employee> getAll() {
		List<Employee> list = new ArrayList<Employee>();

		list.add(new Employee(1, "Emp 01", 1001));
		list.add(new Employee(2, "Emp 02", 1002));
		list.add(new Employee(3, "Emp 03", 1003));
		list.add(new Employee(4, "Emp 04", 1004));
		list.add(new Employee(5, "Emp 05", 1005));
		list.add(new Employee(6, "Emp 06", 1006));
		list.add(new Employee(7, "Emp 07", 1007));

		return list;
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Employee> getAllGet() {
		List<Employee> list = new ArrayList<Employee>();

		list.add(new Employee(1, "Emp 01", 1001));
		list.add(new Employee(2, "Emp 02", 1002));
		list.add(new Employee(3, "Emp 03", 1003));
		list.add(new Employee(4, "Emp 04", 1004));
		list.add(new Employee(5, "Emp 05", 1005));
		list.add(new Employee(6, "Emp 06", 1006));
		list.add(new Employee(7, "Emp 07", 1007));

		return list;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getAllEmployees() {
		return list;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	Employee updateEmployee(@ModelAttribute Employee employee) {
		employee.setName("Updated _ " + employee.getId());
		return employee;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Employee createEmployee() {
		return new Employee(8, "Emp 08", 1008);
	}

	@RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Employee deleteEmployee(@PathVariable("id") Integer id) {
		Employee employee = null;
		for (Employee e : list) {
			if(id.equals(e.getId())){
				employee = e; 
				list.remove(e);
				break;
			}
		}
		return employee;
	}
}
