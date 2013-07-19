package com.mercuria.etl.mgr.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.client.core.Display;
import com.mercuria.etl.mgr.client.core.Presenter;
import com.mercuria.etl.mgr.client.view.BaseContainerView;
import com.smartgwt.client.widgets.Canvas;

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
	
	public void removeAllChield(){
		Canvas[] children = getBaseContainer().getMembers();
		if(null != children && children.length > 0){
			getBaseContainer().removeMembers(children);
		}
	}
}
