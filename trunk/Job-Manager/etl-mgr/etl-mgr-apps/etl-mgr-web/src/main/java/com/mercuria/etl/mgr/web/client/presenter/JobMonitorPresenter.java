package com.mercuria.etl.mgr.web.client.presenter;

import com.google.gwt.user.client.ui.PopupPanel;
import com.mercuria.etl.mgr.web.client.core.Display;
import com.mercuria.etl.mgr.web.client.endpoint.JobMonitorClientEndpoint;
import com.mercuria.etl.mgr.web.client.view.JobMonitorView;

public class JobMonitorPresenter extends BaseContainerPresenter<Display> {

	final PopupPanel popup = new PopupPanel(false, true); 
	private final JobMonitorClientEndpoint jobMonitorClientEndpoint;
	
	public interface JobMonitorDisplay extends Display{
		
	}
	
	
	public JobMonitorPresenter(JobMonitorView display) {
		super(display);
		jobMonitorClientEndpoint = new JobMonitorClientEndpoint();
		bind();
	}


	@Override
	public void bind() {
		loadHistory();
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
