package com.gs.ws.invoker.log.appender;

import com.gs.ws.invoker.log.TextAreaLogWorker;
import com.gs.ws.invoker.log.layout.FlexibleLayout;
import javax.swing.JTextArea;

import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;


public class TextAreaLogAppender extends WriterAppender{

	private static JTextArea outputTextArea = null;
	private static TextAreaLogWorker worker;
	public TextAreaLogAppender(JTextArea textArea) {
		outputTextArea = textArea;
		this.layout = new FlexibleLayout();
		worker = new TextAreaLogWorker("", outputTextArea);
	}
	
    @Override
	public void append(LoggingEvent event) {
		final String message = this.layout.format(event);
		worker = new TextAreaLogWorker(message, outputTextArea);
		worker.execute();
	}
	
}
