package com.gs.question.master.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import com.gs.question.master.web.WebConstants;


@Controller
@RequestMapping("/user")
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes({WebConstants.LOGGED_IN_USER_NAME})
public class UserController {

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String showDashboard(ModelMap model, Principal principal, HttpServletRequest request){
		
		String name = principal.getName();
		model.addAttribute(WebConstants.LOGGED_IN_USER_NAME, name);
		request.getSession().setAttribute(WebConstants.LOGGED_IN_USER_NAME, principal.getName());
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