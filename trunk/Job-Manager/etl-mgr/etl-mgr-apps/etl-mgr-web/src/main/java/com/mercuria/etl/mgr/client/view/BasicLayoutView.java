package com.mercuria.etl.mgr.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.mercuria.etl.mgr.client.UIConstants;
import com.mercuria.etl.mgr.client.presenter.BasicLayoutPresenter.LayoutDisplay;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
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
		mainLayout.setBackgroundColor("black");
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		
		HLayout headerLayout = new HLayout();
		headerLayout.setWidth100();
		headerLayout.setHeight(UIConstants.HEADER_HEIGHT);
		headerLayout.setBackgroundColor("blue");
		headerLayout.addMember(headerView);
		mainLayout.addMember(headerLayout);
		
		HLayout centerLayout = new HLayout();
		centerLayout.setBackgroundColor("#507B14");
		
		VLayout sideNavLayout = new VLayout();
		sideNavLayout.setLayoutMargin(5);
		sideNavLayout.setBackgroundColor("#176767");
		sideNavLayout.setWidth(250);
		sideNavLayout.setShowResizeBar(true);
		sideNavLayout.addMember(new Label("Side Bar"));
		centerLayout.addMember(sideNavLayout);
		
		VLayout containerLayout = new VLayout();
		containerLayout.setBorder("1px solid #a1a1a1");
		containerLayout.setLayoutMargin(5);
		containerLayout.setBackgroundColor("white");
		containerLayout.addMember(new Label("Main Container"));
		centerLayout.addMember(containerLayout);
		mainLayout.addMember(centerLayout);
		
		
		
		
		HLayout southLayout =  new HLayout();
		southLayout.setWidth100();
		southLayout.setHeight(30);
		southLayout.setBackgroundColor("black");
		mainLayout.addMember(southLayout);
		
		
        
        
		/*VLayout mainLayout;
		HLayout northLayout;
		HLayout southLayout;
		HLayout centerLayout;
		
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

		centerLayout = new HLayout();
		
		Canvas canvas = new Canvas();
        canvas.setBackgroundImage("[SKIN]/shared/background.gif");
        canvas.setWidth100();
        canvas.setHeight100();
        canvas.addChild(baseContainerView);
		
		centerLayout.addMember(navigationView);
		centerLayout.addMember(baseContainerView);

		southLayout = new HLayout();
		southLayout.setHeight(UIConstants.FOOTER_HEIGHT);
		southLayout.setBackgroundColor("#333");
		
		mainLayout.addMember(northLayout);
		mainLayout.addMember(centerLayout);
		mainLayout.addMember(southLayout);*/

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
