package com.yuansong.dao;

public class ResponseData {
	
	private String responseGuid;
	
	private String errCode;
	
	private String errType;
	
	private String errDesc;
	
	private ResponseContent responseContent;

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
