package com.yuansong.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yuansong.dao.RequestData;
import com.yuansong.dao.ResponseData;

@Service
public class DataHandlerServiceImpl implements IDataHandlerService {
	
	private final Logger logger = Logger.getLogger(DataHandlerServiceImpl.class);
		
	@Override
	public ResponseData handler(RequestData requestData) {
		
		String requestGuid = requestData.getRequestGuid();
		logger.debug("requestGuid - " + requestGuid);
		if(requestGuid == null || requestGuid.equals("")) {
			return new ResponseData(
					"", 
					"1", 
					"", 
					"requestGuid不能为空", 
					requestData.getRequestContent().getScalarParBody(),
					requestData.getRequestContent().getRecordParBody());
		}
		
		String clientName = requestData.getClientName();
		logger.debug("clientName - " + clientName);
		if(clientName == null || clientName.equals("")) {
			return new ResponseData(
					requestGuid, 
					"1", 
					"", 
					"clientName不能为空", 
					requestData.getRequestContent().getScalarParBody(),
					requestData.getRequestContent().getRecordParBody());
		}
		
		String clientVersion = requestData.getClientVersion();
		logger.debug("clientVersion - " + clientVersion);
		if(clientVersion == null || clientVersion.equals("")) {
			return new ResponseData(
					requestGuid, 
					"1", 
					"", 
					"clientVersion不能为空", 
					requestData.getRequestContent().getScalarParBody(),
					requestData.getRequestContent().getRecordParBody());
		}
		
		String functionModel = requestData.getFunctionModel();
		logger.debug("functionModel - " + functionModel);
		if(functionModel == null || functionModel.equals("")) {
			return new ResponseData(
					requestGuid, 
					"1", 
					"", 
					"functionModel不能为空",
					requestData.getRequestContent().getScalarParBody(),
					requestData.getRequestContent().getRecordParBody());
		}
		
		String functionKey = requestData.getFunctionKey();
		logger.debug("functionKey - " + functionKey);
		if(functionKey == null || functionKey.equals("")) {
			return new ResponseData(
					requestGuid, 
					"1", 
					"", 
					"functionKey不能为空", 
					requestData.getRequestContent().getScalarParBody(),
					requestData.getRequestContent().getRecordParBody());
		}
		
		if(!checkScalarPar(requestData)) {
			return new ResponseData(
					requestGuid, 
					"1", 
					"", 
					"请求内容中缺少指定的参数值", 
					requestData.getRequestContent().getScalarParBody(),
					requestData.getRequestContent().getRecordParBody());
		}
		
		if(!checkRecordPar(requestData)) {
			return new ResponseData(
					requestGuid, 
					"1", 
					"", 
					"请求内容中缺少指定的记录集", 
					requestData.getRequestContent().getScalarParBody(),
					requestData.getRequestContent().getRecordParBody());			
		}
		
		return null;		
	}
	
	private boolean checkScalarPar(RequestData requestData) {
		return false;
	}
	
	private boolean checkRecordPar(RequestData requestData) {
		return false;
	}
	
}
