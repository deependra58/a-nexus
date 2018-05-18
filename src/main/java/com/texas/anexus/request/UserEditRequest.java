package com.texas.anexus.request;

import com.texas.anexus.model.Address;

public class UserEditRequest {

	private String fullName;
	private String phoneNo;
	private String email;
	private AddressEditRequest addressEditRequest;
	
	public AddressEditRequest getAddressEditRequest() {
		return addressEditRequest;
	}
	public void setAddressEditRequest(AddressEditRequest addressEditRequest) {
		this.addressEditRequest = addressEditRequest;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
