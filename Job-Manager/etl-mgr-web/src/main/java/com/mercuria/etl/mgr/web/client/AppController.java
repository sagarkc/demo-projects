package com.mercuria.etl.mgr.web.client;


import java.util.logging.Logger;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.web.client.core.Display;
import com.mercuria.etl.mgr.web.client.core.Presenter;
import com.mercuria.etl.mgr.web.client.presenter.BasicLayoutPresenter;
import com.mercuria.etl.mgr.web.client.view.BasicLayoutView;

public class AppController implements Presenter<Display>{
	
	private static Logger logger = Logger.getLogger(EtlManager.class.getName());
	private HasWidgets container;

	public AppController() {
		logger.info("In AppController constructor");
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			BasicLayoutPresenter presenter = new BasicLayoutPresenter(new BasicLayoutView());
			presenter.go(container);
		}
	}

	@Override
	public void bind() {
		
	}

	@Override
	public Display getDisplay() {
		return null;
	}

}