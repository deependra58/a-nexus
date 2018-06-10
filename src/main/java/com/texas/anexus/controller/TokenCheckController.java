package com.texas.anexus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.services.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/tokenCheck")
public class TokenCheckController {
	
	@Autowired
	private LoginService loginService;
	@ApiOperation(value = "Check token")
	@RequestMapping(value = "checkToken", method = RequestMethod.GET)
	public ResponseEntity<Object> checkToken(@RequestHeader String token) {
		loginService.isValidToken(token);
		return new ResponseEntity<Object>("Valid Token", HttpStatus.OK);
	}
}
