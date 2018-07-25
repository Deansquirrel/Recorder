package com.yuansong.service;

import java.util.Map;

public interface IDataVerifyService {
	
	public String getScalarParVerify(String model, String key) throws Exception;
	
	public String getRecordParVerify(String model, String key) throws Exception;
	
	public boolean checkScalarPar(String model, String key, Map<String, String> scalarParBody);
	
	public boolean checkRecordPar(String model, String key, Map<String, Map<String, String>> recordParBody);
	
}
