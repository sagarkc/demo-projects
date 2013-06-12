/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.gwtproject.three.client.service.UserManager
 * Date     : Jun 12, 2013
 */

package com.gs.gwtproject.three.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.gs.gwtproject.three.shared.model.User;
import java.util.List;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
@RemoteServiceRelativePath("rpc/userManager")
public interface UserManager extends RemoteService{

    User getUser(String email);
    
    List<User> getAllUsers();
}
