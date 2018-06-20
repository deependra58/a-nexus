package com.texas.anexus.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.cloud.CloudinaryResource;
import com.texas.anexus.exceptions.AlreadyExistException;
import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.Address;
import com.texas.anexus.model.Follower;
import com.texas.anexus.model.Login;
import com.texas.anexus.model.User;
import com.texas.anexus.repository.AddressRepository;
import com.texas.anexus.repository.FollowerRepository;
import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.repository.UserRepository;
import com.texas.anexus.request.AdditionalRegisterCreationRequest;
import com.texas.anexus.request.UserEditRequest;
import com.texas.anexus.request.UserRegisterRequest;
import com.texas.anexus.response.AddressResponse;
import com.texas.anexus.response.SkillResponse;
import com.texas.anexus.response.UserResponse;
import com.texas.anexus.util.BCrypt;
import com.texas.anexus.util.FileUtil;
import com.texas.anexus.util.LoginStatus;
import com.texas.anexus.util.Status;
import com.texas.anexus.util.UserRole;

@Service
public class UserService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private FollowerRepository followerRepository;

	@Transactional
	public User registerUser(UserRegisterRequest userCreationRequest) {

		/* For Login */
		Login login = loginRepository.findByUsernameAndStatusNot(
				userCreationRequest.getUsername(), Status.DELETED);
		if (login != null) {
			throw new AlreadyExistException("Username already Exist!");
		}
		login = new Login();
		login.setUsername(userCreationRequest.getUsername());
		String pw = BCrypt.hashpw(userCreationRequest.getPassword(), BCrypt.gensalt());
		login.setPassword(pw);
		login.setLoginStatus(LoginStatus.LOGOUT);
		login.setStatus(Status.ACTIVE);
		login.setCreatedDate(new Date());

		/* For storing address */
		Address address = new Address();
		address.setDistrict(userCreationRequest.getDistrict());
		address.setState(userCreationRequest.getState());
		address.setLocalLevel(userCreationRequest.getLocalLevel());

		address.setStatus(Status.ACTIVE);

		User user = new User();

		user.setEmail(userCreationRequest.getEmail());

		loginRepository.save(login);
		addressRepository.save(address);
		user.setLogin(login);
		user.setFollowers((long) 0);
		user.setFollowing((long) 0);

		user.setGender(userCreationRequest.getGender());
		user.setFullName(userCreationRequest.getFullName());
		user.setAddress(address);
		user.setCreatedDate(new Date());
		user.setUserRole(UserRole.USER);
		user.setStatus(Status.ACTIVE);
		userRepository.save(user);
		return user;

	}

	@Transactional
	public UserResponse getUser(Long id) {
		User user = userRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (user == null) {
			throw new NotFoundException("User with id:" + id + " not found!");
		}
		UserResponse ur = new UserResponse();
		List<SkillResponse> sr = new ArrayList<SkillResponse>();
		Address address = addressRepository
				.findByIdAndStatusNot(user.getAddress().getId(), Status.DELETED);
		AddressResponse ar = new AddressResponse();
		ar.setDistrict(address.getDistrict());
		ar.setState(address.getState());
		ar.setLocalLevel(address.getLocalLevel());
		System.out.println(address.toString());
		ur.setAddressResponse(ar);
		ur.setEmail(user.getEmail());
		ur.setId(user.getId());
		ur.setInterestField(user.getInterestField());
		ur.setSkills(user.getSkills());
		// String[] st=user.getSkills();

		// System.out.println("------------"+user.getSkills());
		ur.setProfilePicture(user.getProfilePicture());
		ur.setFollowers(user.getFollowers());
		ur.setFollowing(user.getFollowing());
		ur.setFullName(user.getFullName());
		ur.setPhoneNo(user.getPhoneNo());

		return ur;

	}

	@Transactional
	public List<UserResponse> getAllUser() {

		List<UserResponse> userList = new ArrayList<UserResponse>();
		List<User> user = userRepository.findAllByStatusNot(Status.DELETED);
		user.stream().forEach(u -> {
			UserResponse ur = new UserResponse();
			Address address = addressRepository
					.findByIdAndStatusNot(u.getAddress().getId(), Status.DELETED);
			AddressResponse ar = new AddressResponse();
			ar.setDistrict(address.getDistrict());
			ar.setState(address.getState());
			Login login = loginRepository.findByIdAndStatusNot(u.getId(), Status.DELETED);
			System.out.println(address.toString());
			ur.setInterestField(u.getInterestField());
			ur.setAddressResponse(ar);
			ur.setEmail(u.getEmail());
			ur.setId(u.getId());
			ur.setProfilePicture(u.getProfilePicture());
			ur.setFollowers(u.getFollowers());
			ur.setFollowing(u.getFollowing());
			ur.setFullName(u.getFullName());
			ur.setUsername(login.getUsername());
			ur.setPhoneNo(u.getPhoneNo());
			userList.add(ur);
		});
		return userList;
	}

	@Transactional
	public void editUser(Long id, UserEditRequest userEditRequest) {

		User user = userRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (user == null) {
			throw new NotFoundException("User with id:" + id + " not found!");
		}
		Address address = addressRepository
				.findByIdAndStatusNot(user.getAddress().getId(), Status.DELETED);
		address.setDistrict(userEditRequest.getAddressEditRequest().getDistrict());
		address.setState(userEditRequest.getAddressEditRequest().getState());

		address.setLocalLevel(userEditRequest.getAddressEditRequest().getLocalLevel());
		user.setAddress(address);
		user.setEmail(userEditRequest.getEmail());

		user.setInterestField(userEditRequest.getInterestField());
		user.setSkills(userEditRequest.getSkills());
		user.setFullName(userEditRequest.getFullName());
		user.setPhoneNo(userEditRequest.getPhoneNo());
		user.setModifiedDate(new Date());
		userRepository.save(user);
	}

	@Transactional
	public void deleteUser(Long id) {
		User user = userRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (user == null) {
			throw new NotFoundException("User not found with id:" + id);
		}
		user.setStatus(Status.DELETED);
		userRepository.save(user);

	}

	public List<UserResponse> getUserByUsername(String firstName, Long loginId) {
		List<UserResponse> userResponseList = new ArrayList<UserResponse>();
		Login login = loginRepository.findByIdAndLoginStatusNot(loginId,
				LoginStatus.LOGOUT);
		if (login == null) {
			throw new ServiceException("Please Login first");
		}
		List<User> users = userRepository.findByFullNameAndStatusNot(firstName,
				Status.DELETED);
		if (users.isEmpty()) {
			throw new NotFoundException("user Not found with " + firstName);
		}
		users.stream().forEach(u -> {
			UserResponse ur = new UserResponse();
			ur.setId(u.getId());

			ur.setFullName(u.getFullName());
			ur.setEmail(u.getEmail());
			ur.setPhoneNo(u.getPhoneNo());
			ur.setInterestField(u.getInterestField());
			Address address = addressRepository
					.findByIdAndStatusNot(u.getAddress().getId(), Status.DELETED);
			AddressResponse ar = new AddressResponse();
			ar.setDistrict(address.getDistrict());
			ar.setState(address.getState());
			ar.setLocalLevel(address.getLocalLevel());

			userResponseList.add(ur);
		});
		return userResponseList;
	}

	@Transactional
	public void addRegisterUser(AdditionalRegisterCreationRequest addUserCreationRequest,
			Long loginId) {
		Login login = loginRepository.findByIdAndLoginStatusNot(loginId,
				LoginStatus.LOGOUT);
		// if (login == null) {
		// throw new ServiceException("Please login!");
		// }
		System.out.println(addUserCreationRequest.getPhoneNo());
		User user = userRepository.findByLoginAndStatusNot(login, Status.DELETED);
		// if (addUserCreationRequest.getPhoneNo() != null)
		user.setPhoneNo(addUserCreationRequest.getPhoneNo());
		user.setInterestField(addUserCreationRequest.getInterestedField());
		user.setSkills(addUserCreationRequest.getSkills());
		userRepository.save(user);
	}

	@SuppressWarnings("unused")
	public void uploadProfilePicture(String profilePicture, Long loginId) {
		Login login = loginRepository.findByIdAndLoginStatusNot(loginId,
				LoginStatus.LOGOUT);
		// if (login == null) {
		// throw new ServiceException("You are not logged in. Please login");
		// }
		User user = userRepository.findByLoginAndStatusNot(login, Status.DELETED);
		File file = null;
		System.out.println("Hello from pp");
		try {

			if (profilePicture != null && !profilePicture.isEmpty()) {
				// System.out.println("----------------------From pp");
				user.setProfilePicture(new CloudinaryResource().uploadFile(
						FileUtil.write("test", profilePicture),
						FileUtil.getFileLocation(loginId, UserRole.USER)));
				String path = "https://res.cloudinary.com/anexus/image/upload/v1526985354/"
						.concat(user.getProfilePicture());
				user.setProfilePicture(path);
				userRepository.save(user);
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}
	}

	@Transactional
	public User followingUser(Long loginId, Long followId) {
		Login loginFollowing = loginRepository.findByIdAndLoginStatusNot(loginId,
				LoginStatus.LOGOUT);
		if (loginFollowing == null) {
			throw new ServiceException("You are not logged in. Please login");
		}
		Login loginToFollow = loginRepository.findByIdAndStatusNot(followId,
				Status.DELETED);
		User userFollowing = userRepository.findByLoginAndStatusNot(loginFollowing,
				Status.DELETED);
		User userFollowed = userRepository.findByLoginAndStatusNot(loginToFollow,
				Status.DELETED);
		if (userFollowing == userFollowed) {
			throw new ServiceException("user cannot follow him/herself");
		}
		Follower follower = followerRepository.findByUserAndFollowingIdAndStatusNot(
				new User(userFollowing.getId()), userFollowed.getId(), Status.DELETED);
		if (follower != null)
			throw new ServiceException("user already followed");
		follower = new Follower();
		follower.setCreatedDate(new Date());
		follower.setUser(userFollowing);
		follower.setFollowingId(userFollowed.getId());
		follower.setStatus(Status.ACTIVE);
		userFollowing.setFollowing(userFollowing.getFollowing() + 1);
		userFollowed.setFollowers(userFollowed.getFollowers() + 1);
		userRepository.save(userFollowed);
		userRepository.save(userFollowing);
		followerRepository.save(follower);

		return userFollowed;
	}

}
