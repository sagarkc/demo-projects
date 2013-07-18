package com.mercuria.etl.mgr.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.mercuria.etl.mgr.client.UIConstants;
import com.mercuria.etl.mgr.client.presenter.BasicLayoutPresenter.LayoutDisplay;
import com.smartgwt.client.widgets.Canvas;
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
		
		VLayout main = new VLayout() {
            {
                setID("isc_EtlManager_1_0");
            }

        };
		
        main.addMember(headerView);
        main.setWidth100();
        main.setHeight100();
        main.setBackgroundColor("#10ff20");
        
        HLayout hLayout = new HLayout();
        hLayout.setLayoutMargin(5);
        hLayout.setWidth100();
        hLayout.setHeight100();
        hLayout.setBackgroundColor("#1020ff");
        
        
        VLayout sideNavLayout = new VLayout();
        sideNavLayout.setHeight100();
        sideNavLayout.setWidth(185);
        sideNavLayout.setShowResizeBar(true);
        sideNavLayout.setBackgroundColor("#176767");
        sideNavLayout.addMember(navigationView);
        
        hLayout.addMember(sideNavLayout);
        
        hLayout.addMember(baseContainerView);
        
        main.addMember(hLayout);
        
        main.draw();
        
        
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

		initWidget(main);
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
