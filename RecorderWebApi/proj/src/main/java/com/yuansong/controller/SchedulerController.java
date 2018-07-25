package com.yuansong.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Scheduler")
public class SchedulerController {
	
	private final Logger logger = Logger.getLogger(SchedulerController.class);
	
	@Autowired
    TaskScheduler scheduler;
	
	public SchedulerController(){
		logger.debug("SchedulerController Init");
	}
	
//	@RequestMapping(value="/Start")
//	public ModelAndView start(Map<String, Object> model){
//		logger.info("Scheduler Start");
//		
//		scheduler.schedule(new TestJob(), new CronTrigger("0 0/1 * * * ?"));
//		model.put("info", "start OK");
//		
//		return new ModelAndView("responsePage", model);
//	}

}
