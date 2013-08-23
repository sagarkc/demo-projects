package com.xchanging.etl.mgr.web.client.view;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.web.client.EtlManager;
import com.xchanging.etl.mgr.web.client.dialog.AddJobsToMyJobMonitorDialog;
import com.xchanging.etl.mgr.web.client.endpoint.RemoteServiceEndpointFactory;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEvent;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorEventListener;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorSummaryEvent;
import com.xchanging.etl.mgr.web.shared.WebConstants;

import de.novanic.eventservice.client.event.RemoteEventService;
import de.novanic.eventservice.client.event.RemoteEventServiceFactory;

public class RTLastHourJobMonitorView extends VLayout {

	private final RTLastHourJobMonitorGrid jobMonitorGrid = new RTLastHourJobMonitorGrid();
	private final ToolStrip jobsToolStrip = new ToolStrip();


	public RTLastHourJobMonitorView() {
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

		jobsToolStrip.setWidth100();
		jobsToolStrip.setHeight(25);
		jobsToolStrip.addSpacer(10);
		Img image = new Img();
		image.setSrc("monitor/job/history-24x24.png");
		image.setWidth(16);
		image.setHeight(16);
		image.setShowRollOver(false);
		image.setShowDownIcon(false);
		image.setShowDown(false);
		jobsToolStrip.addMember(image);
		jobsToolStrip.addSpacer(5);
		Label titleLabel = new Label(
				EtlManager.MESSAGES.getLabel4JobMonitorMyJobs());
		titleLabel.setStyleName("toolbar-title");
		titleLabel.setWidth(250);
		jobsToolStrip.addMember(titleLabel);
		jobsToolStrip.addSeparator();
		jobsToolStrip.addSpacer(5);
		jobsToolStrip.addFill();

		final ToolStripButton refreshListButton = new ToolStripButton("Refresh");
        refreshListButton.setDisabled(true);
        refreshListButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				jobMonitorGrid.fetchData();
			}
		});
        
        DynamicForm autoRefreshForm = new DynamicForm();
        final CheckboxItem autoCheckboxItem = new CheckboxItem("autoRefresh");
        autoCheckboxItem.setTextAlign(Alignment.RIGHT);
        autoCheckboxItem.setLabelAsTitle(true);
        autoCheckboxItem.setValue(true);
        autoCheckboxItem.setWidth(100);
        autoCheckboxItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				if(autoCheckboxItem.getAttributeAsBoolean("value")){
					refreshListButton.setDisabled(true);
					RemoteServiceEndpointFactory.getInstance().getRtLastHourJobMonitorPushServiceEndpoint().start(
							new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
								}
								@Override
								public void onFailure(Throwable caught) {
									SC.say("Server Push on MyJob Job Monitor Data Fail");
								}
							});
				} else {
					refreshListButton.setDisabled(false);
					RemoteServiceEndpointFactory.getInstance().getRtLastHourJobMonitorPushServiceEndpoint().stop(
							new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
								}
								@Override
								public void onFailure(Throwable caught) {
									SC.say("Server Push on MyJob Job Monitor Data Fail");
								}
							});
				}
			}
		});
        autoRefreshForm.setFields(autoCheckboxItem);
        jobsToolStrip.addMember(autoRefreshForm);
        jobsToolStrip.addSpacer(15);

		jobsToolStrip.addButton(refreshListButton);

		header.addMember(jobsToolStrip);

		addMember(header);

		
		jobMonitorGrid.setWidth100();
		jobMonitorGrid.setHeight100();
		addMember(jobMonitorGrid);
		enableClientPush();

	}

	/**
 * 
 */
	private void enableClientPush() {
		RemoteEventServiceFactory theEventServiceFactory = RemoteEventServiceFactory
				.getInstance();
		RemoteEventService theEventService = theEventServiceFactory
				.getRemoteEventService();

		theEventService.addListener(WebConstants.DOMAIN_MONITOR_JOB,
				new RTJobMonitorPushEventProcessor(jobMonitorGrid));
	}

	
}
