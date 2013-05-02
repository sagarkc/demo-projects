/**
 * File :: com.gs.examples.gwt.two.client.ui.MainView
 * Date :: 01-May-2013
 */
package com.gs.examples.gwt.two.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gs.examples.gwt.two.client.service.ExampleServiceClient;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MainView extends Composite {

	private VerticalPanel basePanel = new VerticalPanel();
	private final ExampleServiceClient exampleServiceClient;
	private TextBox txt1, txtA, txtB;
	Button b1 , b2;
	private Label resultLabel;
	
	/**
	 * @param exampleServiceClientImpl 
	 * 
	 */
	public MainView(final ExampleServiceClient exampleServiceClient) {
		initWidget(basePanel);
		this.exampleServiceClient = exampleServiceClient;
		txt1 = new TextBox();
		basePanel.add(txt1);
		
		b1 =new Button("Say Hello");
		b1.addClickHandler(new ButtonClickHandler());
		basePanel.add(b1);
		
		resultLabel = new Label();
		basePanel.add(resultLabel);
		
		
		txtA = new  TextBox();
		txtB = new  TextBox();
		
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(txtA);
		panel.add(new Label("+"));
		panel.add(txtB);
		basePanel.add(panel);
		b2 =new Button("Sum");
		b2.addClickHandler(new ButtonClickHandler());
		basePanel.add(b2);
		
	}
	
	public void updateResult(String resultTxt){
		resultLabel.setText(resultTxt);
	}
	
	private class ButtonClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(event.getSource().equals(b1)){
				exampleServiceClient.sayHello(txt1.getText());
			}
			if(event.getSource().equals(b2)){
				int a = Integer.parseInt(txtA.getText());
				int b = Integer.parseInt(txtB.getText());
				exampleServiceClient.add(a, b);
			}
		}
		
	}
}
