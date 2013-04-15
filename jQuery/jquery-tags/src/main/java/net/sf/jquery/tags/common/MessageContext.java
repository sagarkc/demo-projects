/**
 * File :: net.sf.jquery.tags.common.MessageContext
 * Date :: Apr 14, 2013
 */
package net.sf.jquery.tags.common;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MessageContext implements MessageSourceAware {

	private static MessageContext context;
	private MessageContext(){}
	
	
	public static MessageContext getContext() {
		if(null != context)
			return context;
		synchronized (MessageContext.class) {
			context = new MessageContext();
		}
		return context;
	}
	
	/* -------------------------------------------- */
	private Locale currentLocale;
	private MessageSource messageSource;
	
	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	public String getMessage(String code){
		return getMessageSource().getMessage(code, null, getCurrentLocale());
	}
	
	public String getMessage(String code, String defaultMessage){
		return getMessageSource().getMessage(code, null, defaultMessage, getCurrentLocale());
	}
}
