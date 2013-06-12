/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.gwtproject.three.client.service.UserManagerAsync
 * Date     : Jun 12, 2013
 */

package com.gs.gwtproject.three.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gs.gwtproject.three.shared.model.User;
import java.util.List;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public interface UserManagerAsync {
    void getUser(String email, AsyncCallback<User> callback);
    
    void getAllUsers(AsyncCallback<List<User>> callback);
}
