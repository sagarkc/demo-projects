package com.mercuria.etl.mgr.client.core;

import java.util.HashMap;
import java.util.Map;

public final class ObjectFactory {

	private static final ObjectFactory instance = new ObjectFactory();
	
	private Map<String, Object> objectCache = new HashMap<String, Object>();
	
	public synchronized Object get(String name){
		return objectCache.get(name);
	}
	
	public synchronized void put(String name, Object value){
		objectCache.put(name, value);
	}

	public static ObjectFactory getInstance() {
		return instance;
	}
	
	
}
