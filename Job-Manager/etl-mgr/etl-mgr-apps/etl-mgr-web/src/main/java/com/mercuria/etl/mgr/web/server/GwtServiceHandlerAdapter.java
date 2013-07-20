
package com.mercuria.etl.mgr.web.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sabuj Das 
 */
@Component
public class GwtServiceHandlerAdapter extends RemoteServiceServlet
        implements HandlerAdapter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9902103008L;
	private static ThreadLocal<Object> handlerHolder = new ThreadLocal<Object>();

    public Object getCurrentHandler(){
        return handlerHolder.get();
    }
    
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof RemoteService);
    }

    @Override
    public ModelAndView handle(
            HttpServletRequest request, HttpServletResponse response, Object handler) 
            throws Exception {
        try {
            handlerHolder.set(handler);
            doPost(request, response);
        }
        finally {
            handlerHolder.set(null);
        }
        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1;
    }

    @Override
    public String processCall(String payload) throws SerializationException {
        String value = null;
        try {
            final RPCRequest request = RPC.decodeRequest(payload, 
                    getCurrentHandler().getClass());
            value = RPC.invokeAndEncodeResponse(
                    getCurrentHandler(), 
                    request.getMethod(), 
                    request.getParameters());
        }
        catch (Throwable t) {
            value = RPC.encodeResponseForFailure(null, t);
        }
        
        return value;
    }

    @Override
    protected void doUnexpectedFailure(Throwable e) {
        throw new RuntimeException(e);
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }
    
    
    
}
