package com.texas.anexus.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.texas.anexus.util.Status;

/**
 * <<This is the entity for State>>
 * 
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "state")
public class State implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Status status;

	private String state;
	
	@OneToMany(mappedBy="state", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<District> district;

	public State(Long id) {
		this.id=id;
	}

	public State() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
