package com.texas.anexus.controller;

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
import com.texas.anexus.services.LoginService;
import com.texas.anexus.services.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/commons")
public class CommonController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;
	
	/*API for additional Registration*/

<<<<<<< HEAD
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })

	@ApiOperation(value = "Register user")
	@RequestMapping(value = "additionalRegister", method = RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@RequestBody AdditionalRegisterCreationRequest addUserCreationRequest,@RequestHeader Long LoginId) {
		
		userService.addRegisterUser(addUserCreationRequest,LoginId);
		return new ResponseEntity<Object>("Registered Successfully", HttpStatus.OK);
	}

	/*API for uploading user profile picture*/
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })

	@ApiOperation(value = "Upload profile picture")
	@RequestMapping(value = "uploadPic", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadImage(@RequestBody String profilePicture, @RequestHeader Long loginId) {
		userService.uploadProfilePicture(profilePicture, loginId);
		return new ResponseEntity<Object>("Profile Picture Updated Successfully", HttpStatus.OK);

	}
=======
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })
//
//	@ApiOperation(value = "Register user")
//	@RequestMapping(value = "additionalRegister", method = RequestMethod.POST)
//	public ResponseEntity<Object> registerUser(@RequestBody AdditionalRegisterCreationRequest addUserCreationRequest,@RequestHeader Long LoginId) {
//		userService.addRegisterUser(addUserCreationRequest,LoginId);
//		return new ResponseEntity<Object>("Registered Successfully", HttpStatus.OK);
//	}

//	/*API for uploading user profile picture*/
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })
//
//	@ApiOperation(value = "Upload profile picture")
//	@RequestMapping(value = "uploadPic", method = RequestMethod.POST)
//	public ResponseEntity<Object> uploadImage(@RequestParam String profilePicture, @RequestHeader Long loginId) {
//		userService.uploadProfilePicture(profilePicture, loginId);
//		return new ResponseEntity<Object>("Profile Picture Updated Successfully", HttpStatus.OK);
//
//	}
>>>>>>> d10a1c2b7852a1795133c34705c7f01e380d4705
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })
	@ApiOperation(value="follow user")
	@RequestMapping(value="follow/{followId}",method=RequestMethod.POST)
	public ResponseEntity<Object> followUser(@RequestHeader Long loginId,@PathVariable Long followId){
		User user=userService.followingUser(loginId,followId);
		return new ResponseEntity<Object>("Successfully followed id "+user.getId(),HttpStatus.OK);
		
	}
	
	

	
	

}
