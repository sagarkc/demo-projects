/**
 * File :: com.gs.examples.gwt.two.client.service.ExampleServiceClientImpl
 * Date :: 01-May-2013
 */
package com.gs.gwtproject.three.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.gs.gwtproject.three.shared.CommonEventManager;
import com.gs.gwtproject.three.shared.event.MessageUpdatedEvent;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class ExampleServiceClientImpl implements ExampleServiceClient{

	private ExampleServiceAsync service;
	/**
	 * 
	 */
	public ExampleServiceClientImpl(String url) {
		System.out.println(url);
		service = GWT.create(ExampleService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(url);
	}
	

	@Override
	public void sayHello(String name) {
		service.sayHello(name, new DefaultCallback<String>());
	}

	@Override
	public void add(int a, int b) {
		service.add(a, b, new DefaultCallback<Integer>());
	}

	
	private class DefaultCallback<T> implements AsyncCallback<T>{

		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error occured");
		}

		@Override
		public void onSuccess(T result) {
			System.out.println("Success...");
            MessageUpdatedEvent evt = new MessageUpdatedEvent("Hello....");
            CommonEventManager.getInstance().fireEvent(evt);
		}
		
	}
}
