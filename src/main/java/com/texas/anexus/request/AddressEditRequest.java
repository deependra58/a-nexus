package com.texas.anexus.request;

public class AddressEditRequest {

	private String state;
	private String district;
	private String ruralMunicipality;
	private String municipality;
	private String subMetropolitan;
	private String metropolitan;
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
	public String getRuralMunicipality() {
		return ruralMunicipality;
	}
	public void setRuralMunicipality(String ruralMunicipality) {
		this.ruralMunicipality = ruralMunicipality;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getSubMetropolitan() {
		return subMetropolitan;
	}
	public void setSubMetropolitan(String subMetropolitan) {
		this.subMetropolitan = subMetropolitan;
	}
	public String getMetropolitan() {
		return metropolitan;
	}
	public void setMetropolitan(String metropolitan) {
		this.metropolitan = metropolitan;
	}
	
}
