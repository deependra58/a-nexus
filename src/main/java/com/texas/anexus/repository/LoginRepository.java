package com.texas.anexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Login;
import com.texas.anexus.util.LoginStatus;
import com.texas.anexus.util.Status;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	Login findByUsernameAndStatusNot(String username, Status deleted);

	Login findByIdAndStatusNot(Long userId, Status deleted);

	Login findByIdAndLoginStatusNot(Long loginId, LoginStatus logout);



}
