package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.District;
import com.texas.anexus.model.Metropolitan;

@Repository
public interface MetropolitanRepository extends JpaRepository<Metropolitan, Long> {

	Metropolitan findByMetropolitan(String string);

	List<Metropolitan> findAllByDistrict(District district);

}
