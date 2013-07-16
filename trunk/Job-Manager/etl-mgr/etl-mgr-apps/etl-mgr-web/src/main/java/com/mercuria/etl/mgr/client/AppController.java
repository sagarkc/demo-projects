package com.mercuria.etl.mgr.client;


import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.core.Presenter;
import com.mercuria.etl.mgr.client.presenter.BasicLayoutPresenter;
import com.mercuria.etl.mgr.client.view.BasicLayoutView;

public class AppController implements Presenter<Display>,
		ValueChangeHandler<String> {
	
	private static Logger logger = Logger.getLogger(EtlManager.class.getName());
	private HasWidgets container;

	public AppController() {
		logger.info("In AppController constructor");
		bind();
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter<Display> presenter = null;

			if (token.equals("home")) {
				presenter = new BasicLayoutPresenter(new BasicLayoutView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("home");
		}
	}

	@Override
	public void bind() {
		History.addValueChangeHandler(this);

	}

	@Override
	public Display getDisplay() {
		return null;
	}

}