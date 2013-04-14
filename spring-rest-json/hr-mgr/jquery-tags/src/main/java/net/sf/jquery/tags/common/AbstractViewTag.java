/**
 * 
 */
package net.sf.jquery.tags.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import net.sf.jquery.tags.util.BrowserEncoderUtil;

/**
 * @author sabuj.das
 *
 */
public abstract class AbstractViewTag extends AbstractTag {

	private final static long serialVersionUID = 17989876876L;
	
	
	private String htmlTagName;
	
	public String getHtmlTagName() {
		return htmlTagName;
	}

	public void setHtmlTagName(String htmlTagName) {
		this.htmlTagName = htmlTagName;
	}

	public String buildUri(String page) {
		String uri = page;

		if (!page.startsWith("/")) {
			uri = pageContext.getServletContext().getContextPath() + "/" + page;
		}

		return uri;
	}

	
	protected String buildStartHtml(final Map<String, String> attributes) {
		StringBuilder sb = new StringBuilder();

		sb.append("<").append(getHtmlTagName()).append(" ");

		for (String name : attributes.keySet()) {
			String value = attributes.get(name);

			sb.append(BrowserEncoderUtil.encodeForAttribute(name));
			sb.append('=');
			sb.append('"');
			sb.append(value);

			sb.append('"');
			sb.append(' ');
		}

		sb.append(">");

		return sb.toString();
	}
	
	public void writeEndTag() throws JspException {
		try {
			pageContext.getOut().write("</" + getHtmlTagName() +">");
		} catch (IOException e) {
			pageContext.getServletContext().log(e.getLocalizedMessage(), e);
		}

	}
	
	
	public final boolean isDynamicAttribute(String key) {
		if(TagsConstants.ATTR_UI_FIELD_NAME.equalsIgnoreCase(key)
				|| TagsConstants.ATTR_HTML_TAG_NAME.equalsIgnoreCase(key)
				|| TagsConstants.ATTR_TARGET_FIELD_NAME.equalsIgnoreCase(key))
			return false;
		return true;
	}
	
	
}
