package com.mercuria.etl.mgr.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mercuria.etl.mgr.client.presenter.BasicLayoutPresenter.LayoutDisplay;

public class BasicLayoutView extends Composite implements LayoutDisplay{

	private HeaderView headerView;
	private VerticalPanel baseContainerPanel = new VerticalPanel();
	
	private DockLayoutPanel basePanel = new DockLayoutPanel(Unit.EM);
	
	public BasicLayoutView() {
		
		
		initLayout();
		
	}
	
	/**
	 * 
	 */
	private void initLayout() {
		basePanel.setWidth("100%");
		basePanel.setHeight("100%");
		
		
		
		//baseContainerPanel.setWidth("100%");
		//baseContainerPanel.setHeight("100%");
		baseContainerPanel.setBorderWidth(1);
		
		headerView = new HeaderView();
		basePanel.addNorth(headerView, 2);
		basePanel.addSouth(new HTML("footer"), 2);
		basePanel.addWest(new HTML("navigation"), 10);
		basePanel.add(baseContainerPanel);
		
		
		
		initWidget(basePanel);
	}

	@Override
	public void updateHeaderView() {
		headerView.getUserNameLabel().setText("Hello World!!!");
	}
}
