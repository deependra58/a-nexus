package com.texas.anexus.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.texas.anexus.util.LoginStatus;

@Entity
public class Login  extends AbstractEntity{
	
	private String username;
	private String password;
	private Long devviceId;
	
	@Enumerated(EnumType.STRING)
	private LoginStatus loginStatus;

	public String getUsername() {
		return username;
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

	public Long getDevviceId() {
		return devviceId;
	}

	public void setDevviceId(Long devviceId) {
		this.devviceId = devviceId;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	

}
