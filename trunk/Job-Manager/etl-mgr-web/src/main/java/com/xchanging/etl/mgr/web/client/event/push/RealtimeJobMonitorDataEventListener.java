/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEventListener
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
public abstract class RealtimeJobMonitorDataEventListener implements RemoteEventListener, IsSerializable{

	@Override
	public void apply(Event anEvent) {
		if(null != anEvent
				&& anEvent instanceof RealtimeJobMonitorDataEvent){
			RealtimeJobMonitorDataEvent event = (RealtimeJobMonitorDataEvent) anEvent;
			onDataPush(event);
		}
	}
	
	public abstract void onDataPush(RealtimeJobMonitorDataEvent event);
	
}
