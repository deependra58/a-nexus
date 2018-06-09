package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.District;
import com.texas.anexus.model.SubMetropolitan;

@Repository
public interface SubMetropolitanRepository extends JpaRepository<SubMetropolitan, Long>{

	SubMetropolitan findBySubMetropolitan(String string);

	List<SubMetropolitan> findAllByDistrict(District district);

}
