/**
 * File :: com.gs.examples.gwt.two.client.service.ExampleService
 * Date :: 01-May-2013
 */
package com.gs.gwtproject.three.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@RemoteServiceRelativePath("greet.rpc")
public interface ExampleService extends RemoteService{

	String sayHello(String name);
	
	int add(int a, int b);
}
