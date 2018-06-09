package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Schedule;
import com.texas.anexus.model.User;
import com.texas.anexus.util.ScheduleType;
import com.texas.anexus.util.Status;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long>{

	List<Schedule> findAllByStatusNot(Status deleted);

	List<Schedule> findAllByStatusNotAndScheduleTypeNot(Status deleted, ScheduleType private1);

	List<Schedule> findByUserAndStatusNot(User user, Status deleted);

}
