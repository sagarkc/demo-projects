package com.mercuria.etl.mgr.web.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.mercuria.etl.mgr.web.shared.model.JobMonitorData;
import com.mercuria.etl.mgr.web.shared.model.JobMonitorDataFactory;
import com.mercuria.etl.mgr.web.shared.model.JsonDataCollection;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.util.JSONEncoder;

public class JSONDataParser {

	
	public static JsonDataCollection<JavaScriptObject> parseJsonToJSObject(String jsonString){
		
		//XJSONDataSource dataSource = new  XJSONDataSource(JSONEncoder.decode(jsonString));
		
		
		JsonDataCollection<JavaScriptObject> dataCollection = new JsonDataCollection<JavaScriptObject>();
		
		List<JavaScriptObject> data = new ArrayList<JavaScriptObject>();
		//JavaScriptObject jso = JSONEncoder.decode(jsonString);
		
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
					data.add(JSONEncoder.decode(dataValue.toString()));
				}
			}
			dataCollection.setRecords(data);
		}
		
		return dataCollection;
	}
	
	public static List<JobMonitorData> getJobMonitorData(String jsonText){
		JobMonitorDataFactory factory = GWT.create(JobMonitorDataFactory.class);
		List<JobMonitorData> list = new ArrayList<JobMonitorData>();
		if(JsonUtils.safeToEval(jsonText)){
			JSONValue jsonValue = JSONParser.parseStrict(jsonText);
			JSONObject jsonObject;
			if ((jsonObject = jsonValue.isObject()) == null) {
				return list;
			}
			
			
			jsonValue = jsonObject.get(JsonDataCollection.RECORD_FIELD);
			JSONArray jsonArray = jsonValue.isArray();
			if(null != jsonArray){
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject dataValue =  jsonArray.get(i).isObject();
					JobMonitorData data = AutoBeanCodex.decode(factory, JobMonitorData.class, dataValue.toString()).as();
					list.add(data);
				}
			}
		}
		return list;
	}
	
	
}
