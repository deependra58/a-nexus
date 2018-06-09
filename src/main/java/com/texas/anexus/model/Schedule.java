package com.texas.anexus.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.texas.anexus.util.ScheduleType;

@SuppressWarnings("serial")
@Entity
public class Schedule extends AbstractEntity {

	private String task;
//	private Long year;
//	private Long day;
//	private Long month;
//	private Long hrs;
//	private Long min;

	private Date date;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User user;
	private String scheduleImage;
	@Enumerated(EnumType.STRING)
	private ScheduleType scheduleType;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ScheduleType getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getScheduleImage() {
		return scheduleImage;
	}

	public void setScheduleImage(String scheduleImage) {
		this.scheduleImage = scheduleImage;
	}
	
	

}
