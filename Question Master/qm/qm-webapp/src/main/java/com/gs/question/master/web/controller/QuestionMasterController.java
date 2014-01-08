package com.gs.question.master.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
@RequestMapping("/qm")
public class QuestionMasterController {

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String showUserDashBoard(){
		return "showUserDashboardView";
	}
	
}
