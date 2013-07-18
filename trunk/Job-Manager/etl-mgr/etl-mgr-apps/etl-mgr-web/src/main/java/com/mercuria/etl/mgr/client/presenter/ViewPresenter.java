package com.mercuria.etl.mgr.client.presenter;

import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.view.BaseContainerView;

public interface ViewPresenter<D extends Display> {
	
	void updateContainer(final BaseContainerView container);

	void bind();
	
	D getDisplay();
}