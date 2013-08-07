package com.xchanging.etl.mgr.web.client.presenter;


import com.xchanging.etl.mgr.web.client.core.Display;
import com.xchanging.etl.mgr.web.client.core.Presenter;
import com.xchanging.etl.mgr.web.client.core.UIEventManager;

public abstract class BasePresenter<D extends Display> implements Presenter<D>{
	
	private UIEventManager eventManager = UIEventManager.getInstance();
	
	private D headerDisplay;
	private D footerDisplay;
	private D leftNavDisplay;
	private D topActionDisplay;
	private D contentDisplay;
	
	public BasePresenter(D display) {
		this.contentDisplay = display;
	}

	public UIEventManager getEventManager() {
		return eventManager;
	}

	@Override
	public D getDisplay() {
		return contentDisplay;
	}
	
	
}
