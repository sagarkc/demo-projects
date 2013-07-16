package com.mercuria.etl.mgr.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mercuria.etl.mgr.client.core.Display;

public class BasicLayoutPresenter extends BasePresenter<Display>{

	
	public interface LayoutDisplay extends Display{
		void updateHeaderView();
	}
	
	public BasicLayoutPresenter(Display display) {
		super(display);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(getDisplay().asWidget());
	}

	@Override
	public void bind() {
		
	}

	
	
}
