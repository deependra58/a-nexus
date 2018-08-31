package com.texas.anexus.request;

<<<<<<< HEAD
import java.util.Arrays;

=======
>>>>>>> d10a1c2b7852a1795133c34705c7f01e380d4705
import javax.validation.constraints.NotNull;

public class AdditionalRegisterCreationRequest {

	@NotNull(message="phone no. cannot be null")
	private String phoneNo;
	@NotNull(message="interest field  cannot be null")
	private String[] interestedField;
	@NotNull(message="skills cannot be null")
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
	@Override
	public String toString() {
		return "AdditionalRegisterCreationRequest [phoneNo=" + phoneNo + ", interestedField="
				+ Arrays.toString(interestedField) + ", skills=" + Arrays.toString(skills) + "]";
	}
	
	
	
	
}
