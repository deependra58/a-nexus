package com.texas.anexus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.request.AdditionalRegisterCreationRequest;
import com.texas.anexus.services.LoginService;
import com.texas.anexus.services.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/commons")
public class CommonController {

//	@ApiOperation(value="show the list of interestFields")
//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<Object> getAllInterestFields(){
//		
//		//List<InterestField> al = new ArrayList<InterestField>();
//		EnumSet<InterestField> allAnimals = EnumSet.allOf( InterestField.class );
//		System.out.println(allAnimals);
//		return new ResponseEntity<Object>(allAnimals,HttpStatus.OK);
//	}

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })

	@ApiOperation(value = "Register user")
	@RequestMapping(value = "additionalRegister", method = RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@RequestBody AdditionalRegisterCreationRequest addUserCreationRequest,
			@RequestHeader Long loginId) {
		userService.addRegisterUser(addUserCreationRequest, loginId);
		return new ResponseEntity<Object>("Registered Successfully", HttpStatus.OK);
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })

	@ApiOperation(value = "Upload profile picture")
	@RequestMapping(value = "uploadPic", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadImage(@RequestParam String profilePicture, @RequestHeader Long loginId) {
		userService.uploadProfilePicture(profilePicture, loginId);
		return new ResponseEntity<Object>("Profile Picture Updated Successfully", HttpStatus.OK);

	}

}
