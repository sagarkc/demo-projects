package com.gs.demo.gwt.spring.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gs.demo.gwt.spring.shared.CommonEventManager;
import com.gs.demo.gwt.spring.shared.evt.MessageUpdateListener;
import com.gs.demo.gwt.spring.shared.evt.MessageUpdatedEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTSpringOne implements EntryPoint, MessageUpdateListener {
	private static final CommonEventManager eventManager = CommonEventManager.getInstance();
	private Label helloLabel = new Label();
	private Button button = new Button("Msg");
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(helloLabel);
		eventManager.addMessageUpdateListener(this);
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handleButtonClick();
			}

			
		});
		
		verticalPanel.add(button);
		RootPanel.get().add(verticalPanel);
	}
	
	private void handleButtonClick() {
		MessageUpdatedEvent evt = new MessageUpdatedEvent("Hello....");
		eventManager.fireEvent(evt);
	}
	
	public void seyHello(String msg){
		helloLabel.setText(msg);
	}
	
	/* (non-Javadoc)
	 * @see com.gs.demo.gwt.spring.shared.evt.MessageUpdateListener#messageUpdated(com.gs.demo.gwt.spring.shared.evt.MessageUpdatedEvent)
	 */
	@Override
	public void messageUpdated(MessageUpdatedEvent messageUpdatedEvent) {
		if(null != messageUpdatedEvent){
			seyHello(messageUpdatedEvent.getMessage());
		}
	}
}
