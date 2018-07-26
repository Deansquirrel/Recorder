package com.yuansong.service;

import com.yuansong.pojo.EmpInfo;

public interface EmpService {
	
	public void addEmp(EmpInfo empInfo);
	
	public void delEmp(EmpInfo empInfo);
	
	public void disableEmp(EmpInfo empInfo);
	
	public void enableEmp(EmpInfo empInfo);
	
	public Integer getNewEmpInfoId();

}
