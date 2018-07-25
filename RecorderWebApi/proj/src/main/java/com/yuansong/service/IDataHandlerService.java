package com.yuansong.service;

import com.yuansong.dao.RequestData;
import com.yuansong.dao.ResponseData;

public interface IDataHandlerService {
	
	public ResponseData handler(RequestData requestData);

}
