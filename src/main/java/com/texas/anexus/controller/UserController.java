package com.texas.anexus.controller;

import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.model.User;
import com.texas.anexus.request.AdditionalRegisterCreationRequest;
import com.texas.anexus.request.UserEditRequest;
import com.texas.anexus.request.UserRegisterRequest;
import com.texas.anexus.response.UserResponse;
import com.texas.anexus.services.LoginService;
import com.texas.anexus.services.UserService;
import com.texas.anexus.util.InterestField;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@ApiOperation(value = "Register user")
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<Object> registerUser(
			@RequestBody UserRegisterRequest userCreationRequest) {
		User user = userService.registerUser(userCreationRequest);
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "Get User by Id")
	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@RequestParam Long id) {
		UserResponse us = userService.getUser(id);
		return new ResponseEntity<Object>(us, HttpStatus.OK);

	}

	@ApiOperation(value = "Get User by FirstName")
	@RequestMapping(value = "firstName/{firstName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserByUsername(@PathVariable String firstName,
			@RequestHeader Long loginId) {
		List<UserResponse> ur = userService.getUserByUsername(firstName, loginId);
		return new ResponseEntity<Object>(ur, HttpStatus.OK);
	}

	@ApiOperation(value = "Get All users")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getUser() {
		List<UserResponse> us = userService.getAllUser();
		return new ResponseEntity<Object>(us, HttpStatus.OK);

	}

	@ApiOperation(value = "Edit user Info")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> editUser(@RequestHeader Long id,
			@RequestBody UserEditRequest userEditRequest) {

		userService.editUser(id, userEditRequest);
		return new ResponseEntity<Object>("Edited Successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "Delete the user")
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@RequestHeader Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<Object>("Edited Successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "show the list of interestFields")
	@RequestMapping(value = "interestFields", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllInterestFields() {

		// List<InterestField> al = new ArrayList<InterestField>();
		EnumSet<InterestField> interestFields = EnumSet.allOf(InterestField.class);
		System.out.println(interestFields);
		return new ResponseEntity<Object>(interestFields, HttpStatus.OK);
	}

//	/* API for uploading user profile picture */
//
//	@ApiOperation(value = "Upload profile picture")
//	@RequestMapping(value = "uploadPic", method = RequestMethod.POST)
//	public ResponseEntity<Object> uploadImage(@RequestBody String profilePicture,
//			@RequestHeader Long loginId) {
//		userService.uploadProfilePicture(profilePicture, loginId);
//		return new ResponseEntity<Object>("Profile Picture Updated Successfully",
//				HttpStatus.OK);
//
//	}
//
//	@ApiOperation(value = "Register user")
//	@RequestMapping(value = "additionalRegister", method = RequestMethod.POST)
//	public ResponseEntity<Object> registerUser(
//			@RequestBody AdditionalRegisterCreationRequest addUserCreationRequest,
//			@RequestHeader Long LoginId) {
//		userService.addRegisterUser(addUserCreationRequest, LoginId);
//		return new ResponseEntity<Object>("Registered Successfully", HttpStatus.OK);
//	}

}
