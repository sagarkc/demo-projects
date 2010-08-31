package com.gs.rm.model;

public enum EntityType {
	
	PERSON,
	USER,
	DEFAULT;
	
	public String toString() {
		String constantCode = null;
		switch (this) {
		
		case PERSON:
			constantCode = "Person";
			break;
		case USER:
			constantCode = "User";
			break;
		
		default:
			constantCode = null;
		}
		return constantCode;
	}
}
