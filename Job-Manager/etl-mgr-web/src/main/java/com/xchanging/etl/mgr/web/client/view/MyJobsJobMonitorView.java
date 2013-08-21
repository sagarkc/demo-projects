/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.view.MyJobsJobMonitorView
 * Date:	Aug 7, 2013  2:24:54 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.view;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.web.client.EtlManager;
import com.xchanging.etl.mgr.web.client.core.UIEventManager;
import com.xchanging.etl.mgr.web.client.dialog.AddJobsToMyJobMonitorDialog;
import com.xchanging.etl.mgr.web.client.endpoint.RemoteServiceEndpointFactory;
import com.xchanging.etl.mgr.web.client.event.MyJobSelectedJobNamesChangedEvent;
import com.xchanging.etl.mgr.web.client.event.MyJobSelectedJobNamesChangedEventListener;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEvent;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEventListener;
import com.xchanging.etl.mgr.web.shared.WebConstants;

import de.novanic.eventservice.client.event.RemoteEventService;
import de.novanic.eventservice.client.event.RemoteEventServiceFactory;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class MyJobsJobMonitorView extends VLayout
		implements MyJobSelectedJobNamesChangedEventListener{

	
	private final JobExecutionRealtimeGrid jobMonitorGrid 
		= new JobExecutionRealtimeGrid();
	private final ToolStrip jobsToolStrip = new ToolStrip();
	
	/**
	 * 
	 */
	public MyJobsJobMonitorView() {
		setStyleName("job-monitor-realTime");
		setWidth100();
		setHeight100();
		setLayoutMargin(2);
		initComponents();
		UIEventManager.getInstance().addListener(MyJobSelectedJobNamesChangedEvent.TYPE, this);
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
        Label titleLabel = new Label(EtlManager.MESSAGES.getLabel4JobMonitorMyJobs());
        titleLabel.setStyleName("toolbar-title");
        titleLabel.setWidth(250);
        jobsToolStrip.addMember(titleLabel);
        jobsToolStrip.addSeparator();
        jobsToolStrip.addSpacer(5);
        jobsToolStrip.addFill();
        
        ToolStripButton addJobButton = new ToolStripButton("Add Job");
        addJobButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final AddJobsToMyJobMonitorDialog dialog
					= new AddJobsToMyJobMonitorDialog();
				dialog.show();
			}
		});
        
        jobsToolStrip.addButton(addJobButton);
        
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
			new RealtimeJobMonitorDataEventListener() {

				@Override
				public void onDataPush(RealtimeJobMonitorDataEvent event) {
					if(null == event.getCurrentExecutionData())
						return;
					ListGridRecord records[] = new ListGridRecord[event.getCurrentExecutionData().size()];
					for (int i = 0; i < event.getCurrentExecutionData().size(); i++) {
						JobExecutionHistoryVo monitorVo = event.getCurrentExecutionData().get(i);
						ListGridRecord record = new ListGridRecord();
						record.setAttribute(JobExecutionHistoryVo.Fields.JOB_NAME, monitorVo.getJobName());
						record.setAttribute(JobExecutionHistoryVo.Fields.JOB_EXECUTION_ID, monitorVo.getJobExecutionId());
						record.setAttribute(JobExecutionHistoryVo.Fields.EXIT_CODE, monitorVo.getExitCode());
						record.setAttribute(JobExecutionHistoryVo.Fields.STATUS_CODE, monitorVo.getStatusCode());
						record.setAttribute(JobExecutionHistoryVo.Fields.START_TIME, monitorVo.getStartTime());
						record.setAttribute(JobExecutionHistoryVo.Fields.END_TIME, monitorVo.getEndTime());
						record.setAttribute(JobExecutionHistoryVo.Fields.EXIT_MESSAGE, monitorVo.getExitMessage());
						records[i] = record;
					}
					jobMonitorGrid.setData(records);
				}
			});
		
		RemoteServiceEndpointFactory.getInstance().getJobMonitorPushServiceEndpoint().start(
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

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.event.MyJobSelectedJobNamesChangedEventListener#selectedJobNamesChanged(com.xchanging.etl.mgr.web.client.event.MyJobSelectedJobNamesChangedEvent)
	 */
	@Override
	public void selectedJobNamesChanged(MyJobSelectedJobNamesChangedEvent event) {
		if(null != event && null != event.getSelectedJobNames()){
			jobMonitorGrid.filterData(
					new Criterion("jobNames", OperatorId.IN_SET, 
							event.getSelectedJobNames()));
		}
	}
	
	
}
