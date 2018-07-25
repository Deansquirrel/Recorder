package com.yuansong.datahandler;

import java.util.HashMap;
import java.util.Map;

public class DataFormat {
	
	private Map<String, Map<String, Map<String, String>>> list;
	
	public DataFormat() {
		list = new HashMap<String, Map<String, Map<String, String>>>();
		
		Map<String, String> verifyStr;
		
		verifyStr = new HashMap<String, String>();
		verifyStr.put("ScalarParVerify", "A|B|C|D");
		verifyStr.put("RecordParVerify", "A|B|C|D");
		if(list.containsKey("model")) {
			list.get("model").put("key", verifyStr);
		}		
	}
	
	public String getScalarParVerify(String model, String key) throws Exception {
		if(list.containsKey(model)) {
			if(list.get(model).containsKey(key)) {
				return list.get(model).get(key).get("ScalarParVerify");
			}
			else {
				throw new Exception("配置文件中不包含指定的Key（" + key +  "）");				
			}
		}
		else {
			throw new Exception("配置文件中不包含指定的Model（" + model +  "）");
		}
	}

	public String getRecordParVerify(String model, String key) throws Exception {
		if(list.containsKey(model)) {
			if(list.get(model).containsKey(key)) {
				return list.get(model).get(key).get("RecordParVerify");
			}
			else {
				throw new Exception("配置文件中不包含指定的Key（" + key +  "）");				
			}
		}
		else {
			throw new Exception("配置文件中不包含指定的Model（" + model +  "）");
		}
	}

}
