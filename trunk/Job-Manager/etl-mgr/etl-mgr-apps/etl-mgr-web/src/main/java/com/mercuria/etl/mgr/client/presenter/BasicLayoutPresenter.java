package com.mercuria.etl.mgr.client.presenter;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.view.ActionMenubarView;
import com.mercuria.etl.mgr.client.view.BaseContainerView;
import com.mercuria.etl.mgr.client.view.BasicLayoutView;
import com.mercuria.etl.mgr.client.view.HeaderView;
import com.mercuria.etl.mgr.client.view.NavigationView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class BasicLayoutPresenter extends BasePresenter<Display>{

	private final BasicLayoutView basicLayoutView;
	private NavigationPresenter navigationPresenter;
	
	public interface LayoutDisplay extends Display{
		HeaderView getHeaderView();
		ActionMenubarView getActionMenubarView();
		NavigationView getNavigationView();
		BaseContainerView getBaseContainerView();
	}
	
	public BasicLayoutPresenter(Display display) {
		super(display);
		basicLayoutView = (BasicLayoutView) display;
		navigationPresenter = new NavigationPresenter(basicLayoutView.getNavigationView());
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(getDisplay().asWidget());
	}

	@Override
	public void bind() {
		basicLayoutView.getHeaderView().getLogoutButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "http://localhost:8080/etlmgr/logout.htm");
				try {
		            Request response = builder.sendRequest(null, new RequestCallback() {
		                public void onError(Request request, Throwable exception) {
		                    com.google.gwt.user.client.Window.alert("Error in logout");
		                }

		                public void onResponseReceived(Request request, Response response) {
		                	//com.google.gwt.user.client.Window.Location.replace("http://localhost:8080/etlmgr");
		                	com.google.gwt.user.client.Window.Location.reload();
		                }
		            });

		        } catch (RequestException e) {
		            // Code omitted for clarity
		        }
			}
		});
		
		basicLayoutView.getHeaderView().getEtlMgrHomeButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                com.google.gwt.user.client.Window.open("http://localhost:8080/etlmgr", "ETL Manager", null);
            }
        });
		
		
		basicLayoutView.getNavigationView().getJobMonitorButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
	}

	public BasicLayoutView getBasicLayoutView() {
		return basicLayoutView;
	}

	
	
}
