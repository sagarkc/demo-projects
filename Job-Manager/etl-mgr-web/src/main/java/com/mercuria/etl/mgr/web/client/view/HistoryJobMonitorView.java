/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.client.view.HistoryJobMonitorView
 * Date:	Aug 5, 2013  8:12:46 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.client.view;

import com.mercuria.etl.mgr.web.client.core.UIEventManager;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class HistoryJobMonitorView extends VLayout {

	private static UIEventManager uiEventManager = UIEventManager.getInstance();
	
	private final ToolStripButton refreshButton = new ToolStripButton("Refresh");
	private final JobExecutionHistoryGrid jobMonitorHistoryGrid 
		= new JobExecutionHistoryGrid();
	private final JobHistoryFilterView jobHistoryFilterView = new JobHistoryFilterView(); 
	private final CheckboxItem showFilterCheckboxItem = new CheckboxItem();
	private final ToolStrip jobHistoryToolStrip = new ToolStrip();
	
	public HistoryJobMonitorView() {
		setStyleName("job-monitor-realTime");
		setWidth100();
		setHeight100();
		setLayoutMargin(2);
		initComponents();
	}

	private void initComponents() {
		HLayout header = new HLayout();
		header.setWidth100();
		header.setHeight(25);
		  
        jobHistoryToolStrip.setWidth100();
        jobHistoryToolStrip.setHeight(25);
        jobHistoryToolStrip.addFill();
        
        jobHistoryToolStrip.addButton(refreshButton);
        
		showFilterCheckboxItem.setTitle("Show Filter Section");
		showFilterCheckboxItem.setValue(true);
		showFilterCheckboxItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				if((Boolean)event.getValue() ){
					jobHistoryFilterView.animateShow(AnimationEffect.SLIDE);
				} else {
					jobHistoryFilterView.animateHide(AnimationEffect.SLIDE);
				}
			}
		});
		
		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setItems(showFilterCheckboxItem);
		jobHistoryToolStrip.addMember(dynamicForm);
		
		
		header.addMember(jobHistoryToolStrip);
		
		addMember(header);
		
		addMember(jobHistoryFilterView);
		
		
		
		jobMonitorHistoryGrid.setWidth100();  
		jobMonitorHistoryGrid.setHeight100();  
		
		
		addMember(jobMonitorHistoryGrid);  
  
        
	}

	public JobExecutionHistoryGrid getJobMonitorHistoryGrid() {
		return jobMonitorHistoryGrid;
	}

	
	public JobHistoryFilterView getJobHistoryFilterView() {
		return jobHistoryFilterView;
	}
	
	
}