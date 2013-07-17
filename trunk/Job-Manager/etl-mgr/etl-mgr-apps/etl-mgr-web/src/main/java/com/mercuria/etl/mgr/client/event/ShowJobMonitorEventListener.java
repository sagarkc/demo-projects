package com.mercuria.etl.mgr.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ShowJobMonitorEventListener extends EventHandler{

	void showJobMonitor(ShowJobMonitorEvent event);
}
