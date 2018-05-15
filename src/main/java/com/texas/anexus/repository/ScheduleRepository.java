package com.texas.anexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Schedule;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long>{

}
