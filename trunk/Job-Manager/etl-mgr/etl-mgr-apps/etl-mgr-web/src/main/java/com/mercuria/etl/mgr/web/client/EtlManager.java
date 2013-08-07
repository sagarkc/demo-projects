package com.xchanging.etl.mgr.web.client;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.xchanging.etl.mgr.web.client.i18n.ApplicationMessages;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EtlManager implements EntryPoint {

	private static Logger logger = Logger.getLogger(EtlManager.class.getName());
	
	public static final ApplicationMessages MESSAGES = GWT
			.create(ApplicationMessages.class);


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
	    
	    DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
		     public String format(Date date) {
		    	 String format = null;
		         if (date != null) { 
		        	 final DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
	        		 format = dateFormatter.format(date);
		         }
		         return format;
		     }
		});
	    
		AppController appViewer = new AppController();
	    appViewer.go(RootLayoutPanel.get());
        
	}
}


