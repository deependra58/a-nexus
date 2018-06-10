package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Follower;
import com.texas.anexus.model.User;
import com.texas.anexus.util.Status;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

	Follower findByUserAndFollowingIdAndStatusNot(User user, Long id, Status deleted);

	List<Follower> findByUserAndStatusNot(User user, Status deleted);

}
