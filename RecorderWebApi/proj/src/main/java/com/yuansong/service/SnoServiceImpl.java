package com.yuansong.service;

import org.springframework.stereotype.Service;

import com.yuansong.common.IdWorker;

@Service
public class SnoServiceImpl implements SnoService {

	@Override
	public long nextId() {
		IdWorker idWorker = new IdWorker();
		return idWorker.nextId(); 
	}

}
