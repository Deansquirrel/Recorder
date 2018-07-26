package com.yuansong.service;

public interface DataFormatService {
	
	/**
	 * 传入数据逆格式化
	 * @param data 传入数据
	 * @return 逆格式化后的传入数据
	 */
	public String IFormat(String data);
	
	/**
	 * 返回数据格式化
	 * @param data 待格式化的返回数据
	 * @return 格式化后的返回数据
	 */
	public String OFormat(String data);

}
