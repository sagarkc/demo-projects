package com.mercuria.etl.mgr.web.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.mercuria.etl.mgr.web.client.i18n.ApplicationMessages;
import com.mercuria.etl.mgr.web.client.view.BasicLayoutView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EtlManager implements EntryPoint {

	private static Logger logger = Logger.getLogger(EtlManager.class.getName());
	
	public static final ApplicationMessages MESSAGES = GWT
			.create(ApplicationMessages.class);

	private final BasicLayoutView basicLayoutView = new BasicLayoutView();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		logger.info("In EtlManager.onModuleLoad()");
		// get rid of scroll bars, and clear out the window's built-in margin,
	    // because we want to take advantage of the entire client area
	    Window.enableScrolling(false);
	    Window.setMargin("0px");
	    Cookies.setCookie("skin_name_2_4", "EnterpriseBlue");
	    
	    
	    RootLayoutPanel.get().add(basicLayoutView);
        
	}
	
	/*private HeaderView headerView = new HeaderView();
	private VLayout mainLayout;
    private HLayout northLayout;
    private HLayout southLayout;
    private ApplicationContainerView eastLayout;
    private NavigationView westLayout;
    
    public void onModuleLoad() {
        
        com.google.gwt.user.client.Window.enableScrolling(false);
        com.google.gwt.user.client.Window.setMargin("0px");
        
        // main layout occupies the whole area
        mainLayout = new VLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();

        northLayout = new HLayout();
        northLayout.setHeight(UIConstants.HEADER_HEIGHT);

        northLayout.addMember(headerView);

        westLayout = new NavigationView();
        westLayout.setWidth(220);
        
        eastLayout = new ApplicationContainerView();
        eastLayout.setWidth("85%");
        
        southLayout = new HLayout();
        southLayout.setMembers(westLayout, eastLayout);

        mainLayout.addMember(northLayout);
        mainLayout.addMember(southLayout);

        // add the main layout container to GWT's root panel
        RootLayoutPanel.get().add(mainLayout);
        //mainLayout.draw();

    }*/
}


