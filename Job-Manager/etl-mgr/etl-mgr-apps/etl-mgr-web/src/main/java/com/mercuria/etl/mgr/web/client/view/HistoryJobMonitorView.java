package com.mercuria.etl.mgr.web.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.mercuria.etl.mgr.web.client.core.GWTCollectionDataGrid;
import com.mercuria.etl.mgr.web.client.core.GWTGridColumnHeader;
import com.mercuria.etl.mgr.web.client.core.UIEventManager;
import com.mercuria.etl.mgr.web.client.ds.HistoricalJobMonitorDataSource;
import com.mercuria.etl.mgr.web.client.event.HistoricalJobMonitorEvent;
import com.mercuria.etl.mgr.web.client.event.HistoricalJobMonitorEventListener;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.FilterBuilder;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HistoryJobMonitorView extends VLayout implements HistoricalJobMonitorEventListener{

	private static UIEventManager uiEventManager = UIEventManager.getInstance();
	
	private final ToolStripButton refreshButton = new ToolStripButton("Refresh");
	private final GWTCollectionDataGrid<JavaScriptObject> jobMonitorHistoryGrid 
		= new GWTCollectionDataGrid<JavaScriptObject>();
	private final List<GWTGridColumnHeader> columnHeaders = new ArrayList<GWTGridColumnHeader>();;
	private final JobHistoryFilterView jobHistoryFilterView = new JobHistoryFilterView(); 
	private final CheckboxItem showFilterCheckboxItem = new CheckboxItem();
	private final ToolStrip jobHistoryToolStrip = new ToolStrip();
	
	private final HistoricalJobMonitorDataSource historicalJobMonitorDataSource 
		= HistoricalJobMonitorDataSource.getInstance();
	
	private final FilterBuilder filterBuilder = new FilterBuilder(); 
	
	
	public HistoryJobMonitorView() {
		uiEventManager.addListener(HistoricalJobMonitorEvent.TYPE, this);
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
		
		ListGridField idField = new ListGridField("jobInstanceId", "JOB Instance ID");
		idField.setCanEdit(false);
		idField.setHidden(true);
		idField.setType(ListGridFieldType.INTEGER);
		
		ListGridField nameField = new ListGridField("jobName", "JOB Name");
		ListGridField exitCodeField = new ListGridField("exitCode", "Exit Code");
		ListGridField startTimeField = new ListGridField("startTime", "Job Start Time");
		startTimeField.setCellFormatter(new CellFormatter() {

			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				record.getAttributeAsLong("startTime");
				return null;
			}
			
		});
		startTimeField.setAlign(Alignment.LEFT);
		
		ListGridField endTimeField = new ListGridField("endTime",  "Job End Time");
		ListGridField exitMessageField = new ListGridField("exitMessage", "Exit Message");
		
		filterBuilder.setDataSource(historicalJobMonitorDataSource);
		
		jobMonitorHistoryGrid.setFields(idField, nameField, exitCodeField, 
				startTimeField, endTimeField, exitMessageField);
		
		jobMonitorHistoryGrid.setWidth100();  
		jobMonitorHistoryGrid.setHeight100();  
		jobMonitorHistoryGrid.setShowAllRecords(true); 
		jobMonitorHistoryGrid.setAutoFetchData(true);
		jobMonitorHistoryGrid.setDataSource(historicalJobMonitorDataSource);
		
		Button filterButton = new Button("Filter");
		filterButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				jobMonitorHistoryGrid.filterData(filterBuilder.getCriteria());
			}
		});

	    addMember(filterBuilder);
	    addMember(filterButton);  
        addMember(jobMonitorHistoryGrid);  
  
        
	}

	public GWTCollectionDataGrid<JavaScriptObject> getJobMonitorHistoryGrid() {
		return jobMonitorHistoryGrid;
	}

	@Override
	public void showHistoricalJobMonitorData(HistoricalJobMonitorEvent event) {
		jobMonitorHistoryGrid.reload(event.getJobMonitorData());
	}

	public JobHistoryFilterView getJobHistoryFilterView() {
		return jobHistoryFilterView;
	}
	
	
}
