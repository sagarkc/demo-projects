/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.gwtproject.three.client.service.UserManagerClientImpl
 * Date     : Jun 12, 2013
 */

package com.gs.gwtproject.three.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.gs.gwtproject.three.shared.CommonEventManager;
import com.gs.gwtproject.three.shared.event.UserUpdateEvent;
import com.gs.gwtproject.three.shared.model.User;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class UserManagerClientImpl implements UserManagerClient{
    private UserManagerAsync service;
    private CommonEventManager commonEventManager 
            = CommonEventManager.getInstance();
    
    public UserManagerClientImpl() {
        service = GWT.create(UserManager.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc/userManager");
        System.out.println(GWT.getModuleBaseURL() + "rpc/userManager");
    }
    
    public void getUser(String email){
    	System.out.println("in getUser");
        service.getUser(email, new AsyncCallback<User>() {

            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(User result) {
                commonEventManager.fireEvent(new UserUpdateEvent(result));
            }
        });
    }
}
