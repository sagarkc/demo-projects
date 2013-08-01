package com.mercuria.etl.mgr.web.client.presenter;

import java.util.HashMap;

import com.google.gwt.user.client.ui.PopupPanel;
import com.mercuria.etl.mgr.web.client.core.Display;
import com.mercuria.etl.mgr.web.client.endpoint.JobMonitorClientEndpoint;
import com.mercuria.etl.mgr.web.client.view.JobHistoryFilterView;
import com.mercuria.etl.mgr.web.client.view.JobMonitorView;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.events.SubmitValuesHandler;

public class JobMonitorPresenter extends BaseContainerPresenter<Display> {

	final PopupPanel popup = new PopupPanel(false, true); 
	private final JobMonitorClientEndpoint jobMonitorClientEndpoint;
	private JobMonitorDisplay jobMonitorDisplay;
	 
	public interface JobMonitorDisplay extends Display{
		public JobHistoryFilterView getJobHistoryFilterView();
	}
	
	
	public JobMonitorPresenter(JobMonitorView display) {
		super(display);
		jobMonitorDisplay = display;
		jobMonitorClientEndpoint = new JobMonitorClientEndpoint();
		bind();
	}


	@Override
	public void bind() {
		loadHistory();
		jobMonitorDisplay.getJobHistoryFilterView().getSearchButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				jobMonitorDisplay.getJobHistoryFilterView().getValuesManager().setValues(new HashMap());
				jobMonitorDisplay.getJobHistoryFilterView().getValuesManager().submit();
			}
		});
		
		jobMonitorDisplay.getJobHistoryFilterView().getValuesManager().addSubmitValuesHandler(new SubmitValuesHandler() {
			
			@Override
			public void onSubmitValues(SubmitValuesEvent event) {
				if(event.getSource() instanceof ValuesManager){
					ValuesManager valuesManager = (ValuesManager) event.getSource();
					SC.say("Values: \n" 
							+ valuesManager.getAttribute("selectAllJobNames") + "\n"
							+ valuesManager.getAttribute("selectedJobNames") + "\n"
							+ valuesManager.getAttribute("executionStartDate") + "\n"
							+ valuesManager.getAttribute("executionEndDate") + "\n"
							+ valuesManager.getAttribute("executionStartTime") + "\n"
							+ valuesManager.getAttribute("executionEndTime") );
				}
			}
		});
	}

	
	/**
	 * 
	 */
	private void loadHistory() {
		jobMonitorClientEndpoint.loadHistoricalMonitorData();
	}


	@Override
	public void render() {
		removeAllChield();
		getBaseContainer().addMember(getDisplay().asWidget());
		getBaseContainer().redraw();
	}


//	// Create a modal dialog box that will not auto-hide
//	popup.add(new Label("Please wait"));
//	popup.setGlassEnabled(true); // Enable the glass panel
//	popup.center(); // Center the popup and make it visible

	
	
}
