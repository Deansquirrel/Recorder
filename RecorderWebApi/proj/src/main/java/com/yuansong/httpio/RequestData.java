package com.yuansong.httpio;

public class RequestData {
	
	private String requestGuid;
	
	private String clientName;
	
	private String clientVersion;
	
	private String functionModel;
	
	private String functionKey;
	
	private RequestContent requestContent;

	public String getRequestGuid() {
		return requestGuid;
	}

	public void setRequestGuid(String requestGuid) {
		this.requestGuid = requestGuid;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getFunctionModel() {
		return functionModel;
	}

	public void setFunctionModel(String functionModel) {
		this.functionModel = functionModel;
	}

	public String getFunctionKey() {
		return functionKey;
	}

	public void setFunctionKey(String functionKey) {
		this.functionKey = functionKey;
	}

	public RequestContent getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(RequestContent requestContent) {
		this.requestContent = requestContent;
	}

}
