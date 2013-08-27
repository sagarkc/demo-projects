/**
 * 
 */
package com.xchanging.etl.mgr.web.client.dialog;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.FormItemType;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.xchanging.etl.mgr.model.scheduler.SchedulerDetail;
import com.xchanging.etl.mgr.web.client.jmx.ds.MBeanSchedulerNamesDataSource;

/**
 * @author Sabuj
 *
 */
public class AddJMXSchedulerDialog extends Window {

	private final VLayout layout = new VLayout();
	
	public AddJMXSchedulerDialog() {
		
		setWidth(750);   
        setHeight(420); 
        setTitle("JMX Jobs...");    
        setShowMinimizeButton(false);  
        setIsModal(true);  
        setShowModalMask(true);  
        centerInPage();  
        setMembersMargin(5);
        
		addCloseClickHandler(new CloseClickHandler() {   
            public void onCloseClick(CloseClickEvent event) {   
                destroy();   
            }   
        });
		
        layout.setWidth100();
        layout.setHeight100();
        layout.setMembersMargin(3);
        
        final ListGrid mbeanNamesGrid = new ListGrid();  
        mbeanNamesGrid.setWidth100();  
        mbeanNamesGrid.setHeight100();  
        mbeanNamesGrid.setShowAllRecords(true);  
        mbeanNamesGrid.setSelectionType(SelectionStyle.SIMPLE);  
        mbeanNamesGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        ListGridField jobNameField 
	    	= new ListGridField(SchedulerDetail.Fields.SCHEDULER_NAME, "Job Name");  
        mbeanNamesGrid.setFields(jobNameField); 
        mbeanNamesGrid.setDataSource(MBeanSchedulerNamesDataSource.getInstance());
        mbeanNamesGrid.setAutoFetchData(false);
        
		HLayout searchBar = new HLayout();
		searchBar.setWidth100();
		searchBar.setHeight(25);
		
		Label searchText = new Label("JMX Host");
		searchText.setHeight(25);
		searchText.setWidth(70);
		searchBar.addMember(searchText);
		
		DynamicForm jmxSearchForm = new DynamicForm();
		jmxSearchForm.setLayoutAlign(VerticalAlignment.BOTTOM);
		jmxSearchForm.setWidth100();  
		jmxSearchForm.setPadding(5); 
		jmxSearchForm.setItemLayout(FormLayoutType.TABLE);
		//jmxSearchForm.setColWidths(120);
		
		final TextItem jmxHostInput = new TextItem("jmxHostName", "JMX Host");
		
		final TextItem jmxPortInput = new TextItem("jmxPortNumber", "Port");
		jmxSearchForm.setFields(jmxHostInput, jmxPortInput);
		
		jmxSearchForm.setTitleWidth(50);
		searchBar.addMember(jmxSearchForm);
		
		IButton jmxSearchButton = new IButton("Search");
		jmxSearchButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Criteria criteria = new Criteria();
				criteria.addCriteria("jmxHostName", jmxHostInput.getAttributeAsString("value"));
				criteria.addCriteria("jmxPortNumber", jmxPortInput.getAttributeAsString("value"));
				mbeanNamesGrid.filterData(criteria);
			}
		});
		searchBar.addMember(jmxSearchButton);
		
		layout.addMember(searchBar);
		
		VLayout vLayout = new VLayout();
		vLayout.setWidth100();
		vLayout.setHeight100();
		
		HLayout layer01 = new HLayout();
		layer01.setWidth100();
		layer01.setHeight100();
		
		Label selectMbeanLabel = new Label("Select MBean:");
		selectMbeanLabel.setWidth(45);
		layer01.addMember(selectMbeanLabel);
		
		layer01.addMember(mbeanNamesGrid);
		
		vLayout.addMember(layer01);
		
		HLayout layer02 = new HLayout();
		layer02.setWidth100();
		layer02.setHeight(25);
		layer02.setAlign(Alignment.CENTER);
		
		IButton slectButton = new IButton("Select", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SC.say("Clicked");
			}
		});
		layer02.addMember(slectButton);
		
		vLayout.addMember(layer02);
		
		layout.addMember(vLayout);
        
		addItem(layout);
	}

	
}
