/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.client.view.NavigationView
 * Date:	Aug 5, 2013  6:25:26 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.client.view;

import com.mercuria.etl.mgr.web.client.UIConstants;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
public class NavigationView extends VLayout implements ResizedHandler {
	private final SectionStack navigationStack = new SectionStack();   
	
	private final SectionStackSection monitorSection = new SectionStackSection();
	private final VLayout monitorSectionContent = new VLayout();
	private final SectionStackSection historySection = new SectionStackSection();
	private final VLayout historySectionContent = new VLayout();
	private final Button jobHistoryMonitorButton = new Button("Historical Data");
	
	public NavigationView() {

		super();
		addResizedHandler(this);
		setWidth(UIConstants.NAV_WEST_WIDTH);
		setHeight100();
		this.setMembersMargin(20);
		this.setOverflow(Overflow.HIDDEN);
		this.setShowResizeBar(true);
		
		jobHistoryMonitorButton.setStyleName("navigationButton");
		jobHistoryMonitorButton.setWidth100();
		jobHistoryMonitorButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				BaseContainerView.getContainer().setView(new HistoryJobMonitorView());
			}
		});

		navigationStack.setVisibilityMode(VisibilityMode.MUTEX);   
		navigationStack.setWidth("227");   
		navigationStack.setHeight100();   
		navigationStack.setAutoWidth();
		navigationStack.setOverflow(Overflow.HIDDEN);
		navigationStack.setStyleName("navigationAccordianTitle");

		monitorSection.setTitle("Historical Data");
		monitorSection.setExpanded(true);
		//monitorSection.setItems(jobMonitorButton);
		monitorSectionContent.setWidth100();   
		monitorSectionContent.setHeight("*");
		
		
		monitorSectionContent.addMember(jobHistoryMonitorButton);
		
		monitorSection.addItem(monitorSectionContent);
		navigationStack.addSection(monitorSection);
		
		historySection.setTitle("History");
		historySection.setExpanded(false);
		
		historySectionContent.setWidth100();   
		historySectionContent.setHeight("*");
		historySectionContent.addMember(new Button("Job History"));
		historySection.addItem(historySectionContent);
		navigationStack.addSection(historySection);
		
		
		HLayout topLayout = new HLayout();
		topLayout.setWidth(200);
		topLayout.setHeight100();
		
		//topLayout.addMember(navigationStack);
		addMember(navigationStack);
		HLayout bottomLayout = new HLayout();
		bottomLayout.setWidth(200);
		bottomLayout.setHeight(UIConstants.HEADER_HEIGHT);
		bottomLayout.addMember(new Label("Bottom"));
		
		
		
		this.addMember(bottomLayout);

	}
	
	public void onResized(ResizedEvent event) {
		if(this.getWidth() > UIConstants.NAV_WEST_WIDTH)
			navigationStack.setWidth(this.getWidth());
		else 
			navigationStack.setWidth(UIConstants.NAV_WEST_WIDTH);
		BaseContainerView.getContainer().redraw();
	}

}
