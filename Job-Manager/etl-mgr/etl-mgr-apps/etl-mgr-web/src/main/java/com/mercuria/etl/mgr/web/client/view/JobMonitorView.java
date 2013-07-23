package com.mercuria.etl.mgr.web.client.view;

import com.mercuria.etl.mgr.web.client.presenter.JobMonitorPresenter.JobMonitorDisplay;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class JobMonitorView extends VLayout implements JobMonitorDisplay, ResizedHandler{

	private final TabSet monitorTabSet = new TabSet();
	private final RealTimeJobMonitorView realTimeJobMonitorView = new RealTimeJobMonitorView();
	private final HistoryJobMonitorView historyJobMonitorView = new HistoryJobMonitorView();
	
	public JobMonitorView() {
		initComponents();
	}

	private void initComponents() {
		setWidth100();
		setHeight100();
		
		TitleBar titleBar = new TitleBar("JOB Monitor", null);
		addMember(titleBar);
		
		
		Layout paneContainerProperties = new Layout();
        paneContainerProperties.setLayoutMargin(0);
        paneContainerProperties.setLayoutTopMargin(1);
        monitorTabSet.setPaneContainerProperties(paneContainerProperties);
        monitorTabSet.setWidth100();
        monitorTabSet.setHeight100();
        
        Tab realtimetab = new Tab();
        realtimetab.setTitle("Real Time&nbsp;&nbsp;");
        realtimetab.setWidth(80);
        realtimetab.setPane(realTimeJobMonitorView);
        
        monitorTabSet.addTab(realtimetab);
        
        Tab historyTab = new Tab();
        historyTab.setTitle("History&nbsp;&nbsp;");
        historyTab.setWidth(80);
        historyTab.setPane(historyJobMonitorView);
        
        monitorTabSet.addTab(historyTab);
        
        addMember(monitorTabSet);
	}
	
	@Override
	public void onResized(ResizedEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
