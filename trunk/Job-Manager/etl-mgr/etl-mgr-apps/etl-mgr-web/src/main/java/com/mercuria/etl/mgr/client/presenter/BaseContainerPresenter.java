package com.mercuria.etl.mgr.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.core.Presenter;
import com.mercuria.etl.mgr.client.view.BaseContainerView;

public abstract class BaseContainerPresenter<D extends Display> implements Presenter<D> {

	private final BaseContainerView baseContainer = new BaseContainerView();
	
	@Override
	public void go(HasWidgets container) {
		throw new RuntimeException("Do not use this method");
	}

	
	public BaseContainerView getBaseContainer() {
		return baseContainer;
	}


	public abstract void render(Presenter<Display> presenter);
	
}
