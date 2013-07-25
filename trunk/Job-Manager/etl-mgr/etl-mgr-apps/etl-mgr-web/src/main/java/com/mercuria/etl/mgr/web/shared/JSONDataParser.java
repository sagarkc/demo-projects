package com.mercuria.etl.mgr.web.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.mercuria.etl.mgr.web.shared.model.JsonDataCollection;

public class JSONDataParser {

	
	public static JsonDataCollection<JavaScriptObject> parseJsonToJSObject(String jsonString){
		JsonDataCollection<JavaScriptObject> dataCollection = new JsonDataCollection<JavaScriptObject>();
		
		List<JavaScriptObject> data = new ArrayList<JavaScriptObject>();
		
		if(JsonUtils.safeToEval(jsonString)){
			JSONValue jsonValue = JSONParser.parseStrict(jsonString);
			JSONObject jsonObject;
			if ((jsonObject = jsonValue.isObject()) == null) {
				return dataCollection;
			}
			
			jsonValue = jsonObject.get(JsonDataCollection.TOTAL_FIELD);
			if(null != jsonValue){
				dataCollection.setTotalRecords((long)jsonValue.isNumber().doubleValue());
			}
			jsonValue = jsonObject.get(JsonDataCollection.PAGE_FIELD);
			if(null != jsonValue){
				dataCollection.setTotalRecords((int)jsonValue.isNumber().doubleValue());
			}
			jsonValue = jsonObject.get(JsonDataCollection.RECORD_FIELD);
			JSONArray jsonArray = jsonValue.isArray();
			if(null != jsonArray){
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject dataValue =  jsonArray.get(i).isObject();
					data.add(dataValue.getJavaScriptObject());
				}
			}
			dataCollection.setRecords(data);
		}
		
		return dataCollection;
	}
	
}
