package com.mercuria.etl.mgr.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.mercuria.etl.mgr.client.presenter.BasicLayoutPresenter.LayoutDisplay;

public class BasicLayoutView extends Composite implements LayoutDisplay{

	private HeaderView headerView;
	
	public BasicLayoutView() {
		headerView = new HeaderView();
	}
	
	@Override
	public void updateHeaderView() {
		headerView.getUserNameLabel().setText("Hello World!!!");
	}
}
