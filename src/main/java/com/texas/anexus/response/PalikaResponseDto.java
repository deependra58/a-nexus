package com.texas.anexus.response;

import com.texas.anexus.util.LocalLevelType;

public class PalikaResponseDto {
	private Long id;
	private String localLevel;
	private LocalLevelType localLevelType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocalLevel() {
		return localLevel;
	}
	public void setLocalLevel(String localLevel) {
		this.localLevel = localLevel;
	}
	public LocalLevelType getLocalLevelType() {
		return localLevelType;
	}
	public void setLocalLevelType(LocalLevelType localLevelType) {
		this.localLevelType = localLevelType;
	}
	

}
