/**
 * 
 */
package net.sf.jquery.tags.ui.flexigrid.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;

import net.sf.jquery.tags.common.AbstractViewTag;
import net.sf.jquery.tags.common.MessageContext;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class FlexigridColumnViewTag extends AbstractViewTag implements
		DynamicAttributes {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8202656326565685659L;

	private Map<String, String> dynamicAttributes = new HashMap<String, String>();

	private String display;
	private String name;
	private int width;
	private boolean sortable;
	private String align;
	
	/**
	 * 
	 */
	public FlexigridColumnViewTag() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, String> getDynamicAttributes() {
		return dynamicAttributes;
	}

	public void setDynamicAttributes(Map<String, String> dynamicAttributes) {
		this.dynamicAttributes = dynamicAttributes;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		if(null != display && display.length() > 0){
			if(display.startsWith("key:")){
				String key  = display.substring(display.indexOf(':')+1);
				display = MessageContext.getContext().getMessage(key);
			}
		}
		this.display = display;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setDynamicAttribute(String arg0, String key, Object value)
			throws JspException {
		dynamicAttributes.put(key.toLowerCase(), String.valueOf(value));
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			Tag pTag = getParent();
			if(null == pTag)
				throw new JspException("Parent tag <flexigrid> not found!!!");
			if(pTag instanceof FlexigridViewTag){
				FlexigridViewTag parentTag = (FlexigridViewTag) pTag;
				parentTag.getColumnModelContent().append("{")
					.append("display: '").append(getDisplay())
					.append("', name : '").append(name)
					.append("', width : ").append(width)
					.append(", sortable : ").append(sortable)
					.append(", align: '").append(align).append("'")
				.append("},");
				
			} else {
				throw new JspException("Parent tag <flexigrid> not found!!!");
			}
			pageContext.getOut().write("");
		} catch (IOException e) {
			pageContext.getServletContext().log(e.getLocalizedMessage(), e);
		}

		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		
		return EVAL_PAGE;
	}
}
