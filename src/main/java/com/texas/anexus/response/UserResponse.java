package com.texas.anexus.response;

import java.util.List;

public class UserResponse {

	private Long id;
	private String fullName;
	private String phoneNo;
	private String email;
	private AddressResponse addressResponse;
	private List<String> interestField;
	
	public List<String> getInterestField() {
		return interestField;
	}
	public void setInterestField(List<String> interestField) {
		this.interestField = interestField;
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
	public AddressResponse getAddressResponse() {
		return addressResponse;
	}
	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
