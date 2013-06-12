/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.gwtproject.three.shared.event.UserUpdateEvent
 * Date     : Jun 12, 2013
 */

package com.gs.gwtproject.three.shared.event;

import com.google.gwt.event.shared.GwtEvent;
import com.gs.gwtproject.three.shared.listener.UserUpdateListener;
import com.gs.gwtproject.three.shared.model.User;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class UserUpdateEvent extends GwtEvent<UserUpdateListener>{

    public static final Type<UserUpdateListener> TYPE = new Type<UserUpdateListener>();
    private final User user;

    public UserUpdateEvent(User user) {
        this.user = user;
    }
    
    @Override
    public Type<UserUpdateListener> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UserUpdateListener handler) {
        handler.userUpdated(this);
    }

    public User getUser() {
        return user;
    }

}
