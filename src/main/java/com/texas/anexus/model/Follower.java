package com.texas.anexus.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Follower extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	private Long followingId;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getFollowingId() {
		return followingId;
	}

	public void setFollowingId(Long followingId) {
		this.followingId = followingId;
	}

	
}
