/**
 * ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior
 * permission from the author.
 * ---------------------------------------------------------------------------*
 * Type : com.gs.gwtproject.three.server.UserManagerImpl Date : Jun 12, 2013
 */
package com.gs.gwtproject.three.server;

import com.gs.gwtproject.three.client.service.UserManager;
import com.gs.gwtproject.three.shared.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
@Component(value = "userManager")
public class UserManagerImpl implements UserManager {

    @Override
    public User getUser(String email) {
        User user = new User();
        user.setFirstName("Sabuj");
        user.setLastName("Das");
        user.setEmail(email);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        User user = new User();
        user.setFirstName("Sabuj");
        user.setLastName("Das");
        user.setEmail("sa@mail.com");
        List<User> l  = new ArrayList<>();
        l.add(user);
        return l;
    }
}
