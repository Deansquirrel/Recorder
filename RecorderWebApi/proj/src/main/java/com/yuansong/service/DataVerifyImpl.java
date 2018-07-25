package com.yuansong.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class DataVerifyImpl implements IDataVerifyService {
	
	private final Logger logger = Logger.getLogger(DataVerifyImpl.class);
	
	private Map<String, Map<String, List<String>>> scalarParList;
	private Map<String, Map<String, Map<String, List<String>>>> recordParList;
	
	private Gson mGson = new Gson();
	
	public DataVerifyImpl() {
		scalarParList = new HashMap<String, Map<String, List<String>>>();
		recordParList = new HashMap<String, Map<String, Map<String, List<String>>>>();
		
		String scalarPar = "" + 
				"{" + 
				"	\"modelA\": {" + 
				"		\"keyA\": [\"sA\", \"sB\", \"sC\", \"sD\"]," +
				"		\"keyB\": [\"sA\", \"sB\", \"sC\", \"sD\"]" +
				"	}," + 
				"	\"modelB\": {" + 
				"		\"keyA\": [\"sA\", \"sB\", \"sC\", \"sD\"]," +
				"		\"keyB\": [\"sA\", \"sB\", \"sC\", \"sD\"]" +
				"	}" + 
				"}";
		
		scalarParList = mGson.fromJson(scalarPar, new TypeToken<Map<String, Map<String, List<String>>>>(){}.getType());
		
		String recordPar = "" + 
				"{" + 
				"	\"modelA\": {" + 
				"		\"keyA\": {" + 
				"			\"tableA\": [\"rA\", \"rB\", \"rC\"]" + 
				"		}" + 
				"	}" + 
				"}";
		
		recordParList = mGson.fromJson(recordPar, new TypeToken<Map<String, Map<String, Map<String, List<String>>>>>(){}.getType());

	}

	@Override
	public String getScalarParVerify(String model, String key) throws Exception {
//		if(list.containsKey(model)) {
//			if(list.get(model).containsKey(key)) {
//				return list.get(model).get(key).get("ScalarParVerify");
//			}
//			else {
//				throw new Exception("配置文件中不包含指定的Key（" + key +  "）");				
//			}
//		}
//		else {
//			throw new Exception("配置文件中不包含指定的Model（" + model +  "）");
//		}
		return "";
	}

	@Override
	public String getRecordParVerify(String model, String key) throws Exception {
//		if(list.containsKey(model)) {
//			if(list.get(model).containsKey(key)) {
//				return list.get(model).get(key).get("RecordParVerify");
//			}
//			else {
//				throw new Exception("配置文件中不包含指定的Key（" + key +  "）");				
//			}
//		}
//		else {
//			throw new Exception("配置文件中不包含指定的Model（" + model +  "）");
//		}
		return "";
	}

	@Override
	public boolean checkScalarPar(String model, String key, Map<String, String> scalarParBody) {
//		String scalarParVerify;
//		try{
//			scalarParVerify = this.getScalarParVerify(model, key);
//		}
//		catch(Exception ex) {
//			logger.warn(ex.getMessage());
//			return false;
//		}
//		if(scalarParVerify.equals("")) {
//			return true;
//		}
//		String[] list = scalarParVerify.split("|");
//		for(int i= 0 ;i<list.length;i++) {
//			if(!scalarParBody.containsKey(list[i])){
//				logger.warn("请求数据中不包含所需的参数（" + list[i]  + "）");
//				return false;
//			}
//		}
		return true;
	}

	@Override
	public boolean checkRecordPar(String model, String key, Map<String, Map<String, String>> recordParBody) {
//		String recordParVerify;
//		try {
//			recordParVerify = this.getRecordParVerify(model, key);
//		}
//		catch(Exception ex) {
//			logger.warn(ex.getMessage());
//			return false;
//		}
//		
//		if(recordParVerify.equals("")) {
//			return true;
//		}
//		String[] list = recordParVerify.split("|||");
//		for(int i=0;i<list.length;i++) {
//			
//		}
//		
//		
		
		return false;
	}

}
