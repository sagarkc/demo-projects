package com.mercuria.etl.mgr.client.presenter;

import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.core.Presenter;

public class JobMonitorPresenter extends BaseContainerPresenter<Display> {

	public interface JobMonitorDisplay extends Display{
		
	}
	
	private Display display;
	
	public JobMonitorPresenter(Display display) {
		this.display = display;
	}


	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void render(Presenter<Display> presenter) {
		
	}


	@Override
	public Display getDisplay() {
		return display;
	}

	
	
}
