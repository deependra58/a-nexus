package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.District;
import com.texas.anexus.model.Municipality;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long>{

	Municipality findByMunicipality(String string);

	List<Municipality> findAllBydistrict(District district);

}
