/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.gwtproject.three.client.ui.UserView
 * Date     : Jun 12, 2013
 */

package com.gs.gwtproject.three.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gs.gwtproject.three.shared.CommonEventManager;
import com.gs.gwtproject.three.shared.event.UserUpdateEvent;
import com.gs.gwtproject.three.shared.listener.UserUpdateListener;
import com.gs.gwtproject.three.shared.model.User;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class UserView extends Composite implements UserUpdateListener{
    private CommonEventManager commonEventManager 
            = CommonEventManager.getInstance();
    private VerticalPanel basePanel = new VerticalPanel();
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label emailLabel;
    
    public UserView() {
        commonEventManager.addListener(UserUpdateEvent.TYPE, this);
        basePanel.setWidth("320");
        basePanel.setHeight("320");
        basePanel.setBorderWidth(1);
        initWidget(basePanel);
        
        firstNameLabel = new Label();
        lastNameLabel = new Label();
        emailLabel = new Label();
        
        HorizontalPanel hp1 = new HorizontalPanel();
        hp1.add(firstNameLabel);
        HorizontalPanel hp2 = new HorizontalPanel();
        hp2.add(lastNameLabel);
        HorizontalPanel hp3 = new HorizontalPanel();
        hp3.add(emailLabel);
        basePanel.add(hp1);
        basePanel.add(hp2);
        basePanel.add(hp3);
    }

    public void updateView(User u){
        firstNameLabel.setText(u.getFirstName());
        lastNameLabel.setText(u.getLastName());
        emailLabel.setText(u.getEmail());
    }

    @Override
    public void userUpdated(UserUpdateEvent event) {
        updateView(event.getUser());
    }
    
    
}
