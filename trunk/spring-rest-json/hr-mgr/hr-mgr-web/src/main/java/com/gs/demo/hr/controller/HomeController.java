/**
 * File :: com.gs.demo.hr.controller.HomeController
 * Date :: Apr 14, 2013
 */
package com.gs.demo.hr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "home-page-view";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(){
		return "search-page-view";
	}
	
	@RequestMapping(value="/employee", method=RequestMethod.GET)
	public String employee(){
		return "emp-home-view";
	}
	
	
	
}
