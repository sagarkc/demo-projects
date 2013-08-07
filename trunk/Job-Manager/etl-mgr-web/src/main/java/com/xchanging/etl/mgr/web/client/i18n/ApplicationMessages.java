package com.xchanging.etl.mgr.web.client.i18n;


/**
 * Interface to represent the messages contained in resource bundle: Path:
 * com/xchanging/etl/mgr/client/i18n/ApplicationMessages.properties'.
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

	/**
	 * @return
	 */
	@DefaultMessage("Historical Job Monitor")
	@Key("title.monitor.job.historical.data")
	String getTitleMonitorJobHistoricalData();

	@DefaultMessage("Last Hour Data")
	@Key("title.monitor.job.historical.data.filter.last.hour")
	String getLastHourFilterItemTitle();
	
	/* Navigation */
	@DefaultMessage("Job Monitor")
	@Key("title.section.job.monitor")
	String getSectionTitle4JobMonitor();
	
	@DefaultMessage("My Jobs")
	@Key("lbl.section.job.monitor.my.jobs")
	String getLabel4JobMonitorMyJobs();
	
	@DefaultMessage("Historical")
	@Key("lbl.section.job.monitor.historical")
	String getLabel4JobMonitorHistorical();
	
	
	@DefaultMessage("Scheduler Monitor")
	@Key("title.section.scheduler.monitor")
	String getSectionTitle4SchedulerMonitor();
	
	@DefaultMessage("Integrated Schedulers")
	@Key("title.sub.section.integrated.schedulers")
	String getSubSectionTitle4IntegratedSchedulers();
	
	@DefaultMessage("Add Scheduler (JMX)")
	@Key("lbl.sub.section.add.jmx.scheduler")
	String getLabel4AddJmxScheduler();
	
	@DefaultMessage("Triggers")
	@Key("lbl.sub.section.jmx.scheduler.trigger")
	String getLabel4Triggers();
	
	@DefaultMessage("Jobs")
	@Key("lbl.sub.section.jmx.scheduler.job")
	String getLabel4Jobs();
	
}
