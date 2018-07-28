package com.yuansong.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuansong.common.CommonFun;
import com.yuansong.pojo.EmpInfo;
import com.yuansong.pojo.EmpLogin;
import com.yuansong.repository.EmpInfoRepository;
import com.yuansong.repository.EmpLoginRepository;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {
	
//	private final Logger logger = Logger.getLogger(EmpServiceImpl.class);
	
	@Autowired
	private EmpInfoRepository empInfoRepository;
	
	@Autowired
	private EmpLoginRepository empLoginRepository;

	@Override
	public void addEmp(String userName) throws Exception {
		if(isUserNameExist(userName)) {
			throw new Exception("已存在名称为【" + userName + "】的用户");
		}
		else {
			EmpInfo empInfo = new EmpInfo(
					empInfoRepository.getNextId(),
					userName,
					new Date(),
					new Date(),
					false);
			empInfoRepository.add(empInfo);
		}
	}

	@Override
	public void delEmp(Integer id) {
		EmpInfo empInfo = empInfoRepository.get(id);
		if(empInfo == null) {
			return;
		}
		empInfoRepository.del(empInfo);	
	}

	@Override
	public void disableEmp(Integer id) {
		EmpInfo empInfo = empInfoRepository.get(id);
		if(empInfo == null) {
			return;
		}
		empInfo.setDeleted(true);
		empInfoRepository.update(empInfo);
	}

	@Override
	public void enableEmp(Integer id) throws Exception {
		EmpInfo empInfo = empInfoRepository.get(id);
		if(empInfo == null) {
			return;
		}
		if(isUserNameExist(empInfo.getUserName())) {
			throw new Exception("已存在名称为【" + empInfo.getUserName() + "】的用户");
		}
		else {
			empInfo.setDeleted(false);
			empInfoRepository.update(empInfo);
		}
	}

//	@Override
//	public List<EmpInfo> getEmpList() {
//		return empInfoRepository.getList();
//	}
//
//	@Override
//	public List<EmpInfo> getEmpList(boolean delete) {
//		return empInfoRepository.getList(delete);
//	}
	
	private boolean isUserNameExist(String name) {
		for(EmpInfo emp : empInfoRepository.getList(false)) {
			if(emp.getUserName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkUser(String loginName, String userPwd) {
		String pwd = CommonFun.md5Encode(userPwd);
		for(EmpLogin emp : empLoginRepository.getList(false)) {
			if(emp.getLoginName().equals(loginName) && emp.getUserPwd().equals(pwd)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Integer getUserId(String loginName) {
		for(EmpLogin emp : empLoginRepository.getList(false)) {
			if(emp.getLoginName().equals(loginName)) {
				return emp.getUserId();
			}
		}
		return null;
	}

	@Override
	public void addEmpLogin(Integer userId, String userName, String userPwd) throws Exception {
		if(isLoginNameExist(userName)) {
			throw new Exception("登录名【" + userName + "】已存在");
		}
		else {
			EmpLogin empLogin = new EmpLogin(
					userId,
					userName,
					CommonFun.md5Encode(userPwd),
					new Date(),
					new Date(),
					false);
			empLoginRepository.add(empLogin);
		}
		
	}

	@Override
	public void delEmpLogin(Integer id) {
		EmpLogin empLogin = empLoginRepository.get(id);
		if(empLogin != null) {
			empLoginRepository.del(empLogin);
		}
	}

	@Override
	public void disableEmpLogin(Integer id) {
		EmpLogin empLogin = empLoginRepository.get(id);
		if(empLogin == null) {
			return;
		}
		empLogin.setDeleted(true);
		empLoginRepository.update(empLogin);
	}

	@Override
	public void enableEmpLogin(Integer id) throws Exception {
		EmpLogin empLogin = empLoginRepository.get(id);
		if(empLogin == null) {
			return;
		}
		if(isLoginNameExist(empLogin.getLoginName())) {
			throw new Exception("登录名【" + empLogin.getLoginName() + "】已存在");
		}
		empLogin.setDeleted(false);
		empLoginRepository.update(empLogin);
	}
	
	private boolean isLoginNameExist(String loginName) {
		List<EmpLogin> list = empLoginRepository.getList(false);
		for(EmpLogin emp : list) {
			if(emp.getLoginName().equals(loginName)) {
				return true;
			}
		}
		return false;
	}

}
