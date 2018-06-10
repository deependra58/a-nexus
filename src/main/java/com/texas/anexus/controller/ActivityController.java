package com.texas.anexus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.repository.FollowerRepository;
import com.texas.anexus.repository.ScheduleRepository;
import com.texas.anexus.repository.UserRepository;
import com.texas.anexus.response.ActivityResponse;
import com.texas.anexus.services.ActivityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })
	@ApiOperation(value = "Showing dashboard contents after login")
	@RequestMapping(value = "User Dashboard", method = RequestMethod.GET)
	public ResponseEntity<Object> showUserDashboardActivity(@RequestHeader Long userId) {

		List<ActivityResponse> ar = activityService.getUserDashBoardActivity(userId);
		return new ResponseEntity<Object>(ar, HttpStatus.OK);

	}

}
