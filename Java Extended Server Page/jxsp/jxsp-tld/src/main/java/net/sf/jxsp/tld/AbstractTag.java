package net.sf.jxsp.tld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class AbstractTag  extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8733378144500824721L;

	/**
	 * 
	 */
	public AbstractTag() {
		
	}

	
	public final HttpServletRequest getHttpServletRequest() {
		if(null != pageContext && null != pageContext.getRequest()){
			if(pageContext.getRequest() instanceof HttpServletRequest)
				return (HttpServletRequest)pageContext.getRequest();
		}
		return null;
	}
}