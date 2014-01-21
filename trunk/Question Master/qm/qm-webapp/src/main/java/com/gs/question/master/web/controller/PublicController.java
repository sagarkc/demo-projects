/**
 * 
 */
package com.gs.question.master.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
@RequestMapping("/public")
public class PublicController {

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String showPublicHomePage(ModelMap modelMap, HttpServletRequest request){
		
		
		return "publicHomePageView";
	}
	
}
