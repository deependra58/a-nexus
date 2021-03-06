package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Login;
import com.texas.anexus.model.User;
import com.texas.anexus.util.Status;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByIdAndStatusNot(Long id, Status deleted);

	List<User> findAllByStatusNot(Status deleted);

	User findByLoginAndStatusNot(Login login, Status deleted);

	List<User> findByFullNameAndStatusNot(String firstName, Status deleted);

	User findByEmailAndStatusNot(String email, Status deleted);


}
