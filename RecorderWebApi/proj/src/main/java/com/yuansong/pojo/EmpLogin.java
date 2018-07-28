package com.yuansong.pojo;

import java.util.Date;

public class EmpLogin {
	
	private Integer userId;
	private String loginName;
	private String userPwd;
	private Date addTime;
	private Date lastUpdate;
	private boolean deleted;
	
	public EmpLogin() {
		
	}
	
	public EmpLogin(
			Integer userId,
			String loginName,
			String userPwd,
			Date addTime,
			Date lastUpdate,
			boolean deleted
			) {
		this.userId = userId;
		this.loginName = loginName;
		this.userPwd = userPwd;
		this.addTime = addTime;
		this.lastUpdate = lastUpdate;
		this.deleted = deleted;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
