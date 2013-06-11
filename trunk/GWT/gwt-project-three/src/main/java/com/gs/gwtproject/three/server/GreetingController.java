/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.gwtproject.three.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sabuj
 */
@Controller
@RequestMapping("/greet")
public class GreetingController extends RemoteServiceServlet{
    
    @RequestMapping(method = RequestMethod.GET)
    public String greet(){
        return "Hello from GreetingController!!!";
    }
    
}
