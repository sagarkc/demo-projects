package com.mercuria.etl.mgr.client.view;

import com.google.gwt.core.client.GWT;
import com.mercuria.etl.mgr.client.UIConstants;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderView extends HLayout{

	private final ToolStripButton userNameButton = new ToolStripButton();
	private final ImgButton logoutButton = new ImgButton();
	private final ImgButton etlmgrHomeButton = new ImgButton();
	
	public HeaderView() {
		GWT.log("init HeaderView()..."); 
		
		setHeight(UIConstants.HEADER_HEIGHT);  
		setDefaultLayoutAlign(Alignment.CENTER);
		
		ToolStrip topBar = new ToolStrip();
        topBar.setHeight100();
        topBar.setWidth100();
        topBar.addSpacer(6);
        
        
        etlmgrHomeButton.setSrc("system-monitor-logo-227x48.png");
        etlmgrHomeButton.setWidth(227);
        etlmgrHomeButton.setHeight(48);
        etlmgrHomeButton.setShowRollOver(false);
        etlmgrHomeButton.setShowDownIcon(false);
        etlmgrHomeButton.setShowDown(false);
        topBar.addMember(etlmgrHomeButton);
        topBar.addSpacer(6);
		
        topBar.addFill();
        
        
        userNameButton.setTitle("User Name");
        userNameButton.setIcon("system-monitor-16x16.png");
        topBar.addButton(userNameButton);

        topBar.addSeparator();
        
        
        logoutButton.setWidth(24);
        logoutButton.setHeight(24);
        logoutButton.setSrc("system-monitor-16x16.png");
        logoutButton.setShowFocused(false);
        logoutButton.setShowFocusedIcon(false);
        logoutButton.setPrompt("Logout");
        logoutButton.setHoverWidth(110);
        logoutButton.setHoverStyle("interactImageHover");


        topBar.addMember(logoutButton);

        topBar.addSpacer(6);

        
		addMember(topBar);
	}


	public ImgButton getLogoutButton() {
		return logoutButton;
	}

	public ImgButton getEtlMgrHomeButton() {
		return etlmgrHomeButton;
	}

	public ToolStripButton getUserNameButton() {
		return userNameButton;
	}

	

	
}
