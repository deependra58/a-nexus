package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.State;
import com.texas.anexus.util.Status;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	State findByState(String string);

	List<State> findAllByStatusNot(Status deleted);

	State findByStateAndStatusNot(String state, Status deleted);

}
