package net.sf.jxsp.core.tag;

public final class TagsContext {

	private static TagsContext context;
	
	private TagsContext(){}

	public static TagsContext getContext() {
		if(null != context)
			return context;
		synchronized (TagsContext.class) {
			context = new TagsContext();
		}
		return context;
	}
	
	private String usingHtmlVersion = "4";

	public String getUsingHtmlVersion() {
		return usingHtmlVersion;
	}

	public void setUsingHtmlVersion(String usingHtmlVersion) {
		this.usingHtmlVersion = usingHtmlVersion;
	}
	
	
	
}
