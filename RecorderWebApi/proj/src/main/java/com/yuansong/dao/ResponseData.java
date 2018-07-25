package com.yuansong.dao;

import java.util.Map;

public class ResponseData {
	
	private String responseGuid;
	
	private String errCode;
	
	private String errType;
	
	private String errDesc;
	
	private ResponseContent responseContent;
	
	public ResponseData() {
		
	}
	
	public ResponseData(
			String responseGuid,
			String errCode,
			String errType,
			String errDesc,
			Map<String, String> scalarParBody,
			Map<String, Map<String, String>> recordParBody
			) {
		this.responseGuid = responseGuid;
		this.errCode = errCode;
		this.errType = errType;
		this.errDesc = errDesc;
		this.responseContent = new ResponseContent(scalarParBody, recordParBody);
	}

	public String getResponseGuid() {
		return responseGuid;
	}

	public void setResponseGuid(String responseGuid) {
		this.responseGuid = responseGuid;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public ResponseContent getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(ResponseContent responseContent) {
		this.responseContent = responseContent;
	}

}
