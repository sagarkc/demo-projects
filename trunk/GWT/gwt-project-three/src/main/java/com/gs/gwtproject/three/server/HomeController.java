/**
 * File :: com.gs.gwtproject.three.server.HomeController
 * Date :: 13-Jun-2013
 */
package com.gs.gwtproject.three.server;

import javax.servlet.http.HttpServletRequest;
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
	public String doLogin(HttpServletRequest request){
            if(null != request){
                request.getSession().invalidate();
                request.getSession(true);
            }
            
            return "GwtProjectThree";
	}
        
        @RequestMapping(value="/home", method=RequestMethod.GET)
        public String showHomePage(){
            return "GwtProjectThree";
        }
	
}
