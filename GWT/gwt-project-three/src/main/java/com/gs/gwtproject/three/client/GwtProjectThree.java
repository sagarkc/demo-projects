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
import com.gs.gwtproject.three.client.service.UserManagerClient;
import com.gs.gwtproject.three.client.service.UserManagerClientImpl;
import com.gs.gwtproject.three.client.ui.UserView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtProjectThree implements EntryPoint {

	private final ApplicationMessages messages = GWT
			.create(ApplicationMessages.class);
	private Label emailLabel;
	private Button button;

	private UserManagerClient userClient = new UserManagerClientImpl();

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		initComponents();
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(emailLabel);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("Button clicked.....");
				handleButtonClick();
			}
		});

		verticalPanel.add(button);
		verticalPanel.add(new UserView());
		RootPanel.get().add(verticalPanel);
	}

	private void initComponents() {
		emailLabel = new Label(messages.nameFieldLebel());
		button = new Button(messages.sendButton());
	}

	private void handleButtonClick() {
		userClient.getUser("dskfhaslkdj alskdjfl askjdf");
	}

}
