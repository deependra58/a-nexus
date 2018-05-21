/*************************************************************************
 * 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained here in is, and remains
 * the property of Texas Imaginology and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here in are proprietary to Texas Imaginology. Dissemination of this
 * information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Texas Imaginology.
 * 
 */
package com.texas.anexus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <<This is the model that saves the token of users logged in>>
 * @author Deependra Karki
 * @version 1.0.0
 * @since 1.0.0, 21 May 2018
 */
@Entity
public class LoginToken extends AbstractEntity {
	private Long loginId;
	private String token;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date tokenExpirationDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpirationDateTime() {
		return tokenExpirationDateTime;
	}

	public void setTokenExpirationDateTime(Date tokenExpirationDateTime) {
		this.tokenExpirationDateTime = tokenExpirationDateTime;
	}

}
