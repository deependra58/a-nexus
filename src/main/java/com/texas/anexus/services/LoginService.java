package com.texas.anexus.services;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.Login;
import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.request.LoginCreationRequest;
import com.texas.anexus.util.BCrypt;
import com.texas.anexus.util.LoginStatus;
import com.texas.anexus.util.Status;


@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	@Transactional
	public Map<Object, Object> login(LoginCreationRequest loginDto){
		Login login=loginRepository.findByUsernameAndStatusNot(loginDto.getUsername(),Status.DELETED);
		if(login==null) {
			throw new NotFoundException("User with username "+loginDto.getUsername()+" not found!");
		}
		
		if(BCrypt.checkpw(loginDto.getPassword(), login.getPassword())) {
			login.setLoginStatus(LoginStatus.LOGGEDIN);
			loginRepository.save(login);
		}
		else {
			throw new ServiceException("Incorrect Password!");
		}
		login.setDevviceId(loginDto.getDeviceId());
		Map<Object,Object> response= new HashMap<>();
		response.put("username",login.getUsername());
		response.put("id", login.getId());
		return response;
		
	}
	
	@Transactional
	public void logout(Long userId) {
		Login login=loginRepository.findByIdAndStatusNot(userId,Status.DELETED);
		if(login==null) {
			throw new NotFoundException("User with id "+userId+"not found!");
		}
		if(login.getLoginStatus().equals(LoginStatus.LOGOUT)) {
			throw new ServiceException("You are logged out");
		}
		login.setLoginStatus(LoginStatus.LOGOUT);
		loginRepository.save(login);
		
		
	}

}
