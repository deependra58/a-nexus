package com.texas.anexus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Schedule extends AbstractEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String task;
	private Long year;
	private Long day;
	private Long month;
	private Long hrs;
	private Long min;
	private Long userId;
	
	
	
	
}
