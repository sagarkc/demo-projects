/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.gwtproject.three.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gs.gwtproject.three.client.service.ExampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sabuj
 */
@Controller
@RequestMapping("/greet")
public class GreetingController extends RemoteServiceServlet implements ExampleService{
    
    
    @Override
    @RequestMapping(method = RequestMethod.GET)
	public String sayHello(String name) {
		return "Hello " + name + " from GreetingController!";
	}

	@Override
	public int add(int a, int b) {
		return (a + b);
	}
}
