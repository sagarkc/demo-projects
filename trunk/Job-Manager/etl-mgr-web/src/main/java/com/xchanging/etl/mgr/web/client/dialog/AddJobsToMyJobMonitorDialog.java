/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.dialog.AddJobsToMyJobMonitorDialog
 * Date:	Aug 7, 2013  2:32:33 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.FilterBuilder;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.web.client.core.UIEventManager;
import com.xchanging.etl.mgr.web.client.ds.DistinctJobNamesDatasource;
import com.xchanging.etl.mgr.web.client.event.MyJobSelectedJobNamesChangedEvent;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class AddJobsToMyJobMonitorDialog extends Window{

	private List<String> selectedJobsNames = new ArrayList<String>();
	private FilterBuilder filterBuilder = new FilterBuilder();
	
	/**
	 * 
	 */
	public AddJobsToMyJobMonitorDialog() {
		setWidth(750);   
        setHeight(420);   
        setTitle("Select job");   
        setShowMinimizeButton(false);   
        setIsModal(true);   
        setShowModalMask(true);   
        centerInPage();   
        setShowResizer(true);
        setShowResizeBar(true);
        setMembersMargin(5);
        
        addCloseClickHandler(new CloseClickHandler() {   
            public void onCloseClick(CloseClickEvent event) {   
                destroy();   
            }   
        });
        
        final ListGrid jobNamesGrid = new ListGrid();  
        jobNamesGrid.setWidth(350);  
        jobNamesGrid.setHeight100();  
        jobNamesGrid.setShowAllRecords(true);  
        jobNamesGrid.setSelectionType(SelectionStyle.SIMPLE);  
        jobNamesGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        ListGridField jobNameField 
	    	= new ListGridField(JobMonitorHistoryVo.Fields.JOB_NAME, "Job Name");  
        jobNamesGrid.setFields(jobNameField); 
        jobNamesGrid.setDataSource(DistinctJobNamesDatasource.getInstance());
        jobNamesGrid.setAutoFetchData(true);
        
        final ListGrid selectedJobsGrid = new ListGrid();  
        selectedJobsGrid.setWidth(350);  
        selectedJobsGrid.setHeight100();  
        selectedJobsGrid.setTop(250);  
        selectedJobsGrid.setShowAllRecords(true);  
        ListGridField selectedJobsField 
        	= new ListGridField(JobMonitorHistoryVo.Fields.JOB_NAME, "Selected Jobs");  
        selectedJobsGrid.setFields(selectedJobsField); 
        
        jobNamesGrid.addSelectionUpdatedHandler(new SelectionUpdatedHandler() {  
            public void onSelectionUpdated(SelectionUpdatedEvent event) {  
            	selectedJobsGrid.setData(jobNamesGrid.getSelectedRecords());  
            }  
        }); 
        
        filterBuilder.setDataSource(DistinctJobNamesDatasource.getInstance());
        filterBuilder.setMembersMargin(5);
        filterBuilder.setHeight(35);
        
        VLayout vLayout = new VLayout();
        vLayout.setWidth100();
        vLayout.setHeight100();
        vLayout.setMembersMargin(2);
        
        
        IButton filterButton = new IButton("Filter");  
        filterButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
            	jobNamesGrid.filterData(filterBuilder.getCriteria());  
            }  
        });  
        
        VStack vStack = new VStack(10);  
        vStack.addMember(filterBuilder);  
        vStack.addMember(filterButton);  
        
        vLayout.addMember(vStack);
        
        
        HLayout gridsLayput = new HLayout();
        gridsLayput.setWidth100();
        gridsLayput.setHeight100();
        
        gridsLayput.addMember(jobNamesGrid);
        Label l = new Label("&nbsp;>>&nbsp;");
        l.setWidth(15);
        gridsLayput.addMember(l);
        gridsLayput.addMember(selectedJobsGrid);
        vLayout.addMember(gridsLayput);
        addItem(vLayout);
        
        
        /*DynamicForm jobNamesForm = new DynamicForm();   
        jobNamesForm.setWidth100();   
        jobNamesForm.setHeight100();
        jobNamesForm.setDataSource(DistinctJobNamesDatasource.getInstance());
        //jobNameForm.setValuesManager(valuesManager);
        
        //valuesManager.setDataSource(DistinctJobNamesDatasource.getInstance());
        //valuesManager.addMember(jobNameForm);
        
        final SelectItem allJobNameSelectItem = new SelectItem(JobMonitorHistoryVo.Fields.JOB_NAME, "Job Name");   
        allJobNameSelectItem.setTitle("Select Job Names "); 
        allJobNameSelectItem.setName(JobMonitorHistoryVo.Fields.JOB_NAME);
        allJobNameSelectItem.setValueField(JobMonitorHistoryVo.Fields.JOB_NAME);
        allJobNameSelectItem.setMultiple(true);   
        allJobNameSelectItem.setMultipleAppearance(MultipleAppearance.GRID);
        allJobNameSelectItem.setOptionDataSource(DistinctJobNamesDatasource.getInstance());
        allJobNameSelectItem.setDisplayField(JobMonitorHistoryVo.Fields.JOB_NAME);
        allJobNameSelectItem.setPickListFields(new ListGridField(JobMonitorHistoryVo.Fields.JOB_NAME));
        allJobNameSelectItem.setPickListWidth(100);
        allJobNameSelectItem.setWidth("380");
        allJobNameSelectItem.setHeight("120");
        jobNamesForm.setItems(allJobNameSelectItem);
        
        addItem(jobNamesForm);*/
        
        Button okButton = new Button("Select Jobs");
        okButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ListGridRecord[] selectedRecords = selectedJobsGrid.getRecords();
				if(null != selectedRecords){
					String[] selectedJobs = new String[selectedRecords.length];
					for (int i = 0; i < selectedRecords.length; i++) {
						ListGridRecord record = selectedRecords[i];
						selectedJobs[i] = record.getAttribute(JobMonitorHistoryVo.Fields.JOB_NAME);
					}
					UIEventManager.getInstance().fireEvent(
							new MyJobSelectedJobNamesChangedEvent(selectedJobs));
					destroy();
				}
			}
		});
        
        addItem(okButton);
        
        
	}
	/**
	 * @return the selectedJobsNames
	 */
	public List<String> getSelectedJobsNames() {
		return selectedJobsNames;
	}
	
	
}
