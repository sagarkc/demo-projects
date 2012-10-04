package net.sf.jxsp.tld;

public class AbstractUriTag extends AbstractViewTag {
	private static final long serialVersionUID = 7664462136498131636L;

	private String uri = null;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = buildUri(uri);
	}
}
