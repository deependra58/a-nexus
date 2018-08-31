package com.texas.anexus.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.texas.anexus.util.LoginStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="login")
public class Login  extends AbstractEntity{
	
	private String username;
	private String password;
	private Long devviceId;
	
	@Enumerated(EnumType.STRING)
	private LoginStatus loginStatus;
	
	@OneToOne(mappedBy = "login", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;

	public Login(Long loginId) {
		this.id=loginId;
	}

	public Login() {
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
