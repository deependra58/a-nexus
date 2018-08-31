package com.texas.anexus.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.request.ForgetPasswordRequest;
import com.texas.anexus.request.LoginCreationRequest;
import com.texas.anexus.services.LoginService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("api/v1/logins")
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<Object>login(@RequestBody LoginCreationRequest loginRequestDto){
		Map<Object, Object> response=loginService.login(loginRequestDto);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public ResponseEntity<Object>logout(@RequestHeader Long userId){
		loginService.logout(userId);
		return new ResponseEntity<Object>("Logged Out Successfully",HttpStatus.OK);
	}
	
	/* Api for passowrd reset */
	@ApiOperation(value = "forget password", notes = "Api to forget password")
	@RequestMapping(value = "/forgetPassword/{email:.+}", method = RequestMethod.POST)
	public ResponseEntity<Object> forgetPassword(@PathVariable String email) {
		LOG.debug("Request Accepted for Reset password");
		Map<Object, Object> response = loginService.resetPassword(email);
		return new ResponseEntity<Object>("Check your email to reset your password.  " + response, HttpStatus.OK);
	}

	@ApiOperation(value = "Api to reset password", notes = "Api to reset password")
	@RequestMapping(value = "/resetPassword/{token}", method = RequestMethod.POST)
	public ResponseEntity<Object> resetPassword(@PathVariable("token") String token,
			@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
		LOG.debug("Request Accepted to reset password");
		loginService.resetForgetPassword(token, forgetPasswordRequest);
		return new ResponseEntity<Object>("Password Reset", HttpStatus.OK);
	}
}
