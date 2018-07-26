package com.yuansong.pojo;

import java.util.Date;

public class EmpInfo {
	
	private Integer userId;
	private String userName;
	private Date addTime;
	private Date lastUpdate;
	private boolean delete;
	
	public EmpInfo() {
		
	}
	
	public EmpInfo(Integer userId, String userName, Date addTime, Date lastUpdate, boolean delete) {
		this.userId = userId;
		this.userName = userName;
		this.addTime = addTime;
		this.lastUpdate = lastUpdate;
		this.delete = delete;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

}
