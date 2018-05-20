package com.texas.anexus.request;

import com.texas.anexus.model.Address;

public class UserEditRequest {

	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNo;
	private String email;
	private AddressEditRequest addressEditRequest;

	public AddressEditRequest getAddressEditRequest() {
		return addressEditRequest;
	}

	public void setAddressEditRequest(AddressEditRequest addressEditRequest) {
		this.addressEditRequest = addressEditRequest;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
