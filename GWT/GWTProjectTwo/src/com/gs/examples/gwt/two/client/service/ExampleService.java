/**
 * File :: com.gs.examples.gwt.two.client.service.ExampleService
 * Date :: 01-May-2013
 */
package com.gs.examples.gwt.two.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@RemoteServiceRelativePath("exampleservice")
public interface ExampleService extends RemoteService{

	String sayHello(String name);
	
	int add(int a, int b);
}
