/**
 * 
 */
package com.gs.question.master.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
public class QuestionMasterController {

	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "home";
	}
	
}
