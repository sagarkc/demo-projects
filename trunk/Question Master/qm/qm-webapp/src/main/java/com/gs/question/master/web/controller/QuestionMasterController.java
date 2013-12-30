/**
 * 
 */
package com.gs.question.master.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gs.question.master.common.exception.ApplicationException;
import com.gs.question.master.model.dto.UserDto;
import com.gs.question.master.service.UserService;
import com.gs.question.master.web.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
//@SessionAttributes(value={WebConstants.SESSION_COOKIE_NAME})
public class QuestionMasterController {

	@Autowired
    private Validator defaultFormValidator;
	@Autowired
    private UserService userService;
	
	@RequestMapping(value="/index.htm", method=RequestMethod.GET)
	public String index(){
		return "homePageView";
	}

	@RequestMapping(value="/showLogin.htm", method=RequestMethod.GET)
	public String showLogin(){
		return "showLoginView";
	}
	
	@RequestMapping(value="/showRegister.htm", method=RequestMethod.GET)
	public String showRegister(ModelMap modelMap){
		modelMap.addAttribute("registrationDto", new UserDto());
		return "showRegisterView";
	}
	
	@RequestMapping(value="/register.htm", method=RequestMethod.POST)
	public String handleRegister(HttpSession httpSession, @Valid @ModelAttribute("registrationDto") UserDto registrationDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "showRegisterView";
		}
		
		String sessionId = httpSession.getId();
		registrationDto.setActivationCode(sessionId);
		Long userId = null;
		try {
			userService.registerUser(registrationDto);
		} catch (ApplicationException e) {
			// TODO: handle exception
		}
		System.out.println("activateUser/" + sessionId + ".htm");
		
		return "showLoginView";
	}
	
	@RequestMapping(value="/activateUser/{userId}/{activationCode}.htm", method=RequestMethod.GET)
	public String activateUser(@PathVariable Long userId, @PathVariable String activationCode){
		
		return "homePageView";
	}
}
