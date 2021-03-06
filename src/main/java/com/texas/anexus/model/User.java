package com.texas.anexus.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.texas.anexus.util.GenderType;
import com.texas.anexus.util.Status;
import com.texas.anexus.util.UserRole;

@SuppressWarnings("serial")
@Entity
public class User extends AbstractEntity {
	private String fullName;
//	private String middleName;
//	private String lastName;
	private String phoneNo;
	private String email;
	private String profilePicture;
	private String rating;
	@Enumerated(EnumType.STRING)
	private GenderType gender;
	private String[] skills;
	private String[] interestField;
	private Long followers;
	private Long following;

	@OneToOne
	private Login login;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Schedule> schedule;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Follower> follower;
	
	
	public User(Long id) {
		this.id=id;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getFollowers() {
		return followers;
	}

	public void setFollowers(Long followers) {
		this.followers = followers;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String[] getInterestField() {
		return interestField;
	}

	public void setInterestField(String[] interestField) {
		this.interestField = interestField;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

//	public String[] getSkills() {
//		return skills;
//	}
//
//	public void setSkills(String[] skills) {
//		this.skills = skills;
//	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public Long getFollowing() {
		return following;
	}

	public void setFollowing(Long following) {
		this.following = following;
	}

	public List<Follower> getFollower() {
		return follower;
	}

	public void setFollower(List<Follower> follower) {
		this.follower = follower;
	}
	

}
