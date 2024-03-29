package com.xchanging.etl.mgr.web.client.core;

import com.google.gwt.user.client.ui.HasWidgets;

public interface Presenter<D extends Display> {
	
	void go(final HasWidgets container);

	void bind();
	
	D getDisplay();
}
