package com.mercuria.etl.mgr.web.client.i18n;

/**
 * Interface to represent the messages contained in resource bundle: 
 * Path: com/mercuria/etl/mgr/client/i18n/ApplicationMessages.properties'.
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
	
	@DefaultMessage("Historical Data")
	@Key("lbl.monitor.job.historical.data.btn")
	String getLblMonitorJobHistoricalDataBtn();
	
	@DefaultMessage("Historical Job Monitor")
	@Key("lbl.monitor.job.historical.data")
	String getLblMonitorJobHistoricalData();
}
