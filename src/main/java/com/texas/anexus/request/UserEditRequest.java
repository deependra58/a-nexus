package com.texas.anexus.request;

import java.util.ArrayList;
import java.util.List;

import com.texas.anexus.util.InterestField;

public class UserEditRequest {

	private String fullName;
	private String[] interestField;
	private String[] skills;
	private String phoneNo;
	private String email;
	private AddressEditRequest addressEditRequest;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	
	public AddressEditRequest getAddressEditRequest() {
		return addressEditRequest;
	}

	public void setAddressEditRequest(AddressEditRequest addressEditRequest) {
		this.addressEditRequest = addressEditRequest;
	}
	

//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getMiddleName() {
//		return middleName;
//	}
//
//	public void setMiddleName(String middleName) {
//		this.middleName = middleName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

	public String[] getInterestField() {
		return interestField;
	}

	public void setInterestField(String[] interestField) {
		this.interestField = interestField;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
