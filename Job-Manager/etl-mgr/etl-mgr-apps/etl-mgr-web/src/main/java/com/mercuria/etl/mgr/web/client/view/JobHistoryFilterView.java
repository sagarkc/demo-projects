/**
 * File :: com.mercuria.etl.mgr.web.client.view.JobHistoryFilterView
 * Date :: 28-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.view;

import com.mercuria.etl.mgr.web.client.ds.HistoricalJobMonitorDataSource;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.FetchMode;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class JobHistoryFilterView extends VLayout{

	private final Button searchButton = new Button("Search");
	private final SelectItem selectJobNameGrid = new SelectItem();
	private final DateItem executionStartDate = new DateItem();
	private final DateItem executionEndDate = new DateItem();
	private final TimeItem executionStartTime = new TimeItem();
	private final TimeItem executionEndTime = new TimeItem();
	
	private final ValuesManager valuesManager = new ValuesManager(); 
	
	/**
	 * 
	 */
	public JobHistoryFilterView() {
		setWidth100();
		setHeight("*");
		setMembersMargin(5);
		setStyleName("jobFilterView");
		initComponents();
	}

	/**
	 * 
	 */
	private void initComponents() {
		HLayout formsHLayout = new HLayout();
		formsHLayout.setWidth100();
		formsHLayout.setHeight("*");
		formsHLayout.setMembersMargin(5);
        
		DynamicForm jobNamesForm = new DynamicForm();
		jobNamesForm.setWidth100();
		jobNamesForm.setHeight100();
		jobNamesForm.setGroupTitle("Filter Jobs");
		jobNamesForm.setIsGroup(true);
		jobNamesForm.setTitleOrientation(TitleOrientation.TOP);
		jobNamesForm.setNumCols(2);
		jobNamesForm.setValuesManager(valuesManager);
		
		jobNamesForm.setDataFetchMode(FetchMode.BASIC);
		
		
		CheckboxItem selectAllJobNamesChkItem = new CheckboxItem();
		selectAllJobNamesChkItem.setValue(true);
		selectAllJobNamesChkItem.setTitle("Select All Jobs");
		selectAllJobNamesChkItem.setWidth("*");
		selectAllJobNamesChkItem.setName("selectAllJobNames");
		selectAllJobNamesChkItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				if((Boolean)event.getValue() ){
					selectJobNameGrid.setDisabled(true);
					selectJobNameGrid.setCriterion(null);
				} else {
					selectJobNameGrid.setDisabled(false);
					//selectJobNameGrid.setCriterion(new Criterion().seta);
				}
			}
		});
		
		selectJobNameGrid.setTitle("Select Jobs"); 
		selectJobNameGrid.setWidth("100%");
        selectJobNameGrid.setMultiple(true);  
        selectJobNameGrid.setMultipleAppearance(MultipleAppearance.GRID); 
        selectJobNameGrid.setValueMap("job", "job1", "job2", "Goat", "Marmoset", "Mouse");
        selectJobNameGrid.setName("selectedJobNames");
        selectJobNameGrid.setCriteriaField("selectedJobNames");
        /*selectJobNameGrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(event.getSource().equals(selectJobNameGrid)){
					String[] values = selectJobNameGrid.getValues();
					valuesManager.setAttribute("selectedJobNames", values, true);
				}
			}
		});*/
		
				
        executionStartDate.setName("executionStartDate");  
        executionStartDate.setTitle("Exceution Start Date");  
        executionStartDate.setRequired(true);
        
        executionEndDate.setName("executionEndDate");  
        executionEndDate.setTitle("Exceution End Date");  
        executionEndDate.setRequired(true);
        
        executionStartTime.setName("executionStartTime");  
        executionStartTime.setTitle("Exceution Start Time");  
        executionStartTime.setRequired(true);  
        
        executionEndTime.setName("executionEndTime");  
        executionEndTime.setTitle("Exceution End Time");  
        executionEndTime.setRequired(true);  
        
        FormItem[] formItems = new FormItem[] {
        		selectAllJobNamesChkItem, selectJobNameGrid,
        		executionStartDate,  executionStartTime, executionEndDate, executionEndTime};
        jobNamesForm.setDataSource(HistoricalJobMonitorDataSource.getInstance(), formItems);
        jobNamesForm.setFields(formItems);
        formsHLayout.addMember(jobNamesForm);
        
        addMember(formsHLayout);
        
        addMember(searchButton);
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public ValuesManager getValuesManager() {
		return valuesManager;
	}
	
	
	
}
