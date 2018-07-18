package com.yuansong.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import com.google.gson.Gson;
import com.yuansong.service.IDataFormatService;

public class ExceptionHandler extends HandlerExceptionResolverComposite {
	
	private final Logger logger = Logger.getLogger(ExceptionHandler.class);
	
	private Gson mGson = new Gson();
	
	@Autowired
	private IDataFormatService dataFormatService;
	
	public ModelAndView resolveException(HttpServletRequest request,   
            HttpServletResponse response, Object handler, Exception ex) {
		
		logger.error(ex.getMessage());
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        ex.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
        
        Map<String,String> data = new HashMap<String,String>();
		data.put("errCode", "404");
		data.put("errDesc","Page not found.");
			
		data.put("errCode", "503");
		data.put("errDesc", ex.getMessage());
		data.put("errPath", sw.toString());
		
		model.put("info", dataFormatService.OFormat(mGson.toJson(data)));
		
        return new ModelAndView("errorPage",model);   
    }

}
