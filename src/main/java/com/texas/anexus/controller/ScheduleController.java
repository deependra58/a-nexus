package com.texas.anexus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.request.ScheduleCreationRequest;
import com.texas.anexus.response.ScheduleResponse;
import com.texas.anexus.services.ScheduleService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/schedules")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@ApiOperation(value="post schedule")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postSchedule(@RequestBody ScheduleCreationRequest scheduleCreationRequest, @RequestHeader Long loginId){
		
		scheduleService.postSchedule(scheduleCreationRequest,loginId);
		return new ResponseEntity<Object>("Schedule Posted successfully",HttpStatus.OK);
			
	}
	
	/*userId is that userId whose schedule i want to see*/
	@ApiOperation(value="get schedule")
	@RequestMapping(value="/{userId}",method=RequestMethod.GET)
	private ResponseEntity<Object> getSchedule(@PathVariable Long userId, @RequestHeader Long loginId){
		List<ScheduleResponse> scheduleResponse=scheduleService.getSchedule(userId,loginId);
		return new ResponseEntity<Object>(scheduleResponse,HttpStatus.OK);
		
	}
	
	

	
}
