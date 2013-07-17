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
	
	
	public HeaderView() {
		GWT.log("init HeaderView()..."); 
		
		setHeight(UIConstants.HEADER_HEIGHT);  
		setDefaultLayoutAlign(Alignment.CENTER);
		
		ToolStrip topBar = new ToolStrip();
        topBar.setHeight100();
        topBar.setWidth100();
        topBar.addSpacer(6);
        
        ImgButton sgwtHomeButton = new ImgButton();
        sgwtHomeButton.setSrc("system-monitor-logo-227x48.png");
        sgwtHomeButton.setWidth(227);
        sgwtHomeButton.setHeight(48);
        sgwtHomeButton.setShowRollOver(false);
        sgwtHomeButton.setShowDownIcon(false);
        sgwtHomeButton.setShowDown(false);
        sgwtHomeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                com.google.gwt.user.client.Window.open("http://localhost:8080/etlmgr", "ETL Manager", null);
            }
        });
        topBar.addMember(sgwtHomeButton);
        topBar.addSpacer(6);
		
        topBar.addFill();
        
        
        userNameButton.setTitle("User Name");
        userNameButton.setIcon("system-monitor-16x16.png");
        topBar.addButton(userNameButton);

        topBar.addSeparator();
        
        ImgButton imgButton = new ImgButton();
        imgButton.setWidth(24);
        imgButton.setHeight(24);
        imgButton.setSrc("system-monitor-16x16.png");
        imgButton.setShowFocused(false);
        imgButton.setShowFocusedIcon(false);
        imgButton.setPrompt("Logout");
        imgButton.setHoverWidth(110);
        imgButton.setHoverStyle("interactImageHover");


        topBar.addMember(imgButton);

        topBar.addSpacer(6);

        
		addMember(topBar);
	}


	public ToolStripButton getUserNameButton() {
		return userNameButton;
	}

	

	
}
