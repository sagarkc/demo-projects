package com.xchanging.etl.mgr.web.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sabuj Das | sabujdas@xchanging.com
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(ModelMap modelMap){
		modelMap.addAttribute("hasSecurityError", false);
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, ModelMap modelMap){
		request.getSession().invalidate();
		modelMap.addAttribute("hasSecurityError", false);
		return "login";
	}
	
	@RequestMapping(value="/loginfailed", method=RequestMethod.GET)
	public String loginFailed(ModelMap modelMap){
		modelMap.addAttribute("hasSecurityError", true);
		return "login";
	}
	
	@RequestMapping(value="/accessDenied", method=RequestMethod.GET)
	public String showAccessDenied(){
		return "redirect:/pages/403.jsp";
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String showPtmMainApp(HttpServletRequest request){
		return "EtlManager";
	}
}