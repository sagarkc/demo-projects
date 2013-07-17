package com.mercuria.etl.mgr.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.mercuria.etl.mgr.client.UIConstants;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class BasicLayoutView extends Composite {

	private HeaderView headerView;
	private ActionMenubarView actionMenubarView;
	private NavigationView navigationView;
	private BaseContainerView baseContainerView;
	

	public BasicLayoutView() {
		initLayout();
	}


	private void initLayout() {
		headerView = new HeaderView();
		actionMenubarView = new ActionMenubarView();
		navigationView = new NavigationView();
		baseContainerView = new BaseContainerView();
		
		VLayout mainLayout;
		HLayout northLayout;
		HLayout southLayout;
		
		mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		northLayout = new HLayout();
		northLayout.setHeight(UIConstants.HEADER_HEIGHT
				+ UIConstants.MENUBAR_HEIGHT);

		VLayout vLayout = new VLayout();
		vLayout.addMember(headerView);
		vLayout.addMember(actionMenubarView);
		northLayout.addMember(vLayout);

		southLayout = new HLayout();

		southLayout.setMembers(navigationView, baseContainerView);

		mainLayout.addMember(northLayout);
		mainLayout.addMember(southLayout);


		initWidget(mainLayout);
	}


	public HeaderView getHeaderView() {
		return headerView;
	}

	public ActionMenubarView getActionMenubarView() {
		return actionMenubarView;
	}

	public NavigationView getNavigationView() {
		return navigationView;
	}

	public BaseContainerView getBaseContainerView() {
		return baseContainerView;
	}

	
	
}
