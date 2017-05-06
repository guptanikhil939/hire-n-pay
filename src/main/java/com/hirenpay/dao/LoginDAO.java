package com.hirenpay.dao;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.User;

public interface LoginDAO
{
	public User loginUser(String email, String password) throws PersistenceException;
}
