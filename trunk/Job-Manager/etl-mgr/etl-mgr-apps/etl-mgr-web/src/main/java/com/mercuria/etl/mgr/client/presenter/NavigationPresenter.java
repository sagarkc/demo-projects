package com.mercuria.etl.mgr.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.client.core.Display;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class NavigationPresenter extends BasePresenter<Display> {

	public NavigationPresenter(Display display) {
		super(display);
	}

	public interface NavigationDisplay extends Display{
		Button getJobMonitorButton();
	}

	@Override
	public void go(HasWidgets container) {
		
	}

	@Override
	public void bind() {
		NavigationDisplay display = (NavigationDisplay) getDisplay();
		Button button = display.getJobMonitorButton();
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
	}
	
	
}
