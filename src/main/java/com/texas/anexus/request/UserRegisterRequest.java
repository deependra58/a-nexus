package com.texas.anexus.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.texas.anexus.util.GenderType;

public class UserRegisterRequest {

	private String fullName;
	private String email;
//	private String phoneNo;
//	private ArrayList<String> interestField;
//@Enumerated(EnumType.STRING)
	private GenderType gender;
	
	private String state;
	private String district;
	private String localLevel;
	private String username;
	private String password;

//	public ArrayList<String> getInterestField() {
//		return interestField;
//	}
//
//	public void setInterestField(ArrayList<String> interestField) {
//		this.interestField = interestField;
//	}
	
	

	public String getUsername() {
		return username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getLocalLevel() {
		return localLevel;
	}

	public void setLocalLevel(String localLevel) {
		this.localLevel = localLevel;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getPhoneNo() {
//		return phoneNo;
//	}
//
//	public void setPhoneNo(String phoneNo) {
//		this.phoneNo = phoneNo;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
