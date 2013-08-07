/**
 * File :: com.xchanging.etl.mgr.web.client.event.HistoricalJobMonitorEventListener
 * Date :: 24-Jul-2013
 */
package com.xchanging.etl.mgr.web.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface HistoricalJobMonitorEventListener extends EventHandler{

	void showHistoricalJobMonitorData(HistoricalJobMonitorEvent event);
	
}
