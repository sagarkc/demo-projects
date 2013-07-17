package com.mercuria.etl.mgr.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mercuria.etl.mgr.client.presenter.BasicLayoutPresenter.LayoutDisplay;

public class BasicLayoutView extends Composite implements LayoutDisplay{

	private VerticalPanel basePanel = new VerticalPanel();
	private HeaderView headerView;
	
	public BasicLayoutView() {
		
		
		initLayout();
		
	}
	
	/**
	 * 
	 */
	private void initLayout() {
		basePanel.setWidth("100%");
		basePanel.setHeight("100%");
		
		headerView = new HeaderView();
		basePanel.add(headerView);
		
		initWidget(basePanel);
	}

	@Override
	public void updateHeaderView() {
		headerView.getUserNameLabel().setText("Hello World!!!");
	}
}
