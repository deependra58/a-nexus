package com.texas.anexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.District;
import com.texas.anexus.model.RuralMunicipality;

@Repository
public interface RuralMunicipalityRepository extends JpaRepository<RuralMunicipality, Long> {

	RuralMunicipality findByRuralMunicipality(String string);

	List<RuralMunicipality> findAllByDistrict(District district);

}
