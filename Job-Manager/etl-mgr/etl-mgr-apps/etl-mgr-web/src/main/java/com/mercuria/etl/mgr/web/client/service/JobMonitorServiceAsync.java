/**
 * File :: com.mercuria.etl.mgr.web.client.service.JobMonitorServiceAsync
 * Date :: 24-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface JobMonitorServiceAsync {

	void loadHistoricalMonitorData(AsyncCallback<String> asyncCallback);
	
	void loadHistoricalMonitorData(List<String> jobNames, AsyncCallback<String> asyncCallback);
	
	void loadHistoricalMonitorData(List<String> jobNames, Date startTime, Date endTime, AsyncCallback<String> asyncCallback);
	
	void loadCurrentMonitorData(List<String> jobNames, AsyncCallback<String> asyncCallback);
	
}
