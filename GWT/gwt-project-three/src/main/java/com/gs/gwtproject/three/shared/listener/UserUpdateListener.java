/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.gwtproject.three.shared.listener.UserUpdateListener
 * Date     : Jun 12, 2013
 */

package com.gs.gwtproject.three.shared.listener;

import com.google.gwt.event.shared.EventHandler;
import com.gs.gwtproject.three.shared.event.UserUpdateEvent;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public interface UserUpdateListener extends EventHandler{

    void userUpdated(UserUpdateEvent event);
    
}
