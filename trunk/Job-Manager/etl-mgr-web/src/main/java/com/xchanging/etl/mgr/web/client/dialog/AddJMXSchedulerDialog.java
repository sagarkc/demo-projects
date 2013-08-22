/**
 * 
 */
package com.xchanging.etl.mgr.web.client.dialog;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

/**
 * @author Sabuj
 *
 */
public class AddJMXSchedulerDialog extends Window {

	private final VLayout layout = new VLayout();
	
	public AddJMXSchedulerDialog() {
		setWidth(750);   
        setHeight(420);   
        setTitle("Add JMX Scheduler");   
        setShowMinimizeButton(false);   
        setIsModal(true);   
        setShowModalMask(true);   
        centerInPage();   
        setShowResizer(true);
        setShowResizeBar(true);
        setMembersMargin(5);
		setPadding(3);
		addCloseClickHandler(new CloseClickHandler() {   
            public void onCloseClick(CloseClickEvent event) {   
                destroy();   
            }   
        });
		
        layout.setWidth100();
        layout.setHeight100();
        layout.setMembersMargin(3);
        
        initLayout();
        
        addMember(layout);
	}

	private void initLayout() {
		ToolStrip searchBar = new ToolStrip();
		searchBar.setWidth100();
		searchBar.setHeight(25);
		
		Label searchText = new Label("JMX Host");
		searchText.setHeight(25);
		searchText.setWidth(70);
		//searchBar.addMember(searchText);
		
		DynamicForm jmxSearchForm = new DynamicForm();
		
		TextItem jmxHostInput = new TextItem("jmxHostName", "JMX Host");
		jmxSearchForm.setFields(jmxHostInput);
		
		searchBar.addMember(jmxSearchForm);
		
		IButton jmxSearchButton = new IButton("Search");
		
		searchBar.addMember(jmxSearchButton);
		
		layout.addMember(searchBar);
		
		
	}
	
}
