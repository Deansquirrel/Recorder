package com.yuansong.service;

import org.springframework.stereotype.Service;

@Service
public class DataFormatServiceImpl implements IDataFormatService {

	@Override
	public String IFormat(String data) {
		return data;
	}

	@Override
	public String OFormat(String data) {
		return data;
	}

}
