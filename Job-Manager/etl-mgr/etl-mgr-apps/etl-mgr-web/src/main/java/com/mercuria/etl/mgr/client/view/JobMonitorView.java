package com.mercuria.etl.mgr.client.view;

import com.mercuria.etl.mgr.client.presenter.JobMonitorPresenter.JobMonitorDisplay;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class JobMonitorView extends VLayout implements JobMonitorDisplay, ResizedHandler{

	public JobMonitorView() {
		initComponents();
	}

	private void initComponents() {
		setWidth("*");
		setHeight("*");
		
		TitleBar titleBar = new TitleBar("JOB Monitor", null);
		addMember(titleBar);
	}
	
	@Override
	public void onResized(ResizedEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
