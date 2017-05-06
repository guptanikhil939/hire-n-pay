package com.hirenpay.dao;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.Otp;
import com.hirenpay.model.User;

public interface RegistrationDAO
{
	public boolean saveUser(User user) throws PersistenceException;

	public boolean registeredUserForMobile(String phoneNumber)
			throws PersistenceException;

	public boolean registeredUserForWeb(String phoneNumber)
			throws PersistenceException;

	public User getUser(String phoneNumber)  throws PersistenceException;
}
