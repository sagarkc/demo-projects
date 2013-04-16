/**
 * File :: com.gs.demo.hr.web.HrmgrLocaleResolver
 * Date :: Apr 16, 2013
 */
package com.gs.demo.hr.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jquery.tags.common.MessageContext;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class HrmgrLocaleResolver extends SessionLocaleResolver {

	@Override
	public void setLocale(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		MessageContext.getContext().setCurrentLocale(locale);
		super.setLocale(request, response, locale);
	}

	
	
}
