package com.mercuria.etl.mgr.web.client.presenter;

import com.mercuria.etl.mgr.web.client.core.Display;
import com.mercuria.etl.mgr.web.client.view.BaseContainerView;

public interface ViewPresenter<D extends Display> {
	
	void updateContainer(final BaseContainerView container);

	void bind();
	
	D getDisplay();
}