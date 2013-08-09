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

import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridField;
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
	private final ValuesManager valuesManager = new ValuesManager();
	/**
	 * 
	 */
	public AddJobsToMyJobMonitorDialog() {
		setWidth(520);   
        setHeight(360);   
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
        
        DynamicForm jobNameForm = new DynamicForm();   
        jobNameForm.setWidth100();   
        jobNameForm.setHeight100();
        jobNameForm.setDataSource(DistinctJobNamesDatasource.getInstance());
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
        jobNameForm.setItems(allJobNameSelectItem);
        
        addItem(jobNameForm);
        
        Button okButton = new Button("Select");
        okButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String[] selectedRecords = allJobNameSelectItem.getValues();
				if(null != selectedRecords && selectedRecords.length > 0){
					UIEventManager.getInstance()
						.fireEvent(new MyJobSelectedJobNamesChangedEvent(selectedRecords));
				}
				destroy();
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
