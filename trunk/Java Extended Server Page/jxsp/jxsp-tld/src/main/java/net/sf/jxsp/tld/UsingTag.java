package net.sf.jxsp.tld;

import javax.servlet.jsp.JspException;

import net.sf.jxsp.core.tag.TagGenerator;
import net.sf.jxsp.core.tag.TagsContext;

public class UsingTag extends AbstractTag {

	private String htmlVersion;
	
	public UsingTag() {
		htmlVersion = TagGenerator.HTML_4;
	}
	
	public String getHtmlVersion() {
		return htmlVersion;
	}

	public void setHtmlVersion(String htmlVersion) {
		this.htmlVersion = htmlVersion;
	}

	@Override
	public int doStartTag() throws JspException {
		TagsContext.getContext().setUsingHtmlVersion(htmlVersion);
		return SKIP_BODY;
	}

	
	
}
