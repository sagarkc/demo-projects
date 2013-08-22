/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorEventListener
 * Date:	Aug 21, 2013  1:18:51 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event.push;

import com.google.gwt.user.client.rpc.IsSerializable;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.listener.RemoteEventListener;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public abstract class RealtimeJobMonitorEventListener implements RemoteEventListener, IsSerializable{

	@Override
	public void apply(Event anEvent) {
		if(null == anEvent)
			return;
		if(anEvent instanceof RealtimeJobMonitorDataEvent){
			RealtimeJobMonitorDataEvent event = (RealtimeJobMonitorDataEvent) anEvent;
			onJobMonitorDataPushed(event);
		}
		else if(anEvent instanceof RealtimeJobMonitorSummaryEvent){
			RealtimeJobMonitorSummaryEvent event = (RealtimeJobMonitorSummaryEvent) anEvent;
			onJobMonitorSummaryPushed(event);
		}
	}
	
	public abstract void onJobMonitorDataPushed(RealtimeJobMonitorDataEvent event);
	
	public abstract void onJobMonitorSummaryPushed(RealtimeJobMonitorSummaryEvent event);
	
}
