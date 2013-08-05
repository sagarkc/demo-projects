package com.mercuria.etl.mgr.web.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.mercuria.etl.mgr.web.client.UIConstants;
import com.mercuria.etl.mgr.web.client.presenter.BasicLayoutPresenter.LayoutDisplay;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class BasicLayoutView extends Composite implements LayoutDisplay{

	private HeaderView headerView;
	private ActionMenubarView actionMenubarView;
	private NavigationView navigationView;
	
	// the root component
	private BaseContainerView baseContainerView;
	

	public BasicLayoutView() {
		initLayout();
	}


	private void initLayout() {
		headerView = new HeaderView();
		actionMenubarView = new ActionMenubarView();
		navigationView = new NavigationView();
		baseContainerView = BaseContainerView.getContainer();
		
		VLayout mainLayout = new VLayout();
		mainLayout.setCanSelectText(true);
		mainLayout.setStyleName("mainLayout");
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		
		HLayout headerLayout = new HLayout();
		headerLayout.setWidth100();
		headerLayout.setHeight(UIConstants.HEADER_HEIGHT);
		headerLayout.addMember(headerView);
		mainLayout.addMember(headerLayout);
		
		HLayout centerLayout = new HLayout();

		navigationView.setShowResizeBar(true);
		centerLayout.addMember(navigationView);
		
		VLayout containerLayout = new VLayout();
		containerLayout.setBorder("1px solid #a1a1a1");
		containerLayout.setLayoutMargin(2);
		//containerLayout.addMember(actionMenubarView);
		containerLayout.addMember(baseContainerView);
		centerLayout.addMember(containerLayout);
		mainLayout.addMember(centerLayout);
		
		
		
		
		HLayout southLayout =  new HLayout();
		southLayout.setWidth100();
		southLayout.setHeight(30);
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
