package com.mercuria.etl.mgr.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
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
		//basePanel.setWidth("100%");
		//basePanel.setHeight("100%");
		
		SplitLayoutPanel p = new SplitLayoutPanel();
		p.addWest(new HTML("navigation"), 128);
		p.add(new HTML("details"));
		
		basePanel.addNorth(new HTML("header"), 2);
		basePanel.addSouth(new HTML("footer"), 2);
		basePanel.add(p);
		
		
		
		initWidget(basePanel);
	}

	@Override
	public void updateHeaderView() {
		headerView.getUserNameLabel().setText("Hello World!!!");
	}
}
