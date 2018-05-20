package com.texas.anexus.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.Login;
import com.texas.anexus.model.Schedule;
import com.texas.anexus.model.User;
import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.repository.ScheduleRepository;
import com.texas.anexus.repository.UserRepository;
import com.texas.anexus.request.ScheduleCreationRequest;
import com.texas.anexus.response.ScheduleResponse;
import com.texas.anexus.util.LoginStatus;
import com.texas.anexus.util.ScheduleType;
import com.texas.anexus.util.Status;


@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Transactional
	public void postSchedule(ScheduleCreationRequest scheduleCreationRequest, Long loginId) {
		Schedule schedule=new Schedule();
		//Login login=loginRepository.findByIdAndStatusNot(loginId,Status.ACTIVE);
		User user=userRepository.findByLoginAndStatusNot(new Login(loginId),Status.DELETED);
		if(user==null) {
			throw new NotFoundException("User not found with given login ID");
		}
		schedule.setDay(scheduleCreationRequest.getDay());
		schedule.setHrs(scheduleCreationRequest.getHrs());
		schedule.setMin(scheduleCreationRequest.getMin());
		schedule.setMonth(scheduleCreationRequest.getMonth());
		schedule.setYear(scheduleCreationRequest.getYear());
		schedule.setCreatedDate(new Date());
		schedule.setScheduleType(scheduleCreationRequest.getScheduleType());
		schedule.setStatus(Status.ACTIVE);
		schedule.setTask(scheduleCreationRequest.getTask());
		schedule.setUser(user);
		scheduleRepository.save(schedule);
		
		
	}

	@Transactional
	public List<ScheduleResponse> getSchedule(Long userId, Long loginId) {
		List<ScheduleResponse> scheduleResponseList=new ArrayList<ScheduleResponse>();
		Login login=loginRepository.findByIdAndLoginStatusNot(loginId,LoginStatus.LOGOUT);
		if(login==null) {
			throw new ServiceException("Please login first");
		}
		/*it means if the user want to see the schedule of himself*/
		if(userId==loginId) {
			List<Schedule> sc=scheduleRepository.findAllByStatusNot(Status.DELETED);
			sc.stream().forEachOrdered(u->{
				ScheduleResponse sr=new ScheduleResponse();
				sr.setDay(u.getDay());
				sr.setHrs(u.getHrs());
				sr.setMin(u.getMin());
				sr.setMonth(u.getMonth());
				sr.setTask(u.getTask());
				sr.setYear(u.getYear());
				scheduleResponseList.add(sr);
				
			});
			return scheduleResponseList;
		}
		/*IT means if user want to see schedule of others then only the public schedule is made visible*/
		else {
			List<Schedule> sc=scheduleRepository.findAllByStatusNotAndScheduleTypeNot(Status.DELETED,ScheduleType.PRIVATE);
			sc.stream().forEachOrdered(u->{
				ScheduleResponse sr=new ScheduleResponse();
				sr.setDay(u.getDay());
				sr.setHrs(u.getHrs());
				sr.setMin(u.getMin());
				sr.setMonth(u.getMonth());
				sr.setTask(u.getTask());
				sr.setYear(u.getYear());
				scheduleResponseList.add(sr);
				
			});
			return scheduleResponseList;
			
		}
		
	}

}
