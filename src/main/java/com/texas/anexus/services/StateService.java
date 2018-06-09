package com.texas.anexus.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.State;
import com.texas.anexus.repository.StateRepository;
import com.texas.anexus.response.StateResponseDto;
import com.texas.anexus.util.Status;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;

	@Transactional
	public List<StateResponseDto> getAllStates() {
		List<StateResponseDto> srd=new ArrayList<StateResponseDto>();
		List<State> states=stateRepository.findAllByStatusNot(Status.DELETED);
		if(states.isEmpty()) {
			throw new NotFoundException("States not found");
		}
		states.stream().forEach(u->{
			StateResponseDto sr=new StateResponseDto();
			sr.setId(u.getId());
			sr.setState(u.getState());
			srd.add(sr);
			
		});
		return srd;
		
	}

}
