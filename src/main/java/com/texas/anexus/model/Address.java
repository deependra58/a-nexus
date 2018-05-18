package com.texas.anexus.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/*
 * <<This is the entity for address>>
 * @Author Deependra Karki
 * @Since May 18 , 2018
 * @Version 1.0.0
 * */
@SuppressWarnings("serial")
@Entity
public class Address extends AbstractEntity {

	private String state;
	private String district;
	private String ruralMunicipality;
	private String municipality;
	private String subMetropolitan;
	private String metropolitan;

	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> user;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "Address [state=" + state + ", district=" + district + ", ruralMunicipality=" + ruralMunicipality
				+ ", municipality=" + municipality + ", subMetropolitan=" + subMetropolitan + ", metropolitan="
				+ metropolitan + ", user=" + user + "]";
	}

}
