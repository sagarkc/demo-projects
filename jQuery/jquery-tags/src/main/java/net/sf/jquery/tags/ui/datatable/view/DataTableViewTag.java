package net.sf.jquery.tags.ui.datatable.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import net.sf.jquery.tags.common.AbstractViewTag;

public class DataTableViewTag extends AbstractViewTag implements
	DynamicAttributes {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8202655468765487987L;
	
	private Map<String, String> dynamicAttributes = new HashMap<String, String>();
	
	
	
	public DataTableViewTag() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDynamicAttribute(String arg0, String key, Object value)
			throws JspException {
		dynamicAttributes.put(key.toLowerCase(), String.valueOf(value));
	}
	
	
	
}
