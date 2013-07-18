package com.mercuria.etl.mgr.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.core.Presenter;
import com.mercuria.etl.mgr.client.view.BaseContainerView;

public abstract class BaseContainerPresenter<D extends Display> implements Presenter<D> {

	private final D display;
	
	
	public BaseContainerPresenter(D display) {
		this.display = display;
	}


	@Override
	public final void go(HasWidgets container) {
	}

	@Override
	public final D getDisplay() {
		return display;
	}
	
	public BaseContainerView getBaseContainer() {
		return BaseContainerView.getContainer();
	}


	public abstract void render();
	
}
