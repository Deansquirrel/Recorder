package com.yuansong.service;

public interface EmpService {
	
	public void addEmp(String userName) throws Exception ;
	
	public void delEmp(Integer id);
	
	public void disableEmp(Integer id);

	public void enableEmp(Integer id) throws Exception;

//	public List<EmpInfo> getEmpList();
//	
//	public List<EmpInfo> getEmpList(boolean delete);
	
	public boolean checkUser(String loginName, String userPwd);
	
	public Integer getUserId(String loginName);
	
	public void addEmpLogin(Integer userId, String userName, String userPwd) throws Exception ;
	
	public void delEmpLogin(Integer id);
	
	public void disableEmpLogin(Integer id);

	public void enableEmpLogin(Integer id) throws Exception;

}
