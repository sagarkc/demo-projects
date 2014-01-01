/**
 * 
 */
package com.gs.question.master.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gs.question.master.common.exception.ApplicationException;
import com.gs.question.master.model.dto.UserDto;
import com.gs.question.master.service.UserService;

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

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin(ModelMap modelMap){
		modelMap.addAttribute("hasSecurityError", false);
		return "showLoginView";
	}
	
	@RequestMapping(value="/loginfailed", method=RequestMethod.GET)
	public String loginFailed(ModelMap modelMap){
		modelMap.addAttribute("hasSecurityError", true);
		return "showLoginView";
	}
	
	@RequestMapping(value="/showRegister", method=RequestMethod.GET)
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
			userId = userService.registerUser(registrationDto);
		} catch (ApplicationException e) {
			// TODO: handle exception
		}
		if(null != userId && userId > 0){
			System.out.println("activateUser/" + userId + "/" + sessionId + ".htm");
		} else {
			//bindingResult.getAllErrors().add(new ObjectError("userId", "Could not create user"));
			return "showRegisterView";
		}
		
		
		return "showLoginView";
	}
	
	@RequestMapping(value="/activateUser/{userId}/{activationCode}.htm", method=RequestMethod.GET)
	public String activateUser(@PathVariable Long userId, @PathVariable String activationCode){
		try {
			boolean activated = userService.activateUser(userId, activationCode);
			if(activated)
				return "showLoginView";
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "userActivationErrorView";
	}
	
	@RequestMapping(value="/error/{errorCode}.htm", method=RequestMethod.GET)
	public String showErrorPage(@PathVariable int errorCode){
		return "error404View";
	}
	
}
