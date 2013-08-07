

package com.xchanging.etl.mgr.web.server;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.AbstractDetectingUrlHandlerMapping;

import com.google.gwt.user.client.rpc.RemoteService;
import com.xchanging.etl.mgr.web.WebConstants;

/**
 *
 * @author Sabuj Das 
 */
@Component
public class GwtHandlerMapping extends AbstractDetectingUrlHandlerMapping{

    @Override
    protected String[] determineUrlsForHandler(String beanName) {
        String[] urls = null;
        
        final Object bean = getApplicationContext().getBean(beanName);
        if(bean instanceof RemoteService){
            urls = new String[]{"/**/" + beanName + WebConstants.RPC_EXT };
        }
        return urls;
    }
   
}
