package com.xchanging.etl.mgr.web.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class NavigationLink implements IsSerializable {

	private String title;
	private String url;

	public NavigationLink(String title, String url) {
		this.title = title;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
