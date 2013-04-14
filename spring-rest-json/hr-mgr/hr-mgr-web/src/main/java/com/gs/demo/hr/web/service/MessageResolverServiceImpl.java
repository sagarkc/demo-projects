/**
 * File :: com.gs.demo.hr.web.service.MessageResolverServiceImpl
 * Date :: Apr 14, 2013
 */
package com.gs.demo.hr.web.service;

import net.sf.jquery.tags.common.MessageContext;
import net.sf.jsonizer.service.MessageResolverService;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MessageResolverServiceImpl implements MessageResolverService {

	/* (non-Javadoc)
	 * @see net.sf.jsonizer.service.MessageResolverService#resolveMessage(java.lang.String)
	 */
	@Override
	public String resolveMessage(String key) {
		return MessageContext.getContext().getMessage(key);
	}

}
