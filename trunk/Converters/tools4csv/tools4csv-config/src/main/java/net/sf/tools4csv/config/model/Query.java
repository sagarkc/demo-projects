package net.sf.tools4csv.config.model;

public class Query {

	private boolean namedParam;
	private String text;
	
	public Query() {
		// TODO Auto-generated constructor stub
	}

	public boolean isNamedParam() {
		return namedParam;
	}

	public void setNamedParam(boolean namedParam) {
		this.namedParam = namedParam;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
