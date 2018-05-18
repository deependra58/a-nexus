package com.texas.anexus.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.anexus.exceptions.AlreadyExistException;
import com.texas.anexus.exceptions.NotFoundException;
import com.texas.anexus.model.Address;
import com.texas.anexus.model.Login;
import com.texas.anexus.model.User;
import com.texas.anexus.repository.AddressRepository;
import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.repository.UserRepository;
import com.texas.anexus.request.AddressEditRequest;
import com.texas.anexus.request.UserEditRequest;
import com.texas.anexus.request.UserRegisterRequest;
import com.texas.anexus.response.AddressResponse;
import com.texas.anexus.response.UserResponse;
import com.texas.anexus.util.BCrypt;
import com.texas.anexus.util.LoginStatus;
import com.texas.anexus.util.Status;
import com.texas.anexus.util.UserRole;
import com.texas.anexus.util.Validator;

@Service
public class UserService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Transactional
	public void registerUser(UserRegisterRequest userCreationRequest, String ruralMunicipality, String municipality,
			String metropolitan, String subMetropolitan) {

		/* For Login */
		Login login = loginRepository.findByUsernameAndStatusNot(userCreationRequest.getUsername(), Status.DELETED);
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
		loginRepository.save(login);

		/* For storing address */
		Address address = new Address();
		address.setDistrict(userCreationRequest.getDistrict());
		address.setState(userCreationRequest.getState());
		address.setRuralMunicipality(ruralMunicipality);
		address.setMunicipality(municipality);
		address.setMetropolitan(metropolitan);
		address.setSubMetropolitan(subMetropolitan);
		address.setStatus(Status.ACTIVE);
		addressRepository.save(address);

		/* For creating user */
		User user = new User();
		if (Validator.getStringValidator(userCreationRequest.getFullName()) == false) {
			throw new ValidationException("Only Alphabets allowed in name.");
		} else {
			user.setFullName(userCreationRequest.getFullName());
		}
		if (Validator.getPhoneValidator(userCreationRequest.getPhoneNo()) == false) {
			throw new ValidationException("Please enter valid phone number.");
		} else {
			user.setPhoneNo(userCreationRequest.getPhoneNo());
		}

		if (Validator.getEmailValidator(userCreationRequest.getEmail()) == false) {
			throw new ValidationException("Please enter a valid email address.");
		} else {
			user.setEmail(userCreationRequest.getEmail());
		}

		user.setLoginId(login.getId());
		user.setAddress(address);
		user.setCreatedDate(new Date());
		user.setUserRole(UserRole.USER);
		user.setStatus(Status.ACTIVE);
		userRepository.save(user);

	}

	@Transactional
	public UserResponse getUser(Long id) {
		User user = userRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (user == null) {
			throw new NotFoundException("User with id:" + id + " not found!");
		}
		UserResponse ur = new UserResponse();
		Address address = addressRepository.findByIdAndStatusNot(user.getAddress().getId(), Status.DELETED);
		AddressResponse ar=new AddressResponse();
		ar.setDistrict(address.getDistrict());
		ar.setState(address.getState());
		ar.setMetropolitan(address.getMetropolitan());
		ar.setMunicipality(address.getMunicipality());
		ar.setRuralMunicipality(address.getRuralMunicipality());
		ar.setSubMetropolitan(address.getSubMetropolitan());
		System.out.println(address.toString());
		ur.setAddressResponse(ar);
		ur.setEmail(user.getEmail());
		ur.setFullName(user.getFullName());
		ur.setPhoneNo(user.getPhoneNo());
		return ur;

	}

	@Transactional
	public List<UserResponse> getAllUser() {

		List<UserResponse> userList=new ArrayList<UserResponse>();
		List<User> user=userRepository.findAllByStatusNot(Status.DELETED);
		user.stream().forEach(u->{
			UserResponse ur = new UserResponse();
			Address address = addressRepository.findByIdAndStatusNot(u.getAddress().getId(), Status.DELETED);
			AddressResponse ar=new AddressResponse();
			ar.setDistrict(address.getDistrict());
			ar.setState(address.getState());
			ar.setMetropolitan(address.getMetropolitan());
			ar.setMunicipality(address.getMunicipality());
			ar.setRuralMunicipality(address.getRuralMunicipality());
			ar.setSubMetropolitan(address.getSubMetropolitan());
			System.out.println(address.toString());
			ur.setAddressResponse(ar);
			ur.setEmail(u.getEmail());
			ur.setFullName(u.getFullName());
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
		Address address=addressRepository.findByIdAndStatusNot(user.getAddress().getId(),Status.DELETED);
	    address.setDistrict(userEditRequest.getAddressEditRequest().getDistrict());
	    address.setState(userEditRequest.getAddressEditRequest().getState());
	    address.setRuralMunicipality(userEditRequest.getAddressEditRequest().getRuralMunicipality());
	    address.setMunicipality(userEditRequest.getAddressEditRequest().getMunicipality());
	    address.setMetropolitan(userEditRequest.getAddressEditRequest().getMetropolitan());
	    address.setSubMetropolitan(userEditRequest.getAddressEditRequest().getSubMetropolitan());
	    
	   
	    address.setModifiedDate(new Date());
	    user.setAddress(address);
	    user.setEmail(userEditRequest.getEmail());
	    user.setFullName(userEditRequest.getFullName());
	    user.setPhoneNo(userEditRequest.getPhoneNo());
	    user.setModifiedDate(new Date());
		userRepository.save(user);
	}

	@Transactional
	public void deleteUser(Long id) {
		User user=userRepository.findByIdAndStatusNot(id, Status.DELETED);
		if(user==null) {
			throw new NotFoundException("User not found with id:"+id);
		}
		user.setStatus(Status.DELETED);
		userRepository.save(user);
		
	}

}
