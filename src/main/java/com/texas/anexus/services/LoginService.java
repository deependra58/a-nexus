package com.texas.anexus.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
<<<<<<< HEAD
import com.texas.anexus.model.TokenGenerator;
import com.texas.anexus.model.User;
import com.texas.anexus.model.Verification;
import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.repository.LoginTokenRepository;
import com.texas.anexus.repository.UserRepository;
import com.texas.anexus.repository.VerificationRepository;
import com.texas.anexus.request.ForgetPasswordRequest;
=======
import com.texas.anexus.model.User;
import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.repository.LoginTokenRepository;
import com.texas.anexus.repository.UserRepository;
>>>>>>> d10a1c2b7852a1795133c34705c7f01e380d4705
import com.texas.anexus.request.LoginCreationRequest;
import com.texas.anexus.util.BCrypt;
import com.texas.anexus.util.Constant;
import com.texas.anexus.util.DateUtil;
import com.texas.anexus.util.DateUtils;
import com.texas.anexus.util.EmailUtility;
import com.texas.anexus.util.LoginStatus;
import com.texas.anexus.util.RandomUtils;
import com.texas.anexus.util.Status;
import com.texas.anexus.util.VerificationStatus;

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
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationRepository verificationRepository;

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
		// login.setDevviceId(loginDto.getDeviceId());
		LoginToken loginToken = loginTokenRepository.findByLoginId(login.getId());
		if (loginToken == null) {
			loginToken = new LoginToken();
			loginToken.setLoginId(login.getId());
		}

		loginToken.setToken(RandomUtils.randomString(50));
		loginToken.setTokenExpirationDateTime(DateUtils.currentDateTimePlusMinutes(tokenExpireAfter));
		loginToken.setStatus(Status.ACTIVE);
		loginToken.setCreatedDate(new Date());
		loginToken = loginTokenRepository.save(loginToken);
<<<<<<< HEAD
		User user = userRepository.findByIdAndStatusNot(login.getId(), Status.DELETED);
=======
		User user=userRepository.findByIdAndStatusNot(login.getId(), Status.DELETED);
>>>>>>> d10a1c2b7852a1795133c34705c7f01e380d4705
		Map<Object, Object> response = new HashMap<>();
		response.put("username", login.getUsername());
		response.put("id", login.getId());
		response.put("token", loginToken.getToken());
<<<<<<< HEAD
		response.put("profile pic", user.getProfilePicture());
=======
		response.put("profile pic",user.getProfilePicture());
>>>>>>> d10a1c2b7852a1795133c34705c7f01e380d4705
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
		LoginToken lt = loginTokenRepository.findByLoginIdAndStatusNot(login.getId(), Status.DELETED);
		lt.setStatus(Status.DELETED);
		loginTokenRepository.save(lt);
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
		LOG.debug("User id {} and token {}", userId, token);
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

		LoginToken login = loginTokenRepository.findByToken(token);
		if (null == login) {
			throw new NotFoundException("Token not found! Enter a valid token");
		}

		System.out.println("Token=" + login.getToken() + "Id=" + login.getId());
		if (!DateUtils.isCurrentTimeBeforeThanGivenTime(login.getTokenExpirationDateTime()))
			throw new ServiceException("Token Expired!");

	}

	@Transactional
	public Map<Object, Object> resetPassword(String email) {

		LOG.debug("Request to reset Password");
		User user = userRepository.findByEmailAndStatusNot(email, Status.DELETED);
		if (user == null) {
			throw new NotFoundException("Email Not found!!");
		}

		Optional<Login> login = loginRepository.findById(user.getLogin().getId());

		TokenGenerator tg = new TokenGenerator();
		String token = tg.generateToken(login.get().getUsername());

		Verification verification = verificationRepository.findByEmailAndVerificationStatusNot(email,
				VerificationStatus.EXPIRE);

		if (verification != null) {
			verification.setCreatedDate(new Date());
			verification.setToken(token);
			verification.setVerificationStatus(VerificationStatus.ACTIVE);
			verificationRepository.save(verification);
		} else {
			Verification verify = new Verification();
			verify.setEmail(user.getEmail());
			verify.setCreatedDate(new Date());
			verify.setExpireDate(DateUtil.getTokenExpireDate(new Date()));
			verify.setToken(token);
			verify.setVerificationStatus(VerificationStatus.ACTIVE);
			verificationRepository.save(verify);

		}
		EmailUtility.sendResetLink(user.getEmail(), token);

		LOG.debug("Request to reset Password accepted");
		Map<Object, Object> response = new HashMap<>();
		response.put("token:", token);
		return response;
	}

	/**
	 * @param forgetPasswordRequest
	 */
	@Transactional
	public void resetForgetPassword(String token, ForgetPasswordRequest forgetPasswordRequest) {

		LOG.debug("Acceped to reset password");

		Verification v = verificationRepository.findByTokenAndVerificationStatusNot(token, VerificationStatus.EXPIRE);
		System.out.println("-------------------------------------"+v.getEmail());
//		if (v==null) {
//			throw new ServiceException("Invalid Session!");
//		}

		if (DateUtil.compareDate(v.getCreatedDate(), v.getExpireDate()) == false) {
			v.setVerificationStatus(VerificationStatus.EXPIRE);
			verificationRepository.save(v);
			throw new ServiceException("Sorry !! Token is expired");
		}

		User user = userRepository.findByEmailAndStatusNot(v.getEmail(), Status.DELETED);
		Login login = loginRepository.findByIdAndStatusNot(user.getLogin().getId(),Status.DELETED);

		if (login == null) {
			throw new NotFoundException("Email Address Not found !!");
		}
		if (!forgetPasswordRequest.getNewPassword().equals(forgetPasswordRequest.getConfirmPassword())) {
			throw new NotFoundException("Password Did not match");
		}
		try {
			String pw = BCrypt.hashpw(forgetPasswordRequest.getConfirmPassword(), BCrypt.gensalt());
			System.out.println("passoword:" + pw);

			login.setPassword(pw);

			Login savedlogin = loginRepository.save(login);
			if (savedlogin != null) {
				v.setVerificationStatus(VerificationStatus.EXPIRE);
				verificationRepository.save(v);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOG.debug("Password is reset");
	}

}
