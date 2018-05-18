package com.texas.anexus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.request.UserEditRequest;
import com.texas.anexus.request.UserRegisterRequest;
import com.texas.anexus.response.UserResponse;
import com.texas.anexus.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Register user")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@RequestBody UserRegisterRequest userCreationRequest,
			@RequestParam(required = false) String ruralMunicipality,
			@RequestParam(required = false) String municipality, @RequestParam(required = false) String metropolitan,
			@RequestParam(required = false) String subMetropolitan) {
		userService.registerUser(userCreationRequest, ruralMunicipality, municipality, metropolitan, subMetropolitan);
		return new ResponseEntity<Object>("Registered Successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Get User by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@RequestParam Long id) {
		UserResponse us = userService.getUser(id);
		return new ResponseEntity<Object>(us, HttpStatus.OK);

	}

	@ApiOperation(value = "Get All users")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getUser() {
		List<UserResponse> us = userService.getAllUser();
		return new ResponseEntity<Object>(us, HttpStatus.OK);

	}

	@ApiOperation(value = "Edit user Info")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> editUser(@RequestHeader Long id,@RequestBody UserEditRequest userEditRequest) {

		userService.editUser(id,userEditRequest);
		return new ResponseEntity<Object>("Edited Successfully", HttpStatus.OK);

	}

}
