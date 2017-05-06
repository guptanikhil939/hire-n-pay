package com.hirenpay.dto;

public class MemberDTO
{
	private Integer userId;
	private String firstName;
	private String lastName;
	private int societyId;
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

	public int getSocietyId()
	{
		return societyId;
	}

	public void setSocietyId(int societyId)
	{
		this.societyId = societyId;
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