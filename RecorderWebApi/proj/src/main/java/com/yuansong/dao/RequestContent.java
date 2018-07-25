package com.yuansong.dao;

import java.util.HashMap;
import java.util.Map;

public class RequestContent {
	
	private Map<String, String> scalarParBody;
	private Map<String, Map<String, String>> recordParBody;
	
	public RequestContent() {
		scalarParBody = new HashMap<String,String>();		
		recordParBody = new HashMap<String, Map<String, String>>();
	}
	
	public Map<String, String> getScalarParBody() {
		return scalarParBody;
	}

	public void setScalarParBody(Map<String, String> scalarParBody) {
		this.scalarParBody = scalarParBody;
	}
	
	public Map<String, Map<String, String>> getRecordParBody() {
		return recordParBody;
	}

	public void setRecordParBody(Map<String, Map<String, String>> recordParBody) {
		this.recordParBody = recordParBody;
	}

	public void addScalarPar(String key, String value) {
		scalarParBody.put(key, value);
	}
	
	public void addScalarPar(Map<String, String> map) {
		for(String key : map.keySet()) {
			scalarParBody.put(key, map.get(key));
		}
	}
	
	public void delScalarPar(String key) {
		scalarParBody.remove(key);
	}
	
	public void addRecordPar(String listKey, Map<String, String> map) {
		Map<String, String> list;
		if(recordParBody.containsKey(listKey)) {
			list = recordParBody.get(listKey);
			for(String key : map.keySet()){
				list.put(key, map.get(key));
			}
		}
		else {
			list = map;
		}
		recordParBody.put(listKey, list);
	}
	
	public void addRecordPar(String listKey, String key, String value) {
		Map<String, String> list;
		if(recordParBody.containsKey(listKey)) {
			list = recordParBody.get(listKey);			
		}
		else {
			list = new HashMap<String, String>();
		}
		list.put(key, value);
		recordParBody.put(listKey, list);
	}
	
	public void delRecordPar(String listKey, String key) {
		if(recordParBody.containsKey(listKey)) {
			Map<String, String> list = recordParBody.get(listKey);
			if(list.containsKey(key)) {
				list.remove(key);
			}
		}
	}
	
	public void delRecordPar(String listKey) {
		if(recordParBody.containsKey(listKey)) {
			recordParBody.remove(listKey);
		}
	}

}
