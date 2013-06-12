package com.gs.gwtproject.three.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gs.gwtproject.three.client.i18n.ApplicationMessages;
import com.gs.gwtproject.three.client.service.ExampleServiceClientImpl;
import com.gs.gwtproject.three.shared.CommonEventManager;
import com.gs.gwtproject.three.shared.event.MessageUpdatedEvent;
import com.gs.gwtproject.three.shared.listener.MessageUpdateListener;

/**
 * Entry point classes define
 * <code>onModuleLoad()</code>.
 */
public class GwtProjectThree implements EntryPoint, MessageUpdateListener {

    private final ApplicationMessages messages = GWT.create(ApplicationMessages.class);

    private static final CommonEventManager eventManager = CommonEventManager.getInstance();
	private Label helloLabel;
	private Button button;
    private ExampleServiceClientImpl clientImpl =
				new ExampleServiceClientImpl(GWT.getModuleBaseURL() + "greet");
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        initComponents();
        VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(helloLabel);
		eventManager.addListener(MessageUpdatedEvent.TYPE, this);
		button.addClickHandler(new ClickHandler() {
            @Override
			public void onClick(ClickEvent event) {
				handleButtonClick();
			}
		});
		
		verticalPanel.add(button);
		RootPanel.get().add(verticalPanel);
    }

    private void initComponents() {
        helloLabel = new Label(messages.nameFieldLebel());
        button = new Button(messages.sendButton());
    }
    
    private void handleButtonClick() {
		clientImpl.sayHello("Sabuj");
	}
    
    public void seyHello(String msg){
		helloLabel.setText(msg);
	}

    @Override
    public void messageUpdated(MessageUpdatedEvent messageUpdatedEvent) {
        if(null != messageUpdatedEvent){
			seyHello(messageUpdatedEvent.getMessage());
		}
    }
}
