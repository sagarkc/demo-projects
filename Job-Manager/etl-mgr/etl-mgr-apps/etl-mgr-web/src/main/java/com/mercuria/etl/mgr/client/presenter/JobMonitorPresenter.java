package com.mercuria.etl.mgr.client.presenter;

import com.google.gwt.user.client.ui.PopupPanel;
import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.view.JobMonitorView;

public class JobMonitorPresenter extends BaseContainerPresenter<Display> {

	final PopupPanel popup = new PopupPanel(false, true); 
	
	public interface JobMonitorDisplay extends Display{
		
	}
	
	private JobMonitorView jobMonitorView;
	
	public JobMonitorPresenter(JobMonitorView display) {
		super(display);
		jobMonitorView = display;
		bind();
	}


	@Override
	public void bind() {
		
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
