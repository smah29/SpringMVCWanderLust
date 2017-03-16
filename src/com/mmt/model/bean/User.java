package com.mmt.model.bean;
//import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.sujata.validator.Phone;
import com.sujata.validator.Password;

//@Component
public class User {

	@NotEmpty
	@Length(min=1, max=20)
	private String userId;
	@NotEmpty
	@Length(min=1, max=20)
	private String userName;
	@NotEmpty
	@Phone
	private String userPhoneNo;
	@NotEmpty
	@Email
	private String userEmailId;
	@Length(min=10, max=50)
	private String userAddress;
	@Length(min=3, max=20)
	@Password
	@NotEmpty
	private String userPassword;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String userName, String userPhoneNo, String userEmailId, String userAddress,
			String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhoneNo = userPhoneNo;
		this.userEmailId = userEmailId;
		this.userAddress = userAddress;
		this.userPassword = userPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoneNo() {
		return userPhoneNo;
	}
	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPhoneNo=" + userPhoneNo + ", userEmailId="
				+ userEmailId + ", userAddress=" + userAddress + ", userPassword=" + userPassword + "]";

	}

}	

	
	
	

