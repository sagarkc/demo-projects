package com.mercuria.etl.mgr.client.presenter;


import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.core.Presenter;
import com.mercuria.etl.mgr.client.core.UIEventManager;

public abstract class BasePresenter<D extends Display> implements Presenter<D>{
	
	private UIEventManager eventManager = UIEventManager.getInstance();
	
	private D headerDisplay;
	private D footerDisplay;
	private D leftNavDisplay;
	private D topActionDisplay;
	private D contentDisplay;
	
	public BasePresenter(D display) {
		this.contentDisplay = display;
		bind();
	}

	public UIEventManager getEventManager() {
		return eventManager;
	}

	@Override
	public D getDisplay() {
		return contentDisplay;
	}
	
	
}
