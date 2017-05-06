package com.hirenpay.dto;

public class UserDTO
{
	private Integer userId;
	private boolean mobile;
	private boolean web;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	private String password;
	private String profilePictureName;

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isMobile()
	{
		return mobile;
	}

	public void setMobile(boolean mobile)
	{
		this.mobile = mobile;
	}

	public boolean isWeb()
	{
		return web;
	}

	public void setWeb(boolean web)
	{
		this.web = web;
	}

	public String getProfilePictureName()
	{
		return profilePictureName;
	}

	public void setProfilePictureName(String profilePictureName)
	{
		this.profilePictureName = profilePictureName;
	}
}