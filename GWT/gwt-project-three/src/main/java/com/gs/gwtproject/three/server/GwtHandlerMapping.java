/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.gwtproject.three.server.GwtHandlerMapping
 * Date     : Jun 12, 2013
 */

package com.gs.gwtproject.three.server;

import com.google.gwt.user.client.rpc.RemoteService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.AbstractDetectingUrlHandlerMapping;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
@Component
public class GwtHandlerMapping extends AbstractDetectingUrlHandlerMapping{

    @Override
    protected String[] determineUrlsForHandler(String beanName) {
        String[] urls = null;
        
        final Object bean = getApplicationContext().getBean(beanName);
        if(bean instanceof RemoteService){
            urls = new String[]{"/" + beanName};
        }
        return urls;
    }
   
}
