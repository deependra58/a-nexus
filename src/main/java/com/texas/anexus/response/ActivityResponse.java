package com.texas.anexus.response;

import java.util.Date;

public class ActivityResponse {
	
	private Long id;
	private Date date;
	private String task;
	private String scheduleImage;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getScheduleImage() {
		return scheduleImage;
	}
	public void setScheduleImage(String scheduleImage) {
		this.scheduleImage = scheduleImage;
	}
	
	
	

}
