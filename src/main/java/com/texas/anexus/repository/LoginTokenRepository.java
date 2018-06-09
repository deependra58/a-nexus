package com.texas.anexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Login;
import com.texas.anexus.model.LoginToken;
import com.texas.anexus.util.Status;

@Repository
public interface LoginTokenRepository extends JpaRepository<LoginToken, Long> {

	LoginToken findByLoginIdAndToken(Long userId, String token);

	LoginToken findByToken(String token);

	LoginToken findByLoginId(Long id);
	LoginToken findByLoginIdAndStatusNot(Long id, Status deleted);

}




