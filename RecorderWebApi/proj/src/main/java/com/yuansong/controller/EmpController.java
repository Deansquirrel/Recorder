package com.yuansong.controller;

import java.util.Date;
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
import com.yuansong.service.DataFormatService;
import com.yuansong.service.EmpService;
import com.yuansong.pojo.EmpInfo;
import com.yuansong.pojo.form.EmpInfoAddForm;
import com.yuansong.pojo.form.EmpInfoDelForm;

@Controller
@RequestMapping(value="/Emp")
public class EmpController {
	
	private final Logger logger = Logger.getLogger(EmpController.class);
	
	@Autowired
	private DataFormatService dataFormatService;
	
	@Autowired
	private EmpService empService;
	
	private final Gson mGson = new Gson();
	
	@RequestMapping(value="/Add", method = RequestMethod.POST)
	public ModelAndView add(@RequestBody String data){
		logger.debug("EmpController add");
		
		EmpInfoAddForm form = mGson.fromJson(data, EmpInfoAddForm.class);
		
		empService.addEmp(new EmpInfo(
				empService.getNewEmpInfoId(),
				form.getName(),
				new Date(),
				new Date(),
				false));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errCode", 0);
		result.put("errMsg", "OK");
		
		
		Map<String,String> model = new HashMap<String,String>();
		model.put("info", dataFormatService.OFormat(mGson.toJson(result)));
		
		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/Del", method = RequestMethod.POST)
	public ModelAndView del(@RequestBody String data){
		logger.debug("EmpController del");
		
		EmpInfoDelForm form = mGson.fromJson(data, EmpInfoDelForm.class);
		empService.delEmp(new EmpInfo(form.getId(),null,null,null,false));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errCode", 0);
		result.put("errMsg", "OK");
		
		
		Map<String,String> model = new HashMap<String,String>();
		model.put("info", dataFormatService.OFormat(mGson.toJson(result)));
		
		return new ModelAndView("responsePage", model);
	}

}
