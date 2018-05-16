package com.texas.anexus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/schedules")
public class ScheduleController {
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> hello(){
			return new ResponseEntity<Object>("Successfully done",HttpStatus.OK);
	}

	
}
