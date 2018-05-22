package com.texas.anexus.services;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.Login;
import com.texas.anexus.model.LoginToken;
import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.repository.LoginTokenRepository;
import com.texas.anexus.request.LoginCreationRequest;
import com.texas.anexus.util.BCrypt;
import com.texas.anexus.util.Constant;
import com.texas.anexus.util.DateUtils;
import com.texas.anexus.util.LoginStatus;
import com.texas.anexus.util.RandomUtils;
import com.texas.anexus.util.Status;

@Service
public class LoginService {

	private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private LoginTokenRepository loginTokenRepository;

	@Value("${anexus.token.expire.enable}")
	private String tokenExpireEnable;

	@Value("${anexus.token.expire.after}")
	private int tokenExpireAfter;

	@Value("${anexus.login.password.length}")
	private int passwordLength;

	@Autowired
	private LoginRepository loginRepository;

	@Transactional
	public Map<Object, Object> login(LoginCreationRequest loginDto) {
		Login login = loginRepository.findByUsernameAndStatusNot(loginDto.getUsername(), Status.DELETED);
		if (login == null) {
			throw new NotFoundException("User with username " + loginDto.getUsername() + " not found!");
		}

		if (BCrypt.checkpw(loginDto.getPassword(), login.getPassword())) {
			login.setLoginStatus(LoginStatus.LOGGEDIN);
			loginRepository.save(login);
		} else {
			throw new ServiceException("Incorrect Password!");
		}
		login.setDevviceId(loginDto.getDeviceId());
		LoginToken loginToken = new LoginToken();
		loginToken.setLoginId(login.getId());
		loginToken.setToken(RandomUtils.randomString(50));
		loginToken.setTokenExpirationDateTime(DateUtils.currentDateTimePlusMinutes(tokenExpireAfter));
		loginToken.setStatus(Status.ACTIVE);
		loginToken = loginTokenRepository.save(loginToken);
		Map<Object, Object> response = new HashMap<>();
		response.put("username", login.getUsername());
		response.put("id", login.getId());
		response.put("token", loginToken.getToken());
		return response;

	}

	@Transactional
	public void logout(Long userId) {
		Login login = loginRepository.findByIdAndStatusNot(userId, Status.DELETED);
		if (login == null) {
			throw new NotFoundException("User with id " + userId + "not found!");
		}
		if (login.getLoginStatus().equals(LoginStatus.LOGOUT)) {
			throw new ServiceException("You are logged out");
		}
		login.setLoginStatus(LoginStatus.LOGOUT);
		loginRepository.save(login);

	}

	/**
	 * Validates token with userid.
	 * 
	 * @param userId
	 * @param token
	 * @return <code>true</code> if valid otherwise <code>false</code>
	 * @author Deependra Karki
	 * @since 1.0.0
	 */
	public boolean isValidToken(Long userId, String token) {
		if (userId.equals(0L)) {
			throw new ServiceException("userId or loginId required in header parameter.");
		}
		if (null == userId || null == token) {
			return false;
		}
		// Login user = userRepository.getOne(userId);
		LOG.debug("User id {} and token {}", userId, token);
		// Login login = loginRepository.findByIdAndToken(user.getLoginId(), token);
		LoginToken login = loginTokenRepository.findByLoginIdAndToken(userId, token);
		if (null == login) {
			return false;
		}
		LOG.debug("Login found.");
		if (tokenExpireEnable.equalsIgnoreCase(Constant.ENABLE)) {
			if (!DateUtils.isCurrentTimeBeforeThanGivenTime(login.getTokenExpirationDateTime()))
				return false;
		}
		return true;
	}

	public void isValidToken(String token) {
//		if (userId.equals(0L)) {
//			throw new ServiceException("userId or loginId required in header parameter.");
//		}
//		if (null == userId || null == token) {
//			return false;
//		}
		// Login user = userRepository.getOne(userId);
		// LOG.debug("User id {} and token {}", userId, token);
		// Login login = loginRepository.findByIdAndToken(user.getLoginId(), token);

		LoginToken login = loginTokenRepository.findByToken(token);
		if (null == login) {
			throw new NotFoundException("Token not found! Enter a valid token");
		}

//		if (tokenExpireEnable.equalsIgnoreCase(Constant.ENABLE)) {
			if (!DateUtils.isCurrentTimeBeforeThanGivenTime(login.getTokenExpirationDateTime()))
				throw new ServiceException("Token Expired!");
//		}
		
	}

}
