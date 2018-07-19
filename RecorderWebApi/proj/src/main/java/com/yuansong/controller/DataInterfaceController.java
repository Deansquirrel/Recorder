package com.yuansong.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yuansong.dao.ResponseContent;
import com.yuansong.dao.ResponseData;

@Controller
@RequestMapping(value="/DataInterface")
public class DataInterfaceController {
	
	private final Logger logger = Logger.getLogger(DataInterfaceController.class);
	
	private Gson mGson = new Gson();
	
	@RequestMapping(value="/TestMap")
	public ModelAndView testMap(Map<String, Object> model){
		logger.info("RootController test");
		
		for(String key : model.keySet()) {
			logger.info(key);
		}
		
		ResponseData responseData = new ResponseData();
		responseData.setResponseGuid("Guid");
		responseData.setErrCode("-1");
		responseData.setErrType("1");
		responseData.setErrDesc("errDesc");
		ResponseContent responseContent = new ResponseContent();
		responseContent.addScalarPar("k", "value");
		responseContent.addRecordPar("TA", "ttt", "vvvvvv");
		responseData.setResponseContent(responseContent);
		
		model.put("info",mGson.toJson(responseData));

		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/TestStr",method = RequestMethod.POST)
	public ModelAndView testStr(@RequestBody String data){
		logger.info("RootController test");
		
		logger.info("data - " + data);
		
		ResponseData responseData = new ResponseData();
		responseData.setResponseGuid("Guid");
		responseData.setErrCode("-1");
		responseData.setErrType("1");
		responseData.setErrDesc("errDesc");
		ResponseContent responseContent = new ResponseContent();
		responseContent.addScalarPar("k", "value");
		responseContent.addRecordPar("TA", "ttt", "vvvvvv");
		responseData.setResponseContent(responseContent);
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("info",mGson.toJson(responseData));

		return new ModelAndView("responsePage", model);
	}

}
