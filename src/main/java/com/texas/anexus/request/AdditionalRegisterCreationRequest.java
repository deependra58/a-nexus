package com.texas.anexus.request;

public class AdditionalRegisterCreationRequest {

	private String phoneNo;
	private String[] interestedField;
	private String[] skills;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String[] getInterestedField() {
		return interestedField;
	}
	public void setInterestedField(String[] interestedField) {
		this.interestedField = interestedField;
	}
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	
	
	
	
}
