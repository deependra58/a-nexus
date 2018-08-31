package com.texas.anexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Verification;
import com.texas.anexus.util.VerificationStatus;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long>{

	Verification findByEmailAndVerificationStatusNot(String email, VerificationStatus expire);


	Verification findByTokenAndVerificationStatusNot(String token, VerificationStatus expire);


}
