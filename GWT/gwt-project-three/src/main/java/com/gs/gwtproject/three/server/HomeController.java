/**
 * File :: com.gs.gwtproject.three.server.HomeController
 * Date :: 13-Jun-2013
 */
package com.gs.gwtproject.three.server;

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
	public String showLogin(){
		return "Login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(){
		return "redirect:/GwtProjectThree.html";
	}
	
}
