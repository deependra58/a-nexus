package com.texas.anexus.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.District;
import com.texas.anexus.model.Metropolitan;
import com.texas.anexus.model.Municipality;
import com.texas.anexus.model.RuralMunicipality;
import com.texas.anexus.model.State;
import com.texas.anexus.model.SubMetropolitan;
import com.texas.anexus.repository.DistrictRepository;
import com.texas.anexus.repository.MetropolitanRepository;
import com.texas.anexus.repository.MunicipalityRepository;
import com.texas.anexus.repository.RuralMunicipalityRepository;
import com.texas.anexus.repository.StateRepository;
import com.texas.anexus.repository.SubMetropolitanRepository;
import com.texas.anexus.response.DistrictResponseDto;
import com.texas.anexus.response.PalikaResponseDto;
import com.texas.anexus.response.StateResponseDto;
import com.texas.anexus.util.LocalLevelType;
import com.texas.anexus.util.Status;

@Service
public class CommonService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RuralMunicipalityRepository ruralMunicipalityRepository;

	@Autowired
	private MunicipalityRepository municipalityRepository;
	
	@Autowired
	private SubMetropolitanRepository subMetropolitanRepository;

	@Autowired
	private MetropolitanRepository metropolitanRepository;

	@Transactional
	public List<DistrictResponseDto> listAllDistricts(String state) {
		State s = stateRepository.findByStateAndStatusNot(state, Status.DELETED);
		if (s == null) {
			throw new NotFoundException("state not found!");
		}
		List<DistrictResponseDto> drd = new ArrayList<DistrictResponseDto>();
		List<District> districts = districtRepository.findAllByStateAndStatusNot(new State(s.getId()), Status.DELETED);
		districts.stream().forEach(u -> {
			DistrictResponseDto dr = new DistrictResponseDto();
			dr.setId(u.getId());
			dr.setDistrict(u.getDistrict());
			drd.add(dr);
		});
		return drd;
	}

	@Transactional
	public List<StateResponseDto> getAllStates() {
		List<StateResponseDto> srd = new ArrayList<StateResponseDto>();
		List<State> states = stateRepository.findAllByStatusNot(Status.DELETED);
		if (states.isEmpty()) {
			throw new NotFoundException("States not found");
		}
		states.stream().forEach(u -> {
			StateResponseDto sr = new StateResponseDto();
			sr.setId(u.getId());
			sr.setState(u.getState());
			srd.add(sr);

		});
		return srd;

	}

	@Transactional
	public List<PalikaResponseDto> getPalikaList(String district) {
		List<PalikaResponseDto> palikaResponse = new ArrayList<PalikaResponseDto>();
		District districts = districtRepository.findByDistrictAndStatusNot(district, Status.DELETED);
		if (districts == null)
			throw new NotFoundException("District Not found.");
		List<RuralMunicipality> rm = ruralMunicipalityRepository.findAllByDistrict(new District(districts.getId()));
		List<Municipality> m = municipalityRepository.findAllBydistrict(new District(districts.getId()));
		List<SubMetropolitan> subMetro = subMetropolitanRepository.findAllByDistrict(new District(districts.getId()));
		List<Metropolitan> metro = metropolitanRepository.findAllByDistrict(new District(districts.getId()));
		if (!rm.isEmpty()) {
			rm.stream().forEach(u -> {
				PalikaResponseDto prd = new PalikaResponseDto();
				prd.setLocalLevel(u.getRuralMunicipality());
				prd.setLocalLevelType(LocalLevelType.RURALMUNICIPAL);
				prd.setId(u.getId());
				palikaResponse.add(prd);
			});

		}
		if (!subMetro.isEmpty()) {
			subMetro.stream().forEach(w -> {
				PalikaResponseDto prd = new PalikaResponseDto();
				prd.setLocalLevel(w.getSubMetropolitan());
				prd.setLocalLevelType(LocalLevelType.SUBMETROPOLITAN);
				prd.setId(w.getId());
				palikaResponse.add(prd);
			});

		}
		if (!metro.isEmpty()) {
			metro.stream().forEach(x -> {
				PalikaResponseDto prd = new PalikaResponseDto();
				prd.setLocalLevel(x.getMetropolitan());
				prd.setLocalLevelType(LocalLevelType.METROPOLITAN);
				prd.setId(x.getId());
				palikaResponse.add(prd);
			});

		}
		if (!m.isEmpty()) {
			m.stream().forEach(v -> {
				PalikaResponseDto prd = new PalikaResponseDto();
				prd.setLocalLevel(v.getMunicipality());
				prd.setLocalLevelType(LocalLevelType.MUNICIPAL);
				prd.setId(v.getId());
				palikaResponse.add(prd);
			});

		}
		return palikaResponse;
	}


}
