package com.mercuria.etl.mgr.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.mercuria.etl.mgr.client.EtlManager;

public class HeaderView extends Composite{

	private Image logoImage;
	private Label userNameLabel;
	private Button logoutButton;
	
	
	public HeaderView() {
		
		logoImage = new Image("assets/images/system-monitor-logo-227x48.png");
		logoImage.setWidth("227");
		logoImage.setHeight("48");
		
		userNameLabel = new Label("Welcome");
		userNameLabel.setStylePrimaryName("userNameLabel");
		
		logoutButton = new Button(EtlManager.MESSAGES.sendButton());
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setWidth("100%");
		horizontalPanel.setHeight("52");
		//horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		horizontalPanel.add(logoImage);
		
		Label emptyLabel = new Label();
		emptyLabel.setWidth("100%");
		horizontalPanel.add(emptyLabel);
		
		horizontalPanel.add(userNameLabel);
		horizontalPanel.add(logoutButton);
		
		initWidget(horizontalPanel);
	}


	public Image getLogoImage() {
		return logoImage;
	}


	public Label getUserNameLabel() {
		return userNameLabel;
	}


	public Button getLogoutButton() {
		return logoutButton;
	}
	
	
}
