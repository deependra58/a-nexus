package com.texas.anexus.request;

import com.texas.anexus.util.ScheduleType;

public class ScheduleCreationRequest {

	private String task;
	private Long year;
	private Long day;
	private Long month;
	private Long hrs;
	private Long min;
	private ScheduleType scheduleType;
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}
	public Long getHrs() {
		return hrs;
	}
	public void setHrs(Long hrs) {
		this.hrs = hrs;
	}
	public Long getMin() {
		return min;
	}
	public void setMin(Long min) {
		this.min = min;
	}
	public ScheduleType getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}
	
	
}
