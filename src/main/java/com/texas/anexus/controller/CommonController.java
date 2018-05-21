package com.texas.anexus.controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.anexus.util.InterestField;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/commons")
public class CommonController {
	
	@ApiOperation(value="show the list of interestFields")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllInterestFields(){
		
		//List<InterestField> al = new ArrayList<InterestField>();
		EnumSet<InterestField> allAnimals = EnumSet.allOf( InterestField.class );
		System.out.println(allAnimals);
		return new ResponseEntity<Object>(allAnimals,HttpStatus.OK);
	}

}
