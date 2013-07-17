package com.mercuria.etl.mgr.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.mercuria.etl.mgr.client.UIConstants;
import com.mercuria.etl.mgr.client.presenter.NavigationPresenter.NavigationDisplay;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class NavigationView extends VLayout implements NavigationDisplay{

	private final SectionStack navigationStack = new SectionStack();   
	
	private final SectionStackSection monitorSection = new SectionStackSection();
	private final VLayout monitorSectionContent = new VLayout();
	private final SectionStackSection historySection = new SectionStackSection();
	private final VLayout historySectionContent = new VLayout();
	private final Button jobMonitorButton = new Button("Job Monitor");
	
	public NavigationView() {

		GWT.log("init Navigation Pane()...", null);

		this.setWidth(UIConstants.NAV_WEST_WIDTH);
		this.setShowResizeBar(true);

		navigationStack.setVisibilityMode(VisibilityMode.MUTEX);   
		navigationStack.setWidth100();   
		navigationStack.setHeight100();   
		navigationStack.setAutoWidth();

		
		monitorSection.setExpanded(true);
		
		monitorSectionContent.setWidth("*");   
		monitorSectionContent.setHeight("*");
		monitorSectionContent.addMember(jobMonitorButton);
		
		monitorSection.addItem(monitorSectionContent);
		navigationStack.addSection(monitorSection);
		
		
		historySection.setIcon("system-monitor-16x16.png");
		
		historySection.setExpanded(false);
		historySectionContent.setWidth("*");   
		historySectionContent.setHeight("*");
		historySectionContent.addMember(new Button("Job History"));
		historySection.addItem(historySectionContent);
		navigationStack.addSection(historySection);
		
		
		HLayout topLayout = new HLayout();
		topLayout.setWidth(getWidthAsString());
		topLayout.setHeight100();
		
		topLayout.addMember(navigationStack);
		addMember(topLayout);
		HLayout bottomLayout = new HLayout();
		bottomLayout.setWidth(getWidthAsString());
		bottomLayout.setHeight(UIConstants.HEADER_HEIGHT);
		bottomLayout.addMember(new Label("Bottom"));
		
		
		
		this.addMember(bottomLayout);
	}

	
	@Override
	public Widget asWidget() {
		return super.asWidget();
	}
	
	@Override
	public Button getJobMonitorButton() {
		return jobMonitorButton;
	}
	
}
