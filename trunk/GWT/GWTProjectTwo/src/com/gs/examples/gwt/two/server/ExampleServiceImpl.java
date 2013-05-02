/**
 * File :: com.gs.examples.gwt.two.server.ExampleServiceImpl
 * Date :: 01-May-2013
 */
package com.gs.examples.gwt.two.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gs.examples.gwt.two.client.service.ExampleService;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class ExampleServiceImpl extends RemoteServiceServlet implements ExampleService{

	
	
	@Override
	public String sayHello(String name) {
		return "Hello " + name + " !";
	}

	@Override
	public int add(int a, int b) {
		return (a + b);
	}

}
