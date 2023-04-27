package com.mysite.core.models;

public interface OSGIconfig {
	public String getServiceName();
	public int getServiceCount();
	public boolean isLiveData();
	public String getRunModes();
}
