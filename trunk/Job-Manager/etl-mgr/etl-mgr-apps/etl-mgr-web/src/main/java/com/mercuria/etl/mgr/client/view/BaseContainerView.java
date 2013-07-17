package com.mercuria.etl.mgr.client.view;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.layout.VLayout;

public class BaseContainerView extends VLayout {

	  public BaseContainerView() {
	    super();
						
	    GWT.log("init Context Area()...", null);
					
	    this.setWidth("*"); 
	    this.setBackgroundColor("#EEEEEE");  
		
	  }	

}
