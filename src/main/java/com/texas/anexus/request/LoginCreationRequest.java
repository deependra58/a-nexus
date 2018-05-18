package com.texas.anexus.request;

import javax.validation.constraints.NotNull;

public class LoginCreationRequest {

	@NotNull(message = "Username cannot be null")
	private String username;
	@NotNull(message = "Password cannot be null")
	private String password;

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
}
