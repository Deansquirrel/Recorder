package com.yuansong.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yuansong.httpio.RequestData;
import com.yuansong.service.DataFormatService;

@Controller
@RequestMapping(value="/Test")
public class TestController {
	
	private final Logger logger = Logger.getLogger(TestController.class);
	
	private final Gson mGson = new Gson();
	
	@Autowired
	private DataFormatService dataFormatService;
	
	@RequestMapping(value="/GetRequestModel")
	public ModelAndView getRequestModel(){
		logger.debug("TestController getRequestModel");
		
		Map<String,String> model = new HashMap<String,String>();
		
		RequestData requestData = new RequestData();
		requestData.setRequestGuid("e5acd4e3-42ae-4fff-a7ea-8a2aaa8406eb".toUpperCase());
		requestData.setClientName("Android");
		requestData.setClientVersion("0.0.0.0 Build20000000");
		requestData.setFunctionModel("Record");
		requestData.setFunctionKey("add");
		
//		RequestContent requestContent = new RequestContent();
		model.put("info", dataFormatService.OFormat(mGson.toJson(requestData)));
		
		return new ModelAndView("responsePage", model);
	}

}
