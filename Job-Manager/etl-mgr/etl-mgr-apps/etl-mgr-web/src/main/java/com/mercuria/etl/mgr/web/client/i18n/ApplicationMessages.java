package com.xchanging.etl.mgr.web.client.i18n;

/**
 * Interface to represent the messages contained in resource bundle: 
 * Path: com/xchanging/etl/mgr/client/i18n/ApplicationMessages.properties'.
 */
public interface ApplicationMessages extends
		com.google.gwt.i18n.client.Messages {

	/**
	 * Translated "ETL Manager".
	 * 
	 * @return translated "ETL Manager"
	 */
	@DefaultMessage("ETL Manager")
	@Key("applicationTitle")
	String applicationTitle();

	/**
	 * Translated "Enter your name".
	 * 
	 * @return translated "Enter your name"
	 */
	@DefaultMessage("Enter your name")
	@Key("nameField")
	String nameField();

	/**
	 * Translated "Send".
	 * 
	 * @return translated "Send"
	 */
	@DefaultMessage("Send")
	@Key("sendButton")
	String sendButton();
}
