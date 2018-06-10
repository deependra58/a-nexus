package com.texas.anexus.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.model.Follower;
import com.texas.anexus.model.Schedule;
import com.texas.anexus.model.User;
import com.texas.anexus.repository.FollowerRepository;
import com.texas.anexus.repository.ScheduleRepository;
import com.texas.anexus.repository.UserRepository;
import com.texas.anexus.response.ActivityResponse;
import com.texas.anexus.util.Status;

@Service
public class ActivityService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowerRepository followerRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Transactional
	public List<ActivityResponse> getUserDashBoardActivity(Long userId) {
		User user=userRepository.findByIdAndStatusNot(userId, Status.DELETED);
		List<Follower> follower=followerRepository.findByUserAndStatusNot(new User(user.getId()),Status.DELETED);
		List<ActivityResponse> activityResponse=new ArrayList<ActivityResponse>();
		follower.stream().forEach(u->{
			
			List<Schedule> schedule=scheduleRepository.findByUserAndStatusNot(new User(u.getId()),Status.DELETED);
			schedule.stream().forEach(v->{
				ActivityResponse ar=new ActivityResponse();
				ar.setDate(v.getCreatedDate());
				ar.setId(v.getId());
				ar.setTask(v.getTask());
				ar.setScheduleImage(v.getScheduleImage());
				activityResponse.add(ar);
			});
			
		
			
		});
		return activityResponse;
	}

}
