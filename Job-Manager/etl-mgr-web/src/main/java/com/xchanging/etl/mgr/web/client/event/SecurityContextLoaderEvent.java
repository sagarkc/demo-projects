/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.SecurityContextLoaderEvent
 * Date:	Aug 20, 2013  6:05:48 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.xchanging.etl.mgr.model.vo.UserVo;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class SecurityContextLoaderEvent extends GwtEvent<SecurityContextLoaderEventListener>{

	public static final Type<SecurityContextLoaderEventListener> TYPE
		= new Type<SecurityContextLoaderEventListener>();

	private final UserVo currentUser;
	
	
	/**
	 * @param currentUser
	 */
	public SecurityContextLoaderEvent(UserVo currentUser) {
		this.currentUser = currentUser;
	}


	@Override
	public Type<SecurityContextLoaderEventListener> getAssociatedType() {
		return TYPE;
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(SecurityContextLoaderEventListener handler) {
		handler.securityContextLoaded(this);
	}


	/**
	 * @return the currentUser
	 */
	public UserVo getCurrentUser() {
		return currentUser;
	}
	
	
}
