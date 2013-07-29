/**
 * File :: com.mercuria.etl.mgr.web.client.view.JobHistoryFilterView
 * Date :: 28-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.view;

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
		jobNamesForm.setGroupTitle("Select Job Names");
		jobNamesForm.setIsGroup(true);
		jobNamesForm.setTitleOrientation(TitleOrientation.TOP);
		jobNamesForm.setNumCols(1);
		jobNamesForm.setValuesManager(valuesManager);
		
		CheckboxItem selectAllJobNamesChkItem = new CheckboxItem();
		selectAllJobNamesChkItem.setValue(true);
		selectAllJobNamesChkItem.setTitle("Select All Jobs");
		selectAllJobNamesChkItem.setWidth("*");
		selectAllJobNamesChkItem.setName("selectAllJobNames");
		
		selectJobNameGrid.setTitle("Select Jobs"); 
		selectJobNameGrid.setWidth(220);
        selectJobNameGrid.setMultiple(true);  
        selectJobNameGrid.setMultipleAppearance(MultipleAppearance.GRID); 
        selectJobNameGrid.setValueMap("Cat", "Dog", "Giraffe", "Goat", "Marmoset", "Mouse");
        selectJobNameGrid.setName("selectedJobNames");
		
		jobNamesForm.setItems(selectAllJobNamesChkItem, selectJobNameGrid);
		
		formsHLayout.addMember(jobNamesForm);
		
		DynamicForm jobExecutionForm = new DynamicForm();
		jobExecutionForm.setWidth(350);
		jobExecutionForm.setHeight100();
		jobExecutionForm.setGroupTitle("Select Job execution times");
		jobExecutionForm.setIsGroup(true);
		jobExecutionForm.setTitleOrientation(TitleOrientation.TOP);
		jobExecutionForm.setNumCols(2);
		jobExecutionForm.setValuesManager(valuesManager);
		
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
        
        jobExecutionForm.setFields(new FormItem[] {
        		executionStartDate,  executionStartTime, executionEndDate, executionEndTime});
        formsHLayout.addMember(jobExecutionForm);
        
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
