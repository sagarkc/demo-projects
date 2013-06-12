/**
 * File :: com.gs.examples.gwt.two.client.service.ExampleServiceAsync
 * Date :: 01-May-2013
 */
package com.gs.gwtproject.three.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface ExampleServiceAsync {

	void sayHello(String name, AsyncCallback<String> callback);
	
	void add(int a, int b, AsyncCallback<Integer> callback);
}
