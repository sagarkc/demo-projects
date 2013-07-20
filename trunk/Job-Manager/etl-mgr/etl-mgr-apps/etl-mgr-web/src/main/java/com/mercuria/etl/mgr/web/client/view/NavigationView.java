package com.mercuria.etl.mgr.web.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.mercuria.etl.mgr.web.client.UIConstants;
import com.mercuria.etl.mgr.web.client.presenter.NavigationPresenter.NavigationDisplay;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class NavigationView extends VLayout implements ResizedHandler, NavigationDisplay{

	private final SectionStack navigationStack = new SectionStack();   
	
	private final SectionStackSection monitorSection = new SectionStackSection();
	private final VLayout monitorSectionContent = new VLayout();
	private final SectionStackSection historySection = new SectionStackSection();
	private final VLayout historySectionContent = new VLayout();
	private final Button jobMonitorButton = new Button("Job Monitor");
	
	public NavigationView() {
		addResizedHandler(this);
		GWT.log("init Navigation View()...");

		this.setWidth(UIConstants.NAV_WEST_WIDTH);

		navigationStack.setVisibilityMode(VisibilityMode.MUTEX);   
		navigationStack.setWidth("227");   
		navigationStack.setHeight100();   
		navigationStack.setAutoWidth();

		
		monitorSection.setExpanded(true);
		
		monitorSectionContent.setWidth("*");   
		monitorSectionContent.setHeight("*");
		monitorSectionContent.addMember(jobMonitorButton);
		
		monitorSection.addItem(monitorSectionContent);
		navigationStack.addSection(monitorSection);
		
		
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
	public void onResized(ResizedEvent event) {
		if(this.getWidth() > UIConstants.NAV_WEST_WIDTH)
			navigationStack.setWidth(this.getWidth());
		else 
			navigationStack.setWidth(UIConstants.NAV_WEST_WIDTH);
		BaseContainerView.getContainer().redraw();
	}


	public SectionStack getNavigationStack() {
		return navigationStack;
	}


	public SectionStackSection getMonitorSection() {
		return monitorSection;
	}


	public VLayout getMonitorSectionContent() {
		return monitorSectionContent;
	}


	public SectionStackSection getHistorySection() {
		return historySection;
	}


	public VLayout getHistorySectionContent() {
		return historySectionContent;
	}


	public Button getJobMonitorButton() {
		return jobMonitorButton;
	}
	
	
}
