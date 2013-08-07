package com.xchanging.etl.mgr.web.client.presenter;

import com.xchanging.etl.mgr.web.client.core.Display;
import com.xchanging.etl.mgr.web.client.view.BaseContainerView;

public interface ViewPresenter<D extends Display> {
	
	void updateContainer(final BaseContainerView container);

	void bind();
	
	D getDisplay();
}