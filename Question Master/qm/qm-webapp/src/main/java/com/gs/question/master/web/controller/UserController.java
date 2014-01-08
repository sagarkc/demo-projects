package com.gs.question.master.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gs.question.master.web.WebConstants;


@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String showDashboard(ModelMap model, Principal principal){
		
		String name = principal.getName();
		model.addAttribute(WebConstants.LOGGED_IN_USER_NAME, name);
		
		return "showUserDashboardView";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String showProfile(ModelMap model){
		
		return "showUserDashboardView";
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.GET)
	public String showSettings(ModelMap model){
		
		return "showUserDashboardView";
	}
	
}
