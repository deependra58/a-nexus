package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.District;
import com.texas.anexus.model.State;
import com.texas.anexus.util.Status;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

	District findByDistrict(String string);

	List<District> findAllByState(State state);

	List<District> findAllByStateAndStatusNot(State state, Status deleted);

	District findByDistrictAndStatusNot(String district, Status deleted);

}
