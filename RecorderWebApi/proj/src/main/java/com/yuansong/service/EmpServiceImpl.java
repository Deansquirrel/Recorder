package com.yuansong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuansong.pojo.EmpInfo;
import com.yuansong.repository.EmpInfoRepository;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {
	
//	private final Logger logger = Logger.getLogger(EmpServiceImpl.class);
	
	@Autowired
	private EmpInfoRepository empInfoRepository;

	@Override
	public void addEmp(EmpInfo empInfo) {
		empInfoRepository.add(empInfo);
	}

	@Override
	public void delEmp(EmpInfo empInfo) {
		empInfoRepository.del(empInfo, true);
	}

	@Override
	public void disableEmp(EmpInfo empInfo) {
		empInfoRepository.del(empInfo, false);
	}

	@Override
	public void enableEmp(EmpInfo empInfo) {
		empInfo.setDelete(false);
		empInfoRepository.update(empInfo);
	}

	@Override
	public Integer getNewEmpInfoId() {
		return 111;
	}

}
