package com.texas.anexus.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.District;
import com.texas.anexus.model.State;
import com.texas.anexus.repository.DistrictRepository;
import com.texas.anexus.repository.StateRepository;
import com.texas.anexus.response.DistrictResponseDto;
import com.texas.anexus.util.Status;


@Service
public class DistrictService {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

	@Transactional
	public List<DistrictResponseDto> listAllDistricts(String state) {
		State s=stateRepository.findByStateAndStatusNot(state,Status.DELETED);
		if(s==null) {
			throw new NotFoundException("state not found!");
		}
		List<DistrictResponseDto> drd=new ArrayList<DistrictResponseDto>();
		List<District> districts=districtRepository.findAllByStateAndStatusNot(new State(s.getId()),Status.DELETED);
		districts.stream().forEach(u->{
			DistrictResponseDto dr=new DistrictResponseDto();
			dr.setId(u.getId());
			dr.setDistrict(u.getDistrict());
			drd.add(dr);
		});
		return drd;
	}

}
