package com.yuansong.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuansong.taskjob.TestJob;

@Controller
@RequestMapping(value="/Scheduler")
public class SchedulerController {
	
	private final Logger logger = Logger.getLogger(SchedulerController.class);
	
	@Autowired
    TaskScheduler scheduler;
	
	@RequestMapping(value="/Start")
	public ModelAndView start(Map<String, Object> model){
		logger.info("Scheduler Start");
		
		scheduler.schedule(new TestJob(), new CronTrigger("0 0/1 * * * ?"));
		model.put("info", "start OK");
		
		return new ModelAndView("responsePage", model);
	}

}
