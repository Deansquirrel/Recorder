package com.yuansong.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yuansong.dao.RequestData;
import com.yuansong.service.DataHandlerServiceImpl;
import com.yuansong.service.IDataFormatService;
import com.yuansong.service.IDataVerifyService;

@Controller
@RequestMapping(value="/DataInterface")
public class DataInterfaceController {
	
	private final Logger logger = Logger.getLogger(DataInterfaceController.class);
	
	private Gson mGson = new Gson();
	
	@Autowired
	private IDataVerifyService dataVerifyService;
	
	@Autowired
	private IDataFormatService dataFormatService;
	
//	@RequestMapping(value="/TestMap")
//	public ModelAndView testMap(Map<String, Object> model){
//		logger.info("RootController test");
//		
//		for(String key : model.keySet()) {
//			logger.info(key);
//		}
//		
//		ResponseData responseData = new ResponseData();
//		responseData.setResponseGuid("Guid");
//		responseData.setErrCode("-1");
//		responseData.setErrType("1");
//		responseData.setErrDesc("errDesc");
//		ResponseContent responseContent = new ResponseContent();
//		responseContent.addScalarPar("k", "value");
//		responseContent.addRecordPar("TA", "ttt", "vvvvvv");
//		responseData.setResponseContent(responseContent);
//		
//		model.put("info",mGson.toJson(responseData));
//
//		return new ModelAndView("responsePage", model);
//	}
	
	@RequestMapping(value="/GetData",method = RequestMethod.POST)
	public ModelAndView getData(@RequestBody String data){
		logger.debug("DataInterfaceController get");
		logger.debug("data - " + data);
		String rData = dataFormatService.IFormat(data);
		logger.debug("real data - " + rData);
		
		RequestData requestData = mGson.fromJson(rData, RequestData.class);
		
//		ResponseData responseData = new ResponseData();
//		responseData.setResponseGuid("Guid");
//		responseData.setErrCode("-1");
//		responseData.setErrType("1");
//		responseData.setErrDesc("errDesc");
//		ResponseContent responseContent = new ResponseContent();
//		responseContent.addScalarPar("k", "value");
//		responseContent.addRecordPar("TA", "ttt", "vvvvvv");
//		responseData.setResponseContent(responseContent);
		
		DataHandlerServiceImpl dataHandler = new DataHandlerServiceImpl();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("info", dataFormatService.OFormat(mGson.toJson(dataHandler.handler(requestData))));

		return new ModelAndView("responsePage", model);
	}
	
}
