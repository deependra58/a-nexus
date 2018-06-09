package com.texas.anexus.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.texas.anexus.util.Status;

@Entity
public class Metropolitan implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String metropolitan;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="district_id")
	private District district;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMetropolitan() {
		return metropolitan;
	}

	public void setMetropolitan(String metropolitan) {
		this.metropolitan = metropolitan;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	

}
